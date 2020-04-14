package com.example.myapplication1.util;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class InternetActive extends AsyncTask<Void, Void, String>
{
    private Context _context;
    InputStream is = null;
    String json = "Fail";

    public InternetActive(Context context) {
        this._context = context;
    }


    @Override
    protected String doInBackground(Void... params) {
        try {
            URL strUrl = new URL(
                    "http://icons.iconarchive.com/icons/designbolts/handstitch-social/24/Android-icon.png"
            );
            // Here I have taken one android small icon from server, you can put your own icon on server
            // and access your URL, otherwise icon may removed from another server.

            URLConnection connection = strUrl.openConnection();
            connection.setDoOutput(true);
            is =  connection.getInputStream();
            json = "Success";

        } catch (Exception e) {
            e.printStackTrace();
            json = "Fail";
        }
        return json;

    }

    @Override
    protected void onPostExecute(String result) {
        if (result != null)
        {
            if(result.equals("Fail"))
            {
                Toast.makeText(_context, "Internet Not Active", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(_context, "Internet Active " + result, Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(_context, "Internet Not Active", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(_context.getApplicationContext(), "Validating Internet", Toast.LENGTH_LONG).show();
        super.onPreExecute();
    }
}
