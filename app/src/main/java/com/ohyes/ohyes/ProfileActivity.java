package com.ohyes.ohyes;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final TextView etPName = (TextView) findViewById(R.id.etPName);
        final TextView etPUsername = (TextView) findViewById(R.id.etPUsername);
        final Button bLogout = (Button) findViewById(R.id.bLogout);

        TextView cardText1 = (TextView) findViewById(R.id.cardText1);
        ImageView cardImage1 = (ImageView) findViewById(R.id.cardImage1);

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

        if (globalVariable.alarmStatus.get(0).equals("0") || globalVariable.alarmStatus.get(0).equals("Alarm off")){
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
            String hour = globalVariable.getTimeHour().get(0);
            String min = globalVariable.getTimeMin().get(0);
            String nameMed = globalVariable.getMedName().get(0);
            String medQuantity = globalVariable.getMedQuan().get(0);

            cardText1.setText(hour+":"+min+" take " + nameMed + " for " + medQuantity + " pill");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cardImage1.setImageDrawable(getDrawable(R.drawable.clockicon));
            }

        }

        for(int i= 0 ; i < globalVariable.getCountCode(); i++){
            CardView cardView = new CardView(ProfileActivity.this);
            cardView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

            LinearLayout parent = new LinearLayout(ProfileActivity.this);
            parent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 110));
            parent.setOrientation(LinearLayout.HORIZONTAL);
            parent.setBackgroundResource(R.drawable.card_edge);

            cardView.addView(parent);

//children of parent linearlayout

            ImageView iv = new ImageView(ProfileActivity.this);
            iv.setLayoutParams(new LinearLayout.LayoutParams(150, LinearLayout.LayoutParams.MATCH_PARENT));
            iv.setImageDrawable(getDrawable(R.drawable.clockicon));

            LinearLayout layout2 = new LinearLayout(ProfileActivity.this);
            layout2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            parent.removeAllViews();
            parent.addView(iv);
            parent.addView(layout2);

//children of layout2 LinearLayout

            TextView tv1 = new TextView(ProfileActivity.this);
            tv1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            tv1.setTextSize(20f);
            tv1.setGravity(Gravity.CENTER_HORIZONTAL);
            String hour1 = globalVariable.getTimeHour().get(i+1);
            String min1 = globalVariable.getTimeMin().get(i+1);
            String nameMed1 = globalVariable.getMedName().get(i+1);
            String medQuantity1 = globalVariable.getMedQuan().get(i+1);
            tv1.setText(hour1+":"+min1+" take " + nameMed1 + " for " + medQuantity1 + " pill");
            layout2.removeAllViews();
            layout2.addView(tv1);

            LinearLayout viewLayout = (LinearLayout) findViewById(R.id.viewLayout);
            viewLayout.addView(cardView);
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
