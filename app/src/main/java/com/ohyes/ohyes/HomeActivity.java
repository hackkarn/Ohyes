package com.ohyes.ohyes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button bTest = (Button) findViewById(R.id.bTest);

        bTest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


            }
        });



    }


}
