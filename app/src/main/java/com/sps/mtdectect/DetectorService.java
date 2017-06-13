package com.sps.mtdectect;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Sayali on 4/14/2016.
 */
public class DetectorService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SenseOrientation.orientation(ProVariables.mycontext);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SenseOrientation.senseManager.unregisterListener(SenseOrientation.listening);
    }
}
