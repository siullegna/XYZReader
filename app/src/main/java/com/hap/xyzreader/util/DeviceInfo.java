package com.hap.xyzreader.util;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by luis on 5/12/18.
 */

public class DeviceInfo {
    public static int getScreenWidth(final Activity activity) {
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }
}