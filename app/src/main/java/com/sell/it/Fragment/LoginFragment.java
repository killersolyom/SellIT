package com.sell.it.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.sell.it.R;
import com.sell.it.Utility.GlideUtils;

public class LoginFragment extends BaseFragment {

    private EditText mUsernameField;
    private EditText mPasswordField;
    private Button mLoginButton;
    private ImageView mApplicationLogo;

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
    }

    @Override
    protected void initComponents() {
        GlideUtils.loadImage("https://picsum.photos/200", mApplicationLogo);
    }

    @Override
    protected void clearImages() {
        GlideUtils.clearImage(mApplicationLogo);
    }
}
