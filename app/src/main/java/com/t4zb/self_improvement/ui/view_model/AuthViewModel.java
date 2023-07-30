package com.t4zb.self_improvement.ui.view_model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.t4zb.self_improvement.model.AuthModel;

public class AuthViewModel extends ViewModel {
    private MutableLiveData<Boolean> userStatus = new MutableLiveData<>();
    private MutableLiveData<AuthModel> authsStatus = new MutableLiveData<>();


    public MutableLiveData<Boolean> getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Boolean userStatus) {
        this.userStatus.setValue(userStatus);
    }

    public MutableLiveData<AuthModel> getAuthsStatus() {
        return authsStatus;
    }

    public void setAuthsStatus(AuthModel authsStatus) {
        this.authsStatus.setValue(authsStatus);
    }
}
