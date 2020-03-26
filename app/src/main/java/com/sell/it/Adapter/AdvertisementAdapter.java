package com.sell.it.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sell.it.R;

import java.util.ArrayList;


public class AdvertisementAdapter extends RecyclerView.Adapter<AdvertisementAdapter.RecyclerViewHolder> {

    private Context context;
    private ArrayList<String> items = new ArrayList<>();

    public AdvertisementAdapter() {
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new RecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_advertisement_component, parent, false));
    }

    public void addItem(String item) {
        items.add(item);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, int position) {
        holder.testText.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        private TextView testText;

        RecyclerViewHolder(final View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.recycler_view_item);
            testText = itemView.findViewById(R.id.test_text);
        }

    }


}
