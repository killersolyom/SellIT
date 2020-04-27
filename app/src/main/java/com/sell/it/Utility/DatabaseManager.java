package com.sell.it.Utility;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sell.it.Model.Event;
import com.sell.it.Model.User;

import java.util.Objects;

import static com.sell.it.Model.Constant.Values.User.USER_KEY;

public class DatabaseManager {
    private static final String FIREBASE_USER_KEY = "users";

    private static FirebaseAuth mAuth;
    private static FirebaseDatabase mFirebaseDatabase;
    private static DatabaseReference mDatabase;
    private static String TAG = "DATABASEMANAGER";

    static void initialize(Context context) {
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = mFirebaseDatabase.getReference();
    }

    public static void createUser(User user){
        mAuth.createUserWithEmailAndPassword(user.getEmailAddress(), user.getPassword())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "createUserWithEmail:success");
                        mDatabase.child(FIREBASE_USER_KEY).child(Objects.requireNonNull(mAuth.getUid()))
                                .setValue(user);
                        DataManager.saveUser(user);
                        Bundle extraBundle = new Bundle();
                        extraBundle.putSerializable(USER_KEY, user);
                        EventDispatcher.offerEvent(new Event(Event.TYPE_FIREBASE,Event.ACTION_REGISTRATION_SUCCESS,extraBundle),true);
                    } else {
                        EventDispatcher.offerEvent(new Event(Event.TYPE_FIREBASE,Event.ACTION_REGISTRATION_FAIL), true);
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    }
                });
    }

    public static void loginUser(String emailAddress, String password){
        mAuth.signInWithEmailAndPassword(emailAddress,password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        if (DataManager.isUserExist(emailAddress, password)) {
                            User loggedInUser =  DataManager.getUser();
                            Bundle extraBundle = new Bundle();
                            extraBundle.putSerializable(USER_KEY, loggedInUser);
                            EventDispatcher.offerEvent(new Event(Event.TYPE_FIREBASE,Event.ACTION_LOGIN_SUCCESS,extraBundle),true);
                        }

                    }
                    else{
                        EventDispatcher.offerEvent(new Event(Event.TYPE_FIREBASE,Event.ACTION_LOGIN_FAIL),true);
                        Log.w(TAG, "loginUserWithEmail:failure", task.getException());
                    }
                });
    }
}
