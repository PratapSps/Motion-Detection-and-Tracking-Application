package com.sps.mtdectect;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Survya on 4/14/2016.
 */
public class SendOrientationData extends AsyncTask {

   static HttpURLConnection connection;
    static boolean checkValidate=true;
    static int checkCounter=0;

    @Override
    protected Object doInBackground(Object[] params) {

           sendData();

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("checking","postExecute");
                if(checkCounter==0 || checkCounter==300){
                    checkValidate=true;
                }
                checkCounter++;
                new SendOrientationData().execute(ProVariables.mycontext);
            }
        }, 1000);

    }

    public static void sendData() {
        try {
            if(checkValidate) {

                checkValidate=false;
                String makeURL = "http://project3.comxa.com/insert_data.php?deviceid=" + ProVariables.devicID + "&orientX=" + ProVariables.x + "&orientY=" + ProVariables.y + "&orientZ=" + ProVariables.z + "&timestamp=" + ProVariables.timestamp;
                URL url = new URL(makeURL);
                Log.d("serverURL", makeURL);
                connection = (HttpURLConnection) url.openConnection();
                BufferedReader readBuffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String check;
                while ((check = readBuffer.readLine()) != null) {
                    result.append(check);
                }
                Log.d("data", result.toString());
                readBuffer.close();
            }

        }
        catch(MalformedURLException ex){

        }
        catch(IOException io){

        }
        finally{
            connection.disconnect();
        }

    }
}