package com.sell.it.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.sell.it.R;

public class RegistrationFragment extends BaseFragment {

    private EditText mEmailField;
    private EditText mFirstNameField;
    private EditText mLastNameField;
    private EditText mUsernameField;
    private EditText mPasswordField;
    private Button mLoginButton;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    @Override
    protected void findView(View view) {
        mEmailField = view.findViewById(R.id.user_email_field);
        mFirstNameField = view.findViewById(R.id.user_first_name_field);
        mLastNameField = view.findViewById(R.id.user_last_name_field);
        mUsernameField = view.findViewById(R.id.user_name_field);
        mPasswordField = view.findViewById(R.id.user_password_field);
        mLoginButton = view.findViewById(R.id.login_button);
    }

    @Override
    protected void initComponents() {

    }

}
