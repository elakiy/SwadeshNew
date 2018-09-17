package com.wankys.www.swadeshurja.Utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Elakiya on 4/3/2018.
 */
public class DetectConnection {
    public static boolean checkInternetConnection(Context context) {
        // detect internet connection
        ConnectivityManager con_manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return con_manager.getActiveNetworkInfo() != null && con_manager.getActiveNetworkInfo().isAvailable() && con_manager.getActiveNetworkInfo().isConnected();
    }
}
