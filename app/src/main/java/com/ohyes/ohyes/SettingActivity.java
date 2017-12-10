package com.ohyes.ohyes;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Button startAlarm = (Button) findViewById(R.id.bAddMed);
        Button bTestBut = (Button) findViewById(R.id.bTest);
        Button bChoose = (Button) findViewById(R.id.bChoose);
        final EditText medName = (EditText) findViewById(R.id.etSNameMed);

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

        final Spinner sChoose = (Spinner) findViewById(R.id.spinnerChoose);

        ArrayList<String> list = new ArrayList<>();
        list.add("1 Rock Style");
        list.add("2 Normal Style");
        list.add("3 Country Style");
        ArrayAdapter<String> adapter = new ArrayAdapter<String> (SettingActivity.this, android.R.layout.simple_spinner_dropdown_item, list);
        sChoose.setAdapter(adapter);

        startAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(globalVariable.medlist == null){
                    ArrayList<String> list = new ArrayList<>();
                    list.add("Aspirin");
                    list.add("chlorpheniramine");
                    list.add("carbocisteine");
                    list.add("NSAID");
                    globalVariable.setMedlist(list);
                }
                ArrayList<String> list = new ArrayList<>();
                list = globalVariable.getMedlist();
                String text = medName.getText().toString();
                list.add(text);
                globalVariable.setMedlist(list);

                medName.setText("Success");

            }
        });

        bTestBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sChoose.getSelectedItem().toString().equals("1")){
                    MediaPlayer mediaSong = MediaPlayer.create(getApplicationContext(), R.raw.animal);
                    mediaSong.start();
                } else if(sChoose.getSelectedItem().toString().equals("2")){
                    MediaPlayer mediaSong = MediaPlayer.create(getApplicationContext(), R.raw.bluesy);
                    mediaSong.start();
                } else if(sChoose.getSelectedItem().toString().equals("3")){
                    MediaPlayer mediaSong = MediaPlayer.create(getApplicationContext(), R.raw.dixie);
                    mediaSong.start();
                }

            }
        });

        bChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sChoose.getSelectedItem().toString().equals("1")){
                    globalVariable.setSongId(1);
                } else if(sChoose.getSelectedItem().toString().equals("2")){
                    globalVariable.setSongId(2);
                } else if(sChoose.getSelectedItem().toString().equals("3")){
                    globalVariable.setSongId(3);
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                builder.setMessage("Success Choosing Song")
                        .setNegativeButton("OK", null)
                        .create()
                        .show();
            }
        });




        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.action_profile){
                    Intent intent = new Intent(SettingActivity.this, ProfileActivity.class);
                    SettingActivity.this.startActivity(intent);
                } else if (item.getItemId()==R.id.action_alarm){
                    Intent intent = new Intent(SettingActivity.this, AddAlarmActivity.class);
                    SettingActivity.this.startActivity(intent);
                } else if (item.getItemId()==R.id.action_setting){
                    Intent intent = new Intent(SettingActivity.this, SettingActivity.class);
                    SettingActivity.this.startActivity(intent);
                }
                return true;
            }
        });
    }
}
