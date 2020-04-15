package com.sell.it.Utility;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sell.it.Model.User;

import java.util.Objects;
import java.util.concurrent.Executor;

public class DatabaseManager {
    private static final String USER_KEY = "users";

    private static FirebaseAuth mAuth;
    private static FirebaseDatabase mFirebaseDatabase;
    private static DatabaseReference mDatabase;
    private static Context mContext;
    private static String TAG = "DATABASEMANAGER";

    public static void initialize(Context context) {
        mContext = context;
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = mFirebaseDatabase.getReference();
    }

    public static void createUser(User user){
        mAuth.createUserWithEmailAndPassword(user.getEmailAddress(), user.getPassword())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "createUserWithEmail:success");
                        mDatabase.child(USER_KEY).child(Objects.requireNonNull(mAuth.getUid()))
                                .setValue(user);
                        DataManager.saveUser(user);
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    }
                });
    }


}
