package com.example.sarth.nyasihui;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp=getSharedPreferences("Login",MODE_PRIVATE);
        final String s=sp.getString("uname",null);
        setContentView(R.layout.splash_screen);

        new CountDownTimer(2000,1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if(s!=null){
                    Intent i = new Intent(MainActivity.this,roomadd.class);
                    startActivity(i);
                }
                setContentView(R.layout.activity_main);
            }
        }.start();
    }
    public void login(View v){
        Intent i = new Intent(MainActivity.this,signin.class);
        startActivity(i);
    }

    public void register(View v){
        Intent i = new Intent(MainActivity.this,Register.class);
        startActivity(i);
    }
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }
}
