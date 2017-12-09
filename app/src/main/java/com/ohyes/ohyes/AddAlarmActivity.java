package com.ohyes.ohyes;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

public class AddAlarmActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    AlarmManager alarmManager;
    TimePicker alarmTimePicker;
    TextView alarmStatus;
    Context context;
    PendingIntent pendingIntent;
    Spinner spinnerSet;
    Spinner spinnerQuantity;
    long pickMedDrop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);
        this.context = this;

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmTimePicker = (TimePicker) findViewById(R.id.timePicker);
        alarmStatus = (TextView) findViewById(R.id.tvAlarmStatus);

        final Calendar wakeUpCall = Calendar.getInstance();
        final Calendar now = Calendar.getInstance();

        Button startAlarm = (Button) findViewById(R.id.bStartAlarm);
        Button stopAlarm = (Button) findViewById(R.id.bStopAlarm);

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

        final Intent intent = new Intent(AddAlarmActivity.this, AlarmReceiver.class);
        final String text1 = globalVariable.getAlarmStatus();

        spinnerSet = (Spinner) findViewById(R.id.medPick);
        spinnerQuantity = (Spinner) findViewById(R.id.medQuantity);



        if(text1==null || text1.equals("Alarm off")){
            alarmStatus.setText("Alarm off");
        }else {
            alarmStatus.setText(globalVariable.getAlarmStatus());
        }

        // set drop down menu for medicine name
        if(globalVariable.medlist == null){
            ArrayList<String> list = new ArrayList<>();
            list.add("Aspirin");
            list.add("chlorpheniramine");
            list.add("carbocisteine");
            list.add("NSAID");
            globalVariable.setMedlist(list);
        } else{
            ArrayList<String> list = new ArrayList<>();
            list = globalVariable.getMedlist();
        }


        ArrayList<String> list2 = new ArrayList<>();
        list2.add("1");
        list2.add("2");
        list2.add("3");
        list2.add("4");
        list2.add("5");



        ArrayAdapter<String> adapter = new ArrayAdapter<String> (AddAlarmActivity.this, android.R.layout.simple_spinner_dropdown_item, globalVariable.getMedlist());
        spinnerSet.setAdapter(adapter);
        adapter = new ArrayAdapter<String> (AddAlarmActivity.this, android.R.layout.simple_spinner_dropdown_item, list2);
        spinnerQuantity.setAdapter(adapter);


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.action_profile){
                    Intent intent = new Intent(AddAlarmActivity.this, ProfileActivity.class);
                    AddAlarmActivity.this.startActivity(intent);
                } else if (item.getItemId()==R.id.action_alarm){
                    Intent intent = new Intent(AddAlarmActivity.this, AddAlarmActivity.class);
                    AddAlarmActivity.this.startActivity(intent);
                } else if (item.getItemId()==R.id.action_setting){
                    Intent intent = new Intent(AddAlarmActivity.this, SettingActivity.class);
                    AddAlarmActivity.this.startActivity(intent);
                }
                return true;
            }
        });

        startAlarm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int hour = 0;
                int minute = 0;

                wakeUpCall.setTimeInMillis(System.currentTimeMillis());

                if(Build.VERSION.SDK_INT < 23){
                    hour = alarmTimePicker.getCurrentHour();
                    minute = alarmTimePicker.getCurrentMinute();
                    wakeUpCall.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
                    wakeUpCall.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
                    wakeUpCall.set(Calendar.SECOND, 0);

                } else{
                    hour = alarmTimePicker.getHour();
                    minute = alarmTimePicker.getMinute();
                    wakeUpCall.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getHour());
                    wakeUpCall.set(Calendar.MINUTE, alarmTimePicker.getMinute());
                    wakeUpCall.set(Calendar.SECOND, 0);
                }

                if (wakeUpCall.getTimeInMillis() <= now.getTimeInMillis()) {
                    wakeUpCall.setTimeInMillis(wakeUpCall.getTimeInMillis() + (AlarmManager.INTERVAL_DAY + 1));
                }
                else {
                    wakeUpCall.setTimeInMillis(wakeUpCall.getTimeInMillis());
                }

                //Set time to globalClass and add zero if less than 10
                if(hour < 10){
                    globalVariable.setTimeHour("0" + String.valueOf(hour));
                } else {
                    globalVariable.setTimeHour(String.valueOf(hour));
                }

                if(minute < 10){
                    globalVariable.setTimeMin("0" + String.valueOf(minute));
                } else {
                    globalVariable.setTimeMin(String.valueOf(minute));
                }

                globalVariable.setAlarmStatus("Alarm set to: " + globalVariable.getTimeHour() + ":" + globalVariable.getTimeMin());
                alarmStatus.setText(globalVariable.getAlarmStatus());

                intent.putExtra("extra", "alarm on");

                String medName = spinnerSet.getSelectedItem().toString();
                String medQuan = spinnerQuantity.getSelectedItem().toString();
                globalVariable.setMedName(medName);
                globalVariable.setMedQuan(medQuan);

                pendingIntent = PendingIntent.getBroadcast(AddAlarmActivity.this, 0, intent, pendingIntent.FLAG_UPDATE_CURRENT);

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                    alarmManager.set(AlarmManager.RTC_WAKEUP, wakeUpCall.getTimeInMillis(), pendingIntent);
                }
                else if (Build.VERSION_CODES.KITKAT <= Build.VERSION.SDK_INT  && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, wakeUpCall.getTimeInMillis(), pendingIntent);
                }
                else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, wakeUpCall.getTimeInMillis(), pendingIntent);
                }



            }
        });

        stopAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalVariable.setAlarmStatus("Alarm off");
                alarmStatus.setText("Alarm off");

                alarmManager.cancel(pendingIntent);

                intent.putExtra("extra", "alarm off");

                sendBroadcast(intent);


                //setContentView(R.layout.activity_add_alarm);

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}
