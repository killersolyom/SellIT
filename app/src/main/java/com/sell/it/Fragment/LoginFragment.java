package com.sell.it.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sell.it.R;
import com.sell.it.Utility.FragmentNavigation;

public class LoginFragment extends BaseFragment {

    private EditText mUsernameField;
    private EditText mPasswordField;
    private Button mLoginButton;
    private ImageView mApplicationLogo;
    private TextView mSignUpText;
    private TextView mGuestUserText;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    protected void findView(View view) {
        mUsernameField = view.findViewById(R.id.user_name_field);
        mPasswordField = view.findViewById(R.id.user_password_field);
        mLoginButton = view.findViewById(R.id.login_button);
        mApplicationLogo = view.findViewById(R.id.app_logo_image);
        mSignUpText = view.findViewById(R.id.text_have_account);
        mGuestUserText = view.findViewById(R.id.guest_user_text);
    }

    @Override
    protected void initListeners() {
        mSignUpText.setOnClickListener(v -> FragmentNavigation.showRegistrationFragment());
        mGuestUserText.setOnClickListener(v -> FragmentNavigation.showAdvertisementFragment());
    }

    @Override
    protected void loadImages() {
        Glide.with(mFragmentView).load("https://picsum.photos/200").into(mApplicationLogo);
    }

    @Override
    protected void clearImages() {
        Glide.with(mFragmentView).clear(mApplicationLogo);
    }

}
