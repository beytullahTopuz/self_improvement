package com.t4zb.self_improvement.utils;

import android.app.Activity;
import android.graphics.Point;
import android.util.DisplayMetrics;

public class DeviceUtility {

    public static Point getWindowsize(Activity activity) {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;
        return new Point(screenWidth, screenHeight);
    }

}
