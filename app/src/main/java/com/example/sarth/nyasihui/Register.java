package com.example.sarth.nyasihui;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class Register extends AppCompatActivity {
    TextView tv;
    EditText e3,e4,e5;
    public SharedPreferences sp;
    Bitmap bp;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp=getSharedPreferences("Login",MODE_PRIVATE);
        setContentView(R.layout.activity_register);
        bp = null;
        iv = findViewById(R.id.imageView);
        tv = findViewById(R.id.textView);
        e3 = findViewById(R.id.editText3);
        e4 = findViewById(R.id.editText4);
        e5 = findViewById(R.id.editText5);
    }
    public void takemage(View v){
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i,0);
    }
    public void register(View v){
        String s1 = e3.getText().toString();
        String s2 = e4.getText().toString();
        if (TextUtils.isEmpty(s1)) {
            e3.setError("Enter Mobile No.");
            return;
        } else if (TextUtils.isEmpty(s2)) {
            e4.setError("Enter Password");
            return;
        }
        else {
            SharedPreferences.Editor e=sp.edit();
            e.putString("uname",e3.getText().toString());
            e.putString("password",e4.getText().toString());
            if(bp!=null){
                ByteArrayOutputStream baos=new ByteArrayOutputStream();
                bp.compress(Bitmap.CompressFormat.PNG,100, baos);
                byte [] b=baos.toByteArray();
                String temp=Base64.encodeToString(b, Base64.DEFAULT);
                e.putString("image",temp);
            }
            e.commit();
            tv.setAlpha(1);
        }
    }

    public void otp(View v){
        if (TextUtils.isEmpty(e5.getText().toString())) {
            e5.setError("Enter Correct O.T.P.");
            return;
        }
        else {
            Intent i = new Intent(Register.this, signin.class);
            startActivity(i);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        bp = (Bitmap) data.getExtras().get("data");
        iv.setImageBitmap(bp);
    }
}
