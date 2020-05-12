package com.sell.it.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.sell.it.Model.CustomPairItem;
import com.sell.it.Model.ViewHolderItem.DropDownItem;
import com.sell.it.R;

import java.util.ArrayList;

public class DropDownViewItem extends BaseInputViewItem implements AdapterView.OnItemSelectedListener {

    private Spinner mSpinner;
    private TextView mTitle;
    private ArrayList<CustomPairItem<String, Class<?>>> mPairItemList;

    public DropDownViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void inflateView(Context context) {
        itemView = LayoutInflater.from(context).inflate(R.layout.drop_down_component_layout, this, true);
    }

    @Override
    protected void initView() {
        mSpinner = findViewById(R.id.selector_spinner);
        mTitle = findViewById(R.id.input_title);
    }

    public void bindItem(DropDownItem item) {
        mValueListener = item.getListener();
        mTitle.setText(item.getTitle());
        mPairItemList = item.getItems();
        ArrayAdapter<CustomPairItem<String, Class<?>>> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, mPairItemList);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mValueListener.writeValue((Class<?>) mPairItemList.get(position).second);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}