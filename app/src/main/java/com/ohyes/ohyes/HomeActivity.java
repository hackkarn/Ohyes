package com.ohyes.ohyes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class HomeActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final Button bTest = (Button) findViewById(R.id.plusbutton);

        final Button bTest2 = (Button) findViewById(R.id.minusbutton);

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();


        bTest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                globalVariable.setCount(globalVariable.getCount()+1);
                Button myButton = new Button(HomeActivity.this);
                myButton.setText("Add Me" + globalVariable.getCount());
                LinearLayout ll = (LinearLayout)findViewById(R.id.buttonlayout);
                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                myButton.setId(globalVariable.getCount());


                ll.addView(myButton, lp);

            }
        });

        bTest2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Button myButton = new Button(HomeActivity.this);
                myButton.setText("Remove Me");

                globalVariable.setCount(globalVariable.getCount()-1);
                LinearLayout ll = (LinearLayout)findViewById(R.id.buttonlayout);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                ll.removeViewAt(globalVariable.getCount());


            }
        });



    }


}
