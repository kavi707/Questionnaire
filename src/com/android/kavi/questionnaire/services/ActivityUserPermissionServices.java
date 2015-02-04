package com.android.kavi.questionnaire.services;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by kavi707 on 2/4/15.
 *
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class ActivityUserPermissionServices {

    /**
     * check the internet connection in the device for running application
     * @param context
     * @return boolean
     */
    public boolean isOnline(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        }

        return false;
    }
}
