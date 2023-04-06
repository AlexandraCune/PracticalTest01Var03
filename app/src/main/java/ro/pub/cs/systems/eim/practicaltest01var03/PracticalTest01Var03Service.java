package ro.pub.cs.systems.eim.practicaltest01var03;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class PracticalTest01Var03Service extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        int first_number = intent.getIntExtra("first_number", 0);
        int second_number = intent.getIntExtra("second_number", 0);
        Log.d("Service", first_number + " " + second_number);

        ProccesingThread processingThread = new ProccesingThread(this, first_number, second_number);

        processingThread.start();

        return START_REDELIVER_INTENT;
    }
}
