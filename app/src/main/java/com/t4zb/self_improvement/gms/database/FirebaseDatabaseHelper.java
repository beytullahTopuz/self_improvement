package com.t4zb.self_improvement.gms.database;

import android.content.Intent;
import android.os.Bundle;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.firestore.FirebaseFirestore;
import com.t4zb.self_improvement.app.APP;
import com.t4zb.self_improvement.app.User;
import com.t4zb.self_improvement.model.AuthModel;
import com.t4zb.self_improvement.model.Avatar;
import com.t4zb.self_improvement.model.DailyStatus;
import com.t4zb.self_improvement.model.DailyStatusValue;
import com.t4zb.self_improvement.model.UserData;
import com.t4zb.self_improvement.ui.enums.ReceiverType;
import com.t4zb.self_improvement.ui.view_model.AuthViewModel;
import com.t4zb.self_improvement.ui.view_model.HomeFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {

    private static final String TAG = FirebaseDatabaseHelper.class.getSimpleName();
    public static final String ACTION_DATA_EVENT = "receiver";
    private static FirebaseDatabaseHelper firebaseDatabaseHelper;
    private final LocalBroadcastManager broadcastManager;
    private final FirebaseFirestore firebaseFirestore;

    public FirebaseDatabaseHelper() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        broadcastManager = LocalBroadcastManager.getInstance(APP.getContext());
    }

    public static FirebaseDatabaseHelper getInstance() {

        if (firebaseDatabaseHelper == null)
            firebaseDatabaseHelper = new FirebaseDatabaseHelper();

        return firebaseDatabaseHelper;
    }

    public void getUser(AuthViewModel authViewModel, String uid) {
        firebaseFirestore.collection("user").document(uid).get().addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                User mUser = task.getResult().toObject(User.class);
                APP.Config.setUser(mUser);
                getUserData(authViewModel, APP.Config.getUser().getUserDataId());
            } else {
                AuthModel authModel = new AuthModel(false, "get fail");
                authViewModel.setAuthsStatus(authModel);
            }
        });
    }

    public void getUserData(AuthViewModel authViewModel, String userDataId) {

        firebaseFirestore.collection("userData")
                .document(userDataId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        UserData userData = documentSnapshot.toObject(UserData.class);
                        APP.Config.setUserData(userData);

                        AuthModel authModel = new AuthModel(true, "get success");
                        authViewModel.setAuthsStatus(authModel);
                    } else {
                        AuthModel authModel = new AuthModel(false, "get fail");
                        authViewModel.setAuthsStatus(authModel);
                    }
                }).addOnFailureListener(e -> {
                    AuthModel authModel = new AuthModel(false, "get fail");
                    authViewModel.setAuthsStatus(authModel);
                });
    }

    public void postUser(User user) {

        firebaseFirestore.collection("user").document(user.getUserId()).set(user).addOnSuccessListener(unused -> {
            APP.Config.setUser(user);
            sendBroadcastMessage(ReceiverType.START_REGISTER_STATE);
        }).addOnFailureListener(e -> sendBroadcastMessage(ReceiverType.UNDEFINED));
    }

    public void createUserData(UserData userData) {
        firebaseFirestore.collection("userData").add(userData).addOnSuccessListener(documentReference -> {
            userData.setUserDataId(documentReference.getId());
            APP.Config.getUser().setUserDataId(userData.getUserDataId());
            APP.Config.setUserData(userData);
            sendBroadcastMessage(ReceiverType.USER_DATA_SUCCESS);
        }).addOnFailureListener(e -> sendBroadcastMessage(ReceiverType.UNDEFINED));
    }

    public void updateUserData(UserData userData) {
        firebaseFirestore.collection("userData").document(userData.getUserDataId()).set(userData).addOnSuccessListener(documentReference -> {
            APP.Config.getUser().setUserDataId(userData.getUserDataId());
            APP.Config.setUserData(userData);
            sendBroadcastMessage(ReceiverType.FINISH);

        }).addOnFailureListener(e -> sendBroadcastMessage(ReceiverType.UNDEFINED));
    }

    public void updateUser(User user) {
        firebaseFirestore.collection("user").document(user.getUserId()).set(user).addOnSuccessListener(unused -> {
            APP.Config.setUser(user);
            sendBroadcastMessage(ReceiverType.USER_UPDATE_SUCCESS);
        }).addOnFailureListener(e -> sendBroadcastMessage(ReceiverType.UNDEFINED));
    }

    public void createUserAvatar(Avatar avatar) {

        firebaseFirestore.collection("avatar").add(avatar).addOnSuccessListener(documentReference -> {
            UserData userData = APP.Config.getUserData();
            userData.setAvatarId(documentReference.getId());
            APP.Config.setUserData(userData);
            sendBroadcastMessage(ReceiverType.USER_AVATAR_SUCCESS);
        }).addOnFailureListener(e -> sendBroadcastMessage(ReceiverType.UNDEFINED));
    }

    public void crateUserDailyStatus(DailyStatus dailyStatus) {

        firebaseFirestore.collection("dailyStatus").add(dailyStatus).addOnSuccessListener(documentReference -> {
            UserData userData = APP.Config.getUserData();
            userData.setUserDailyStatusId(documentReference.getId());
            APP.Config.setUserData(userData);
            sendBroadcastMessage(ReceiverType.USER_DAILY_STATUS_SUCCESS);
        }).addOnFailureListener(e -> sendBroadcastMessage(ReceiverType.UNDEFINED));
    }

    public void addDailyEmotionStatus(DailyStatusValue dailyStatusValue, HomeFragmentViewModel homeFragmentViewModel) {

        // burdaki listeyi roomDB den cek aylık , roomDbdekı sayı gun sayısı her 5 ve 5 ın katı oldugu zaman DB ye yaz
        List<DailyStatusValue> dailyStatusValueList = new ArrayList<>();
        dailyStatusValueList.add(dailyStatusValue);

        UserData userData = APP.Config.getUserData();
        firebaseFirestore.collection("dailyStatus")
                .document(userData.getUserDailyStatusId()).update("dailyStatusValue", dailyStatusValueList)
                .addOnSuccessListener(documentReference -> homeFragmentViewModel.setIsSetEmotionValueSuccess(true))
                .addOnFailureListener(e -> homeFragmentViewModel.setIsSetEmotionValueSuccess(false));
    }

    private void sendBroadcastMessage(ReceiverType apiCommand) {

        Bundle bundle = new Bundle();
        Intent intent = new Intent();
        bundle.putString(ACTION_DATA_EVENT, apiCommand.Get());
        intent.putExtra("mBundle", bundle);
        intent.setAction(ACTION_DATA_EVENT);

        broadcastManager.sendBroadcast(intent);
    }

}
