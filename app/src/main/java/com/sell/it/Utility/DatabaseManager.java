package com.sell.it.Utility;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sell.it.Model.Event;
import com.sell.it.Model.User;

import java.util.Objects;

import static com.sell.it.Model.Constant.Values.Firebase.FAIL;
import static com.sell.it.Model.Constant.Values.Firebase.SUCCESS;
import static com.sell.it.Model.Constant.Values.Firebase.USER_KEY;

public class DatabaseManager {
    private static final String USER_ID = "users";

    private static FirebaseAuth mAuth;
    private static FirebaseDatabase mFirebaseDatabase;
    private static DatabaseReference mDatabase;
    private static Context mContext;

    public static void initialize(Context context) {
        mContext = context;
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = mFirebaseDatabase.getReference();
    }

    public static void createUser(User user) {
        mAuth.createUserWithEmailAndPassword(user.getEmailAddress(), user.getPassword())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        mDatabase.child(USER_ID).child(Objects.requireNonNull(mAuth.getUid()))
                                .setValue(user);
                        Bundle extraBundle = new Bundle();
                        extraBundle.putSerializable(USER_KEY, user);
                        FragmentNavigation.dispatchEvent(new Event(Event.FIREBASE, SUCCESS, extraBundle));
                    } else {
                        FragmentNavigation.dispatchEvent(new Event(Event.FIREBASE, FAIL));
                    }
                });
    }


}
