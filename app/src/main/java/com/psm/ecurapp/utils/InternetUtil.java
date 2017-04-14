package com.psm.ecurapp.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by pablo on 13/6/2016.
 */
public class InternetUtil {

    public static boolean isOnline(Activity context) {

        if (PermissionUtils.validateAccessNetworkState(context)){
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return netInfo != null && netInfo.isConnectedOrConnecting();
        }

        return false;


    }
}
