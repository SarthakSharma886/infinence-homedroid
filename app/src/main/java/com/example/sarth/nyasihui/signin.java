package com.example.sarth.nyasihui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class signin extends AppCompatActivity {
    EditText e1,e2;
    public SharedPreferences sp;
    String uname,pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sp=getSharedPreferences("Login",MODE_PRIVATE);
        uname =sp.getString("uname",null);
        pwd =sp.getString("password",null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        e1 = findViewById(R.id.editText);
        e2 = findViewById(R.id.editText2);
    }

    public void login(View v) {
        String s1 = e1.getText().toString();
        String s2 = e2.getText().toString();
        if (TextUtils.isEmpty(s1)) {
            e1.setError("Enter Mobile No.");
            return;
        } else if (TextUtils.isEmpty(s2)) {
            e2.setError("Enter Password");
            return;
        }
        else{
            if (s1.equals(uname)&&s2.equals(pwd)) {
                Intent i = new Intent(signin.this, roomadd.class);
                startActivity(i);
            }
            else{
                Toast.makeText(this,"Wrong Password or Username",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
