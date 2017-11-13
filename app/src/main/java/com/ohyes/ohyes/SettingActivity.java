package com.ohyes.ohyes;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Button startAlarm = (Button) findViewById(R.id.bAddMed);
        final EditText medName = (EditText) findViewById(R.id.etSNameMed);

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

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
