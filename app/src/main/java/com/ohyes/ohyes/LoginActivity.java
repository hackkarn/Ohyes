package com.ohyes.ohyes;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private ViewGroup rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etUsername = (EditText) findViewById(R.id.etLUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etLPassword);
        final Button bLogin = (Button) findViewById(R.id.bLogIn);
        final TextView registerLink = (TextView) findViewById(R.id.tvRegisterH);
        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

        registerLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);

            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                ArrayList<String> globalList = new ArrayList<>();
                ArrayList<String> globalList2;
                ArrayList<String> globalList3;
                ArrayList<String> globalList4;
                globalList.add(0, "Alarm off");
                globalVariable.setAlarmStatus(globalList);
                globalList= new ArrayList<>();
                globalList.add(0, "0");
                globalVariable.setTimeHour(globalList);
                globalList2= new ArrayList<>();
                globalList2.add(0, "0");
                globalVariable.setTimeMin(globalList2);
                globalList3= new ArrayList<>();
                globalList3.add(0, "0");
                globalVariable.setMedName(globalList3);
                globalList4= new ArrayList<>();
                globalList4.add(0, "0");
                globalVariable.setMedQuan(globalList4);

                if (username.equals("") && password.equals("")){
                    globalVariable.setUsername("adminTest");
                    globalVariable.setUserid("adminOffline");
                    Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                    LoginActivity.this.startActivity(intent);
                    finish();
                }
                else {

                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");

                                if (success) {
                                    String name = jsonResponse.getString("name");

                                    Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                                    intent.putExtra("name", name);
                                    intent.putExtra("username", username);
                                    globalVariable.setUsername(name);
                                    globalVariable.setUserid(username);

                                    LoginActivity.this.startActivity(intent);
                                    finish();

                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                    builder.setMessage("Login Failed")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                    queue.add(loginRequest);
                }
            }
        });


    }
}
