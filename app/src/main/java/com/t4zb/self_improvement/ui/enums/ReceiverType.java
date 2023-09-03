package com.t4zb.self_improvement.ui.enums;

public enum ReceiverType {

    START_REGISTER_STATE,
    REGISTER_SUCCESS,
    USER_DATA_SUCCESS,
    USER_UPDATE_SUCCESS,
    USER_AVATAR_SUCCESS,
    USER_DAILY_STATUS_SUCCESS,
    FINISH,
    UNDEFINED;

    public static String Get(ReceiverType type){
        return type.name();
    }
    public String Get(){
        return Get(this);
    }

}
