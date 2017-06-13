package com.sps.mtdectect;


/**
 * Created by Survya on 4/12/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.*;
import android.util.Log;

public class AppDatabase extends SQLiteOpenHelper{

    AppDatabase(){
        super(ProVariables.mycontext,ProVariables.DATABASE,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ProVariables.Create_Table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static void dbInsertData(SQLiteDatabase db,float x,float y,float z,String devicID,String timestamp){
        ContentValues values=new ContentValues();
        values.put(ProVariables.Col_1,x);
        values.put(ProVariables.Col_2,y);
        values.put(ProVariables.Col_3,z);
        values.put(ProVariables.Col_4,devicID);
        values.put(ProVariables.Col_5,timestamp);
        db.insert(ProVariables.TABLE,null,values);

    }
}
