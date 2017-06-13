package com.sps.mtdectect;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.File;

public class secondPage extends AppCompatActivity {
    static SQLiteDatabase db;
    static ToggleButton detectorButton;
    static boolean checkSync=true;
    static SendOrientationData ss=new SendOrientationData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);
        ProVariables.mycontext=this;
        AppDatabase dbhandle=new AppDatabase();
        db=dbhandle.getWritableDatabase();
        ProVariables.maindb=db;
        InitializeGui();


    }

    public  void onButtonClicked(View set){

        if(detectorButton.isChecked()){
            startService(new Intent(this,DetectorService.class));
            if(checkSync) {
                ss.execute();
                checkSync=false;
            }
            Toast.makeText(secondPage.this, "Motion Detector is ON", Toast.LENGTH_SHORT).show();

        }
        else{
            SenseOrientation.senseCouunter=0;
            stopService(new Intent(this,DetectorService.class));
            Toast.makeText(secondPage.this, "Motion Detector is OFF", Toast.LENGTH_SHORT).show();

        }
    }

    public  void InitializeGui(){
        detectorButton=(ToggleButton)findViewById(R.id.detectOnOff);

    }


}
