package com.sell.it.Fragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sell.it.Model.User;
import com.sell.it.R;
import com.sell.it.Utility.DataCacheUtil;
import com.sell.it.Utility.DataManager;
import com.sell.it.Utility.DatabaseManager;
import com.sell.it.Utility.FragmentNavigation;

public class ProfileFragment extends BaseFragment {

    private ImageView mProfilePicture;
    private TextView mFirstName;
    private TextView mLastName;
    private TextView mEmailAddress;
    private EditText mPhoneNumber;
    private Button mLogoutButton;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void findView(View view) {
        mProfilePicture = view.findViewById(R.id.profile_image);
        mFirstName = view.findViewById(R.id.first_name);
        mLastName = view.findViewById(R.id.last_name);
        mEmailAddress = view.findViewById(R.id.email_address);
        mPhoneNumber = view.findViewById(R.id.phone_number);
        mLogoutButton = view.findViewById(R.id.log_out_button);
    }

    @Override
    protected void initListeners() {
        mLogoutButton.setOnClickListener(v -> {
            DataManager.clearUserData();
            DataCacheUtil.clearItems();
            DatabaseManager.logOut();
            mPhoneNumber.setText("");
            FragmentNavigation.showLoginFragment();
        });
    }

    @Override
    protected void initComponents() {
        User loggedInUser = DataManager.getUser();
        mFirstName.setText(loggedInUser.getFirstName());
        mLastName.setText(loggedInUser.getLastName());
        mEmailAddress.setText(loggedInUser.getEmailAddress());
        mPhoneNumber.setText(DataManager.getPhoneNumber());
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mPhoneNumber.getText().length() <= 11) {
            DataManager.savePhoneNumber(mPhoneNumber.getText().toString());
        }
    }

}
