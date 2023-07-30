package com.t4zb.self_improvement.gms.authentication;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.t4zb.self_improvement.app.APP;
import com.t4zb.self_improvement.app.AppConfig;
import com.t4zb.self_improvement.app.User;
import com.t4zb.self_improvement.model.AuthModel;
import com.t4zb.self_improvement.ui.view_model.AuthViewModel;

public class FirebaseAuthHelper {

    private static final String TAG = FirebaseAuthHelper.class.getSimpleName();
    private static FirebaseAuthHelper firebaseAuthHelper;
    private FirebaseAuth auth;
    private AuthViewModel authViewModel;

    public FirebaseAuthHelper(AuthViewModel authViewModel) {
        auth = FirebaseAuth.getInstance();
        this.authViewModel = authViewModel;
    }

    public static FirebaseAuthHelper GetInstance(AuthViewModel authViewModel) {

        if (firebaseAuthHelper == null)
            firebaseAuthHelper = new FirebaseAuthHelper(authViewModel);

        return firebaseAuthHelper;
    }

    public void registerWithEmail(String userName, String email, String password) {

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                FirebaseUser firebaseUser = auth.getCurrentUser();

                if (firebaseUser == null)
                    return;

                User user = new User(firebaseUser);
                user.setUserName(userName);
                user.setUserPassword(password);
                user.setUserId(firebaseUser.getUid());
                user.setVerifiy(false);

                AppConfig.GMSDatabase().postUser(authViewModel,user);

            } else {
                AuthModel authModel = new AuthModel(false, "Register failed");
                authViewModel.setAuthsStatus(authModel);
            }
        });
    }

    public void LoginEmailPassword(String mail, String pwd) {

        auth.signInWithEmailAndPassword(mail, pwd).addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                AppConfig.GMSDatabase().getUser(authViewModel,auth.getUid());
            } else {
                AuthModel authModel = new AuthModel(false, "Login failed");
                authViewModel.setAuthsStatus(authModel);
            }
        });
    }

}
