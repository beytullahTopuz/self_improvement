package com.t4zb.self_improvement.client;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.t4zb.self_improvement.ui.enums.ReceiverType;

public class ClientReceiver extends BroadcastReceiver {

    private static final String TAG = ClientReceiver.class.getSimpleName();
    public static final String ACTION_DATA_EVENT = "receiver";

    protected void BroadcastRegisterSuccess(ReceiverType receiverType) {}

    protected void BroadcastUserUpdateSuccess(ReceiverType receiverType) {
    }

    protected void BroadcastUserDataSuccess(ReceiverType receiverType) {}

    protected void BroadcastDailyStatusSuccess(ReceiverType receiverType) {}

    protected void BroadcastAvatarSuccess(ReceiverType receiverType) {}

    protected void BroadcastStart(ReceiverType receiverType) {}

    protected void BroadcastFINISH(ReceiverType receiverType) {}

    protected void BroadcastFAIL(ReceiverType receiverType) {}

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle mBundle = intent.getBundleExtra("mBundle");
        String data = mBundle.getString(ACTION_DATA_EVENT, "UNDEFINED");
        ReceiverType type = ReceiverType.valueOf(data);

        processBroadcastMessage(type);
    }

    private void processBroadcastMessage(ReceiverType type) {

        switch (type) {
            case START_REGISTER_STATE:
                BroadcastStart(type);
                break;
            case REGISTER_SUCCESS:
                BroadcastRegisterSuccess(type);
                break;
            case USER_DATA_SUCCESS:
                BroadcastUserDataSuccess(type);
                break;
            case USER_UPDATE_SUCCESS:
                BroadcastUserUpdateSuccess(type);
            case USER_DAILY_STATUS_SUCCESS:
                BroadcastDailyStatusSuccess(type);
                break;
            case USER_AVATAR_SUCCESS:
                BroadcastAvatarSuccess(type);
                break;
            case FINISH:
                BroadcastFINISH(type);
                break;
            case UNDEFINED:
                BroadcastFAIL(type);

        }
    }

}
