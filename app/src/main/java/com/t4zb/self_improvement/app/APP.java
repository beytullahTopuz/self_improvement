package com.t4zb.self_improvement.app;


import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

public class APP extends Application {

    public static APP App;
    public static AppConfig Config;
    public static Resources Resource;
    public static User AppUser;

    @Override
    public void onCreate() {
        super.onCreate();
        App = this;
        Resource = getResources();
        Config = AppConfig.GetInstance();
    }

    public static Context getContext() {
        return App.getApplicationContext();
    }
}
