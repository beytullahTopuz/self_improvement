package com.t4zb.self_improvement.app;

import com.google.firebase.auth.FirebaseUser;

public class User {

    private String userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userDataId;
    private boolean isVerifiy;

    public User() {
    }

    public User(FirebaseUser firebaseUser) {
        this.setUserId(firebaseUser.getUid());
        this.setUserEmail(firebaseUser.getEmail());
        this.setUserDataId("");
        this.setVerifiy(false);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserDataId() {
        return userDataId;
    }

    public void setUserDataId(String userDataId) {
        this.userDataId = userDataId;
    }

    public boolean isVerifiy() {
        return isVerifiy;
    }

    public void setVerifiy(boolean verifiy) {
        isVerifiy = verifiy;
    }
}
