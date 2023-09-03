package com.t4zb.self_improvement.services;

import static com.t4zb.self_improvement.gms.database.FirebaseDatabaseHelper.ACTION_DATA_EVENT;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.t4zb.self_improvement.app.APP;
import com.t4zb.self_improvement.app.AppConfig;
import com.t4zb.self_improvement.client.ClientReceiver;
import com.t4zb.self_improvement.model.Avatar;
import com.t4zb.self_improvement.model.DailyStatus;
import com.t4zb.self_improvement.model.UserData;
import com.t4zb.self_improvement.ui.enums.ReceiverType;

import java.util.ArrayList;

public class InitializeService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LocalBroadcastManager.getInstance(this).registerReceiver(clientReceiver,
                new IntentFilter(ACTION_DATA_EVENT));
        return START_NOT_STICKY;
    }

    private void initializeData(WriteState state) {
        switch (state) {
            case start:
                UserData userData = new UserData();
                userData.setUserId(AppConfig.GetInstance().getUser().getUserId());
                AppConfig.GMSDatabase().createUserData(userData);
                break;
            case updateUser:
                AppConfig.GMSDatabase().updateUser(APP.Config.getUser());
                break;
            case userAvatar:
                Avatar avatar = new Avatar();
                avatar.setUserDataId(APP.Config.getUserData().getUserDataId());
                AppConfig.GMSDatabase().createUserAvatar(avatar);
                break;
            case userDailyStatus:
                DailyStatus dailyStatus = new DailyStatus();
                dailyStatus.setDailyStatusValue(new ArrayList<>());
                dailyStatus.setUserDataId(APP.Config.getUserData().getUserDataId());
                AppConfig.GMSDatabase().crateUserDailyStatus(dailyStatus);
                break;
            case finish:
                AppConfig.GMSDatabase().updateUserData(AppConfig.GetInstance().getUserData());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(clientReceiver);
    }

    private enum WriteState {
        start,
        user,
        userData,
        userAvatar,
        updateUser,
        userDailyStatus,
        finish
    }

    private final ClientReceiver clientReceiver = new ClientReceiver() {

        @Override
        protected void BroadcastStart(ReceiverType receiverType) {
            initializeData(WriteState.start);
        }

        @Override
        protected void BroadcastUserDataSuccess(ReceiverType receiverType) {
            initializeData(WriteState.updateUser);
        }

        @Override
        protected void BroadcastUserUpdateSuccess(ReceiverType receiverType) {
            initializeData(WriteState.userAvatar);
        }

        @Override
        protected void BroadcastAvatarSuccess(ReceiverType receiverType) {
            initializeData(WriteState.userDailyStatus);
        }

        @Override
        protected void BroadcastDailyStatusSuccess(ReceiverType receiverType) {
            initializeData(WriteState.finish);
        }

        @Override
        protected void BroadcastFINISH(ReceiverType receiverType) {
            stopSelf();
        }
    };

}
