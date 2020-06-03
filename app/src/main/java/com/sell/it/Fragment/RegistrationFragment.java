package com.sell.it.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sell.it.CustomView.VerificationEditText;
import com.sell.it.Model.Event;
import com.sell.it.Model.User;
import com.sell.it.R;
import com.sell.it.Utility.BundleUtil;
import com.sell.it.Utility.DatabaseManager;
import com.sell.it.Utility.FragmentNavigation;
import com.sell.it.Utility.TextUtils;

public class RegistrationFragment extends BaseAuthenticationFragment {

    private VerificationEditText mEmailField;
    private VerificationEditText mFirstNameField;
    private VerificationEditText mLastNameField;
    private VerificationEditText mUsernameField;
    private VerificationEditText mPasswordField;
    private Button mRegisterButton;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_registration;
    }

    @Override
    protected void findView(View view) {
        mEmailField = view.findViewById(R.id.user_email_field);
        mFirstNameField = view.findViewById(R.id.user_first_name_field);
        mLastNameField = view.findViewById(R.id.user_last_name_field);
        mUsernameField = view.findViewById(R.id.user_name_field);
        mPasswordField = view.findViewById(R.id.user_password_field);
        mRegisterButton = view.findViewById(R.id.register_button);
    }

    @Override
    protected void initListeners() {
        mRegisterButton.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        boolean isValidEmail = TextUtils.isValidEmailAddress(mEmailField.getItemText());
        mEmailField.updateItem(!isValidEmail);
        boolean isValidPassword = TextUtils.isValidPassword(mPasswordField.getItemText());
        mPasswordField.updateItem(!isValidPassword);
        boolean isValidFirstName = TextUtils.isValidName(mFirstNameField.getItemText());
        mFirstNameField.updateItem(!isValidFirstName);
        boolean isValidLastName = TextUtils.isValidName(mLastNameField.getItemText());
        mLastNameField.updateItem(!isValidLastName);
        boolean isValidUserName = TextUtils.isValidUserName(mUsernameField.getItemText());
        mUsernameField.updateItem(!isValidUserName);

        if (isValidEmail && isValidPassword && isValidFirstName && isValidLastName && isValidUserName) {
            FragmentNavigation.showTransactionDialog(
                    new Event(Event.TYPE_FIREBASE, Event.ACTION_REGISTRATION_FAIL),
                    new Event(Event.TYPE_FIREBASE, Event.ACTION_REGISTRATION_SUCCESS));

            DatabaseManager.createUser(new User(
                    mEmailField.getItemText(),
                    mFirstNameField.getItemText(),
                    mLastNameField.getItemText(),
                    mUsernameField.getItemText(),
                    mPasswordField.getItemText())
            );
        }
    }

    @Override
    protected Bundle saveItems() {
        boolean[] errorArray = {mEmailField.isError(), mFirstNameField.isError(),
                mLastNameField.isError(), mUsernameField.isError(), mPasswordField.isError()};
        return BundleUtil.createBundle(TAG, errorArray);
    }

    @Override
    protected void restoreItems(Bundle bundle) {
        if (BundleUtil.canCast(bundle, TAG, boolean[].class)) {
            boolean[] errorArray = BundleUtil.castItem(bundle, TAG, boolean[].class);
            mEmailField.updateItem(errorArray[0]);
            mFirstNameField.updateItem(errorArray[1]);
            mLastNameField.updateItem(errorArray[2]);
            mUsernameField.updateItem(errorArray[3]);
            mPasswordField.updateItem(errorArray[4]);
        }
    }

}