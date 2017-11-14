package com.ohyes.ohyes;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final EditText etPName = (EditText) findViewById(R.id.etPName);
        final EditText etPUsername = (EditText) findViewById(R.id.etPUsername);
        final Button bLogout = (Button) findViewById(R.id.bLogout);


        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        if (globalVariable.alarmStatus==null || globalVariable.alarmStatus.equals("Alarm off")){

        }
        else {
            //hour.setText(globalVariable.getTimeHour());
            //min.setText(globalVariable.getTimeMin());
            //nameMed.setText(globalVariable.getMedName());
            //medQuantity.setText(globalVariable.getMedQuan());
            String hour = globalVariable.getTimeHour();
            String min = globalVariable.getTimeMin();
            String nameMed = globalVariable.getMedName();
            String medQuantity = globalVariable.getMedQuan();


            TextView myText = new TextView(ProfileActivity.this);
            myText.setText(hour+":"+min+" " + nameMed + " " + medQuantity + " pill");
            LinearLayout ll = (LinearLayout)findViewById(R.id.viewLayout);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ll.addView(myText, lp);

        }


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.action_profile){
                    Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                    ProfileActivity.this.startActivity(intent);
                } else if (item.getItemId()==R.id.action_alarm){
                    Intent intent = new Intent(ProfileActivity.this, AddAlarmActivity.class);
                    ProfileActivity.this.startActivity(intent);
                } else if (item.getItemId()==R.id.action_setting){
                    Intent intent = new Intent(ProfileActivity.this, SettingActivity.class);
                    ProfileActivity.this.startActivity(intent);
                }
                return true;
            }
        });

        //Intent intent = getIntent();
        //String name = intent.getStringExtra("name");
        //String username = intent.getStringExtra("username");
        String name = globalVariable.getUserid();
        String username = globalVariable.getUsername();

        etPName.setText(name);
        etPUsername.setText(username);

        bLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                ProfileActivity.this.startActivity(intent);
            }
        });
    }
}
