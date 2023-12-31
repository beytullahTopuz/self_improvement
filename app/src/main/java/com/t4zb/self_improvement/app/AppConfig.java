package com.t4zb.self_improvement.app;

import com.t4zb.self_improvement.gms.authentication.FirebaseAuthHelper;
import com.t4zb.self_improvement.gms.database.FirebaseDatabaseHelper;
import com.t4zb.self_improvement.model.UserData;
import com.t4zb.self_improvement.ui.view_model.AuthViewModel;

public class AppConfig {

    private static final String TAG = "AppConfig";
    private static AppConfig appConfig;
    private User user;
    private UserData userData;

    /**
     * APPConfig()
     */
    public AppConfig() {

    }

    /**
     * GetInstance()
     */
    public static AppConfig GetInstance() {

        if (appConfig == null)
            appConfig = new AppConfig();

        return appConfig;
    }

    public User getUser() {
        return this.user;
    }

    public void clearUser() {
        this.user = null;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    /**
     * Function: setUser()
     */
    public void setUser(User user) {
        this.user = user;
    }

    public static FirebaseAuthHelper GMSAuth(AuthViewModel authViewModel) {
        return FirebaseAuthHelper.GetInstance(authViewModel);
    }

    public static FirebaseDatabaseHelper GMSDatabase() {
        return FirebaseDatabaseHelper.getInstance();
    }
}
