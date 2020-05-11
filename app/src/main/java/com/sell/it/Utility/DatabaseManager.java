package com.sell.it.Utility;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sell.it.Model.Event;
import com.sell.it.Model.User;
import com.sell.it.Model.ViewHolderItem.Advertisements.DefaultAdvertisementItem;

import java.util.Objects;

public class DatabaseManager {
    private static final String FIREBASE_USER_KEY = "users";
    private static final String FIREBASE_ADS_KEY = "ads";
    private static final String USER_KEY = "User";


    private static FirebaseAuth mAuth;
    private static FirebaseDatabase mFirebaseDatabase;
    private static DatabaseReference mDatabase;

    static void initialize() {
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = mFirebaseDatabase.getReference();
    }

    public static void createUser(User user) {
        mAuth.createUserWithEmailAndPassword(user.getEmailAddress(), user.getPassword())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        mDatabase.child(FIREBASE_USER_KEY)
                                .child(Objects.requireNonNull(mAuth.getUid())).setValue(user);
                        Bundle extraBundle = BundleUtil.createBundle(USER_KEY, user);
                        EventDispatcher.offerEvent(new Event(Event.TYPE_FIREBASE,
                                Event.ACTION_REGISTRATION_SUCCESS, extraBundle), true);
                    } else {
                        EventDispatcher.offerEvent(new Event(Event.TYPE_FIREBASE,
                                Event.ACTION_REGISTRATION_FAIL), true);
                    }
                });
    }

    public static void loginUser(String emailAddress, String password) {
        mAuth.signInWithEmailAndPassword(emailAddress, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && mAuth.getCurrentUser() != null) {
                        getUserFromDatabase(mAuth.getCurrentUser().getUid());
                    } else {
                        EventDispatcher.offerEvent(new Event(Event.TYPE_FIREBASE,
                                Event.ACTION_LOGIN_FAIL), true);
                    }
                });
    }

    public static void verifyUser(String emailAddress, String password) {
        mAuth.signInWithEmailAndPassword(emailAddress, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && mAuth.getCurrentUser() != null) {
                        EventDispatcher.offerEvent(new Event(Event.TYPE_FIREBASE,
                                Event.ACTION_VERIFICATION_SUCCESS), true);
                    } else {
                        EventDispatcher.offerEvent(new Event(Event.TYPE_FIREBASE,
                                Event.ACTION_VERIFICATION_FAIL), true);
                    }
                });
    }

    public static void logOut() {
        mAuth.signOut();
    }

    private static void getUserFromDatabase(String uId) {
        mDatabase.child(FIREBASE_USER_KEY).child(uId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Bundle extraBundle = BundleUtil.createBundle(USER_KEY, new User(dataSnapshot));
                        EventDispatcher.offerEvent(new Event(Event.TYPE_FIREBASE,
                                Event.ACTION_LOGIN_SUCCESS, extraBundle), true);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    public static void uploadAdvertisement(DefaultAdvertisementItem item) {
        String key = mDatabase.push().getKey();
        mDatabase.child(FIREBASE_ADS_KEY)
                .child(item.getCategoryType())
                .child(item.getItemType())
                .child(Objects.requireNonNull(key))
                .setValue(item).addOnSuccessListener(aVoid -> saveAdvertisementId(key));
    }

    private static void saveAdvertisementId(String key) {
        mDatabase.child(FIREBASE_USER_KEY)
                .child(Objects.requireNonNull(mAuth.getUid()))
                .child(FIREBASE_ADS_KEY)
                .setValue(key);
    }

}
