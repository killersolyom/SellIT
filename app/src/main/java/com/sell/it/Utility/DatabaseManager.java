package com.sell.it.Utility;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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

    static void initialize() {
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = mFirebaseDatabase.getReference();
    }

    public static void createUser(User user) {
        mAuth.createUserWithEmailAndPassword(user.getEmailAddress(), user.getPassword())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "createUserWithEmail:success");
                        mDatabase.child(FIREBASE_USER_KEY).child(Objects.requireNonNull(mAuth.getUid())).setValue(user);
                        Bundle extraBundle = BundleUtil.createBundle(USER_KEY, user);
                        EventDispatcher.offerEvent(new Event(Event.TYPE_FIREBASE, Event.ACTION_REGISTRATION_SUCCESS, extraBundle), true);
                    } else {
                        EventDispatcher.offerEvent(new Event(Event.TYPE_FIREBASE, Event.ACTION_REGISTRATION_FAIL), true);
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    }
                });
    }

    public static void loginUser(String emailAddress, String password) {
        mAuth.signInWithEmailAndPassword(emailAddress, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && mAuth.getCurrentUser() != null) {
                        getUserFromDatabase(mAuth.getCurrentUser().getUid());
                    } else {
                        EventDispatcher.offerEvent(new Event(Event.TYPE_FIREBASE, Event.ACTION_LOGIN_FAIL), true);
                        Log.w(TAG, "loginUserWithEmail:failure", task.getException());
                    }
                });
    }

    public static void logOut() {
        mAuth.signOut();
    }

    private static void getUserFromDatabase(String uId){
        mDatabase.child(FIREBASE_USER_KEY).child(uId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Bundle extraBundle = BundleUtil.createBundle(USER_KEY, new User(dataSnapshot));
                EventDispatcher.offerEvent(new Event(Event.TYPE_FIREBASE, Event.ACTION_LOGIN_SUCCESS, extraBundle), true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
