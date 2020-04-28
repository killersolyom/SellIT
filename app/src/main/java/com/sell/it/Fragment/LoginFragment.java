package com.sell.it.Fragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatCheckBox;

import com.bumptech.glide.Glide;
import com.sell.it.Model.Event;
import com.sell.it.R;
import com.sell.it.Utility.DataManager;
import com.sell.it.Utility.DatabaseManager;
import com.sell.it.Utility.FragmentNavigation;
import com.sell.it.Utility.TextUtils;

public class LoginFragment extends BaseAuthenticationFragment {

    private EditText mEmailAddressField;
    private EditText mPasswordField;
    private Button mLoginButton;
    private ImageView mApplicationLogo;
    private TextView mSignUpText;
    private TextView mGuestUserText;
    private AppCompatCheckBox mRememberMe;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void findView(View view) {
        mEmailAddressField = view.findViewById(R.id.email_address_field);
        mPasswordField = view.findViewById(R.id.user_password_field);
        mLoginButton = view.findViewById(R.id.login_button);
        mApplicationLogo = view.findViewById(R.id.app_logo_image);
        mSignUpText = view.findViewById(R.id.text_have_account);
        mGuestUserText = view.findViewById(R.id.guest_user_text);
        mRememberMe = view.findViewById(R.id.remember_me_check_box);
    }

    @Override
    protected void initComponents() {
        boolean rememberMe = DataManager.getRememberMeStatus();
        mRememberMe.setChecked(rememberMe);
        mEmailAddressField.setText(DataManager.getEmailAddress());
        mPasswordField.setText(TextUtils.decrypt(DataManager.getPassword()));
        if (rememberMe && DataManager.isUserExist()) {
            long time = DataManager.getLastAuthenticationTime() - System.currentTimeMillis();
            if (time > 0 && time < 1800000) {//30 min
                FragmentNavigation.showAddAdvertisementFragment();
            } else {
                FragmentNavigation.showTransactionDialog(
                        new Event(Event.TYPE_FIREBASE, Event.ACTION_LOGIN_SUCCESS),
                        new Event(Event.TYPE_FIREBASE, Event.ACTION_LOGIN_FAIL));

                DatabaseManager.loginUser(DataManager.getEmailAddress(),
                        TextUtils.decrypt(DataManager.getPassword()));
            }
        }
    }

    @Override
    protected void initListeners() {
        mSignUpText.setOnClickListener(v -> FragmentNavigation.showRegistrationFragment());
        mGuestUserText.setOnClickListener(v -> FragmentNavigation.showAdvertisementFragment());
        mRememberMe.setOnCheckedChangeListener((buttonView, isChecked) -> DataManager.saveRememberMeStatus(isChecked));
        mLoginButton.setOnClickListener(v -> loginUser());
    }

    @Override
    protected void loadImages() {
        Glide.with(mContext).load(R.drawable.app_logo_png).into(mApplicationLogo);
    }

    @Override
    protected void clearImages() {
        Glide.with(mContext).clear(mApplicationLogo);
    }

    private void loginUser() {
        FragmentNavigation.showTransactionDialog(new Event(Event.TYPE_FIREBASE, Event.ACTION_LOGIN_FAIL), new Event(Event.TYPE_FIREBASE, Event.ACTION_LOGIN_SUCCESS));
        String username = mEmailAddressField.getText().toString().trim();
        String password = mPasswordField.getText().toString().trim();
        DatabaseManager.loginUser(username, password);
    }
    
}
