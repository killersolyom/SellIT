package com.sell.it.Fragment;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sell.it.R;

public class ProfileFragment extends BaseFragment {

    private ImageView mProfilePicture;
    private TextView mFirstName;
    private TextView mLastName;
    private TextView mEmailAddress;
    private EditText mPhoneNumber;

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
    }
}
