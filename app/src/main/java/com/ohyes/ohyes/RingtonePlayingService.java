package com.ohyes.ohyes;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Xesnost on 30/10/2560.
 */

public class RingtonePlayingService extends Service {

    MediaPlayer mediaSong;
    int start_id;
    boolean isRunnning;
    Notification notiPopup;
    GlobalClass globalVariable;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        String state = intent.getExtras().getString("extra");
        Log.e("Ringtone state is: ", state);

        NotificationManager notifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent intentAddAlarmActivity = new Intent(this.getApplicationContext(), AddAlarmActivity.class);
        globalVariable = (GlobalClass) getApplicationContext();
        PendingIntent pendingIntentAddAlarm = PendingIntent.getActivity(this, globalVariable.getSelectCode(), intentAddAlarmActivity, 0);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            notiPopup = new Notification.Builder(this).setContentTitle("An alarm is going off")
                    .setContentText("Clickme!")
                    .setContentIntent(pendingIntentAddAlarm)
                    .setAutoCancel(true)
                    .setSmallIcon(R.drawable.ohyes)
                    .build();
        }else {
            notiPopup = new Notification.Builder(this).setContentTitle("An alarm is going off")
                    .setContentText("Clickme!")
                    .setContentIntent(pendingIntentAddAlarm)
                    .setAutoCancel(true)
                    .setSmallIcon(R.drawable.ohyes)
                    .getNotification();
        }


        if (state.equals("alarm on")){
            startId = 1;
            notifyManager.notify(0, notiPopup);

            Log.e("start ID is: ", String.valueOf(startId));
        } else if (state.equals("alarm off")){
            startId = 0;
            Log.e("start ID is: ", String.valueOf(startId));
        } else{
            startId=0;
            Log.e("start ID is: ", String.valueOf(startId));
        }


        if (!this.isRunnning && startId == 1){

            Log.e("there is no music", "and you want start");
            if(globalVariable.getSongId()==2){
                mediaSong = MediaPlayer.create(this, R.raw.bluesy);
            } else if(globalVariable.getSongId()==3){
                mediaSong = MediaPlayer.create(this, R.raw.dixie);
            } else{
                mediaSong = MediaPlayer.create(this, R.raw.animal);
            }
            mediaSong.setLooping(true);
            mediaSong.start();
            this.isRunnning =true;
            this.start_id = 0;


        }else if (this.isRunnning && startId == 0){

            Log.e("there is music", "and you want end");
            mediaSong.stop();
            mediaSong.reset();

            this.isRunnning = false;
            this.start_id = 0;

        }else if (!this.isRunnning && startId == 0){

            Log.e("there is no music", "and you want end");
            this.isRunnning = false;
            this.start_id = 0;

        }else if (this.isRunnning && startId == 1){

            Log.e("there is music", "and you want start");
            this.isRunnning = true;
            this.start_id = 1;

        }else{
            Log.e("else", "some how you reached this");

        }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {

        Log.e("on Destroy Called", "Boom");
        super.onDestroy();
        this.isRunnning = false;

    }

}
