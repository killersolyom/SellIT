package com.sell.it.Utility;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.sell.it.Model.CustomUri;
import com.sell.it.Model.Event;
import com.sell.it.Model.User;
import com.sell.it.Model.ViewHolderItem.Advertisements.DefaultAdvertisementItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class DatabaseManager {
    private static final String FIREBASE_USER_KEY = "users";
    private static final String FIREBASE_ADS_KEY = "ads";
    public static final String USER_KEY = "User";
    public static final String ALL_ADVERTISEMENT_KEY = "ALL_ADVERTISEMENT_KEY";
    public static final String CATEGORY_ADVERTISEMENT_KEY = "CATEGORY_ADVERTISEMENT_KEY";
    public static final String ITEM_TYPE_ADVERTISEMENT_KEY = "ITEM_TYPE_ADVERTISEMENT_KEY";

    private static FirebaseAuth mAuth;
    private static FirebaseDatabase mFirebaseDatabase;
    private static DatabaseReference mDatabase;
    private static StorageReference mStorageRef;

    private static ArrayList<String> imagePathList = new ArrayList<>();

    static void initialize() {
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = mFirebaseDatabase.getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference("/images");
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
                        EventDispatcher.offerEvent(new Event(Event.TYPE_FIREBASE,
                                Event.ACTION_LOGIN_FAIL), true);
                    }
                });
    }

    public static void uploadAdvertisement(DefaultAdvertisementItem item, ArrayList<CustomUri> imageList) {
        DatabaseManager.uploadPictureList(imageList);
        String key = mDatabase.push().getKey();
        item.setId(key);
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
                .child(key)
                .setValue("");
    }

    public static void getAllAdvertisements() {
        mDatabase.child(FIREBASE_ADS_KEY).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() instanceof HashMap){
                    HashMap<?, ?> ads = (HashMap) dataSnapshot.getValue();
                    Bundle extraBundle = BundleUtil.createBundle(ALL_ADVERTISEMENT_KEY, ads);
                    EventDispatcher.offerEvent(new Event(Event.TYPE_FIREBASE,
                            Event.ACTION_GET_ALL_ADVERTISEMENT, extraBundle));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("TAG", "onCancelled: " + databaseError.getDetails());
            }
        });
    }

    public static void getCategoryAdvertisements(String selectedCategory){
        mDatabase.child(FIREBASE_ADS_KEY).child(selectedCategory).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() instanceof  HashMap){
                    HashMap<?,?> categoryAds = (HashMap) dataSnapshot.getValue();
                    Bundle extraBundle = BundleUtil.createBundle(CATEGORY_ADVERTISEMENT_KEY,categoryAds);
                    EventDispatcher.offerEvent(new Event(Event.TYPE_FIREBASE,
                            Event.ACTION_GET_CATEGORY_ADVERTISEMENT, extraBundle));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static void getSubCategoryAdvertisements(String selectedSubCategory, String selectedCategory){
        mDatabase.child(FIREBASE_ADS_KEY).child(selectedCategory).child(selectedSubCategory).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() instanceof  HashMap){
                    HashMap<?,?> subCategoryAds = (HashMap) dataSnapshot.getValue();
                    Bundle extraBundle = BundleUtil.createBundle(ITEM_TYPE_ADVERTISEMENT_KEY,subCategoryAds);
                    EventDispatcher.offerEvent(new Event(Event.TYPE_FIREBASE,
                            Event.ACTION_GET_ITEM_TYPE_ADVERTISEMENT, extraBundle));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static void uploadPictureList(ArrayList<CustomUri> valueList){
        String key = mDatabase.push().getKey();
        uploadPictures(0,valueList,key);

    }

    private static void uploadPictures(int initial, ArrayList<CustomUri> valueList, String key){

        mStorageRef.child(key).child("adv" + initial + ".jpg").putFile(valueList.get(initial).getUri())
        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.d("TAG", "onSuccess: " + Objects.requireNonNull(taskSnapshot.getUploadSessionUri()).getPath());
                if(valueList.size()-1 != initial){
                    uploadPictures(initial+1,valueList,key);
                }
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if(valueList.size()-1 != initial){
                    uploadPictures(initial+1,valueList,key);
                }
            }
        });
    }

}
