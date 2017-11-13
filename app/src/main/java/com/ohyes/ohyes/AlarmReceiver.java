package com.ohyes.ohyes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Xesnost on 30/10/2560.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("We are in the receiver", "Yay!");

        String getYourString = intent.getExtras().getString("extra");
        Log.e("What is the key", getYourString);

        Intent servicesIntent = new Intent(context, RingtonePlayingService.class);
        servicesIntent.putExtra("extra", getYourString);
        context.startService(servicesIntent);

    }
}
