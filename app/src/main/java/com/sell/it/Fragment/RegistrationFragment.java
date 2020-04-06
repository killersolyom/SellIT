package com.sell.it.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sell.it.Model.User;
import com.sell.it.R;
import com.sell.it.Utility.DataManager;
import com.sell.it.Utility.GlideUtils;

import java.util.Objects;
import java.util.concurrent.Executor;

public class RegistrationFragment extends BaseFragment {

    private EditText mEmailField;
    private EditText mFirstNameField;
    private EditText mLastNameField;
    private EditText mUsernameField;
    private EditText mPasswordField;
    private Button mRegisterButton;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabase;
    private String TAG = "REGISTRATIONFRAGMENT";

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
        mRegisterButton = view.findViewById(R.id.register_button);
    }

    @Override
    protected void initComponents() {
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = mFirebaseDatabase.getReference();
    }

    @Override
    protected void loadImages() {
    }

    @Override
    protected void clearImages() {

    }

    @Override
    protected void initListeners() {
        mRegisterButton.setOnClickListener(v -> registerUser());
    }

    private void registerUser(){
        String email = mEmailField.getText().toString().trim();
        String password = mPasswordField.getText().toString().trim();
        String firstName = mFirstNameField.getText().toString().trim();
        String lastName = mLastNameField.getText().toString().trim();
        String username = mUsernameField.getText().toString().trim();

        if(!email.isEmpty() && !password.isEmpty()) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(Objects.requireNonNull(getActivity()), task -> {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            User user = new User(email,firstName,lastName,username);
                            writeNewUserToDatabase(user);
                            saveNewUserToSharedPreferences(user);
                            FirebaseUser mLoggedInUser = mAuth.getCurrentUser();
                            resetFields();
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        }
                    });
        }
        else {
            Log.w(TAG, "Email or password is empty.");
        }
    }

    private void writeNewUserToDatabase(User user){
        mDatabase.child("users").child(Objects.requireNonNull(mAuth.getUid()))
                .setValue(user);
    }

    private void saveNewUserToSharedPreferences(User user){
        DataManager.saveUser(user);
    }

    private void resetFields(){
        mEmailField.setText("");
        mFirstNameField.setText("");
        mLastNameField.setText("");
        mUsernameField.setText("");
        mPasswordField.setText("");
    }
}
