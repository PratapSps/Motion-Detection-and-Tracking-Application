package com.sps.mtdectect;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.provider.Settings.Secure;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Survya on 4/12/2016.
 */
public class SenseOrientation {
    static SensorManager senseManager;
    static Sensor sense;
    static listenOrienttation listening;
    static public float[] orient_Values=new float[3];
    static public float[] matrix_Values=new float[16];
    static int senseCouunter=0;

    public static void orientation(Context appContext)
    {
        appContext=ProVariables.mycontext;
        senseManager=(SensorManager)appContext.getSystemService(Context.SENSOR_SERVICE);
        listening=new listenOrienttation();
        sense=senseManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        senseManager.registerListener(listening,sense,SensorManager.SENSOR_DELAY_NORMAL);
    }


}

class listenOrienttation implements SensorEventListener{
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(event.sensor.getType()==Sensor.TYPE_ROTATION_VECTOR) {
            SensorManager.getRotationMatrixFromVector(SenseOrientation.matrix_Values,event.values);
            SensorManager.remapCoordinateSystem(SenseOrientation.matrix_Values,SensorManager.AXIS_X, SensorManager.AXIS_Z, SenseOrientation.orient_Values);
            SensorManager.getOrientation(SenseOrientation.matrix_Values, SenseOrientation.orient_Values);
            SenseOrientation.orient_Values[0]=(float)Math.toDegrees(SenseOrientation.orient_Values[0]);
            SenseOrientation.orient_Values[1]=(float)Math.toDegrees(SenseOrientation.orient_Values[1]);
            SenseOrientation.orient_Values[2]=(float)Math.toDegrees(SenseOrientation.orient_Values[2]);
            SimpleDateFormat s = new SimpleDateFormat("ddMMyyyyhhmmss");
            String format = s.format(new Date());
            ProVariables.timestamp=format;
            if(SenseOrientation.senseCouunter==0 || SenseOrientation.senseCouunter==60) {
                Log.d("orientation", Float.toString(SenseOrientation.orient_Values[0]));
                ProVariables.x = SenseOrientation.orient_Values[0];
                ProVariables.y = SenseOrientation.orient_Values[1];
                ProVariables.z = SenseOrientation.orient_Values[2];
                String android_id = Secure.getString(ProVariables.mycontext.getContentResolver(), Secure.ANDROID_ID);
                ProVariables.devicID = android_id;
                AppDatabase.dbInsertData(ProVariables.maindb, ProVariables.x, ProVariables.y, ProVariables.z, ProVariables.devicID, ProVariables.timestamp);
                SenseOrientation.senseCouunter=1;
            }
            SenseOrientation.senseCouunter++;
        }


    }
}
