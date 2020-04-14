package com.example.myapplication1.util;

import android.content.Context;
import android.widget.Toast;
import com.example.myapplication1.util.*;

public class HealthHelper {

    public void checkInternetAvailibility(Context context) {
        ConnectionDetector cd = new ConnectionDetector(context);
        int res = cd.getConnectionType();
        if (res > 0) {
             new InternetActive(context).execute();

        } else {
             Toast.makeText(context, "Internet Not Connected", Toast.LENGTH_LONG).show();
        }
    }
}
