package in.screenbiz.gstapply2017;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by harsh singh on 22-06-2017.
 */

public class DetectConnection {

          public static boolean checkInternetConnection(Context context) {

            ConnectivityManager con_manager = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);

            if (con_manager.getActiveNetworkInfo() != null
                    && con_manager.getActiveNetworkInfo().isAvailable()
                    && con_manager.getActiveNetworkInfo().isConnected()) {
                return true;
            } else {
                return false;
            }
        }
    }





