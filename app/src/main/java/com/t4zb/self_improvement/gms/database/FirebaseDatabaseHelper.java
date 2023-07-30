package com.t4zb.self_improvement.gms.database;

import com.google.firebase.firestore.FirebaseFirestore;
import com.t4zb.self_improvement.app.APP;
import com.t4zb.self_improvement.app.User;
import com.t4zb.self_improvement.model.AuthModel;
import com.t4zb.self_improvement.ui.view_model.AuthViewModel;

public class FirebaseDatabaseHelper {

    private static final String TAG = FirebaseDatabaseHelper.class.getSimpleName();
    private static FirebaseDatabaseHelper firebaseDatabaseHelper;
    private final FirebaseFirestore firebaseFirestore;

    public FirebaseDatabaseHelper(){
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public static FirebaseDatabaseHelper GetInstace(){

        if (firebaseDatabaseHelper == null)
            firebaseDatabaseHelper = new FirebaseDatabaseHelper();

        return firebaseDatabaseHelper;
    }

    public void getUser(AuthViewModel authViewModel,String uid) {
        firebaseFirestore.collection("user").document(uid).get().addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                User mUser = task.getResult().toObject(User.class);
                APP.Config.setUser(mUser);
                AuthModel authModel = new AuthModel(true, "get success");
                authViewModel.setAuthsStatus(authModel);
            } else {
                AuthModel authModel = new AuthModel(false, "get fail");
                authViewModel.setAuthsStatus(authModel);
            }
        });
    }

    public void postUser(AuthViewModel authViewModel, User user) {

        firebaseFirestore.collection("user").document(user.getUserId()).set(user).addOnSuccessListener(unused -> {
            APP.Config.setUser(user);
            AuthModel authModel = new AuthModel(true, "post success");
            authViewModel.setAuthsStatus(authModel);
        }).addOnFailureListener(e -> {
            AuthModel authModel = new AuthModel(false, e.getMessage());
            authViewModel.setAuthsStatus(authModel);
        });
    }


}
