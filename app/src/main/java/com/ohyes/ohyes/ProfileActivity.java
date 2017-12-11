package com.ohyes.ohyes;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

        TextView cardText1 = (TextView) findViewById(R.id.cardText1);
        ImageView cardImage1 = (ImageView) findViewById(R.id.cardImage1);

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

        if (globalVariable.alarmStatus==null || globalVariable.alarmStatus.equals("Alarm off")){
            cardText1.setText("Alarm is not set");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cardImage1.setImageDrawable(getDrawable(R.drawable.clockicon));
            }


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

            cardText1.setText(hour+":"+min+" take " + nameMed + " for " + medQuantity + " pill");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cardImage1.setImageDrawable(getDrawable(R.drawable.clockicon));
            }

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
                finish();
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
