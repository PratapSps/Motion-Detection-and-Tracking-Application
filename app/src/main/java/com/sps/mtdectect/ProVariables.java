package com.sps.mtdectect;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Survya on 4/12/2016.
 */
public class ProVariables {
    static final int timer=3000;
    static Context mycontext;
    static final String DATABASE="AppDatabase";
    static final String TABLE="Orientation";
    static final String Col_1="x";
    static final String Col_2="y";
    static final String Col_3="z";
    static final String Col_4="devic_id";
    static final String Col_5="timestamp";
    static final  String Create_Table="CREATE TABLE IF NOT EXISTS " + TABLE + "( " + Col_1 + " DOUBLE," +Col_2  + " DOUBLE," + Col_3+" DOUBLE,"+Col_4+" varchar,"+Col_5+" bigint"+" )";
    static SQLiteDatabase db;
    static float x=0;
    static float y=0;
    static float z=0;
    static String devicID=null;
    static String timestamp=null;
    static SQLiteDatabase maindb;
    static int dataTimer=0;
    //static String ServerURL="http://proj3.atwebpages.com/insert_data.php?deviceid=";
}
