package com.wankys.www.swadeshurja.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Wankys on 3/16/2018.
 */

class NetworkUtils {

    public static boolean isInternetAvailable(Context context) {
        boolean available = false;
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null) {
                    for (int i = 0; i < info.length; i++) {
                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                            available = true;
                        }
                    }
                }
            }
            if (!available) {
                NetworkInfo wiMax = connectivity.getNetworkInfo(6);

                if (wiMax != null && wiMax.isConnected()) {
                    available = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return available;
    }

}


