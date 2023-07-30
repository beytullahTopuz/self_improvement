package com.t4zb.self_improvement.model;

public class AuthModel {

    private boolean authSuccess;
    private String authMessage;

    public AuthModel() {

    }

    public AuthModel(boolean authSuccess, String authMessage) {
        this.authMessage = authMessage;
        this.authSuccess = authSuccess;
    }

    public boolean isAuthSuccess() {
        return authSuccess;
    }

    public void setAuthSuccess(boolean authSuccess) {
        this.authSuccess = authSuccess;
    }

    public String getAuthMessage() {
        return authMessage;
    }

    public void setAuthMessage(String authMessage) {
        this.authMessage = authMessage;
    }
}
