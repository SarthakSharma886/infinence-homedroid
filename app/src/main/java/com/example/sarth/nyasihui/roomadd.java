package com.example.sarth.nyasihui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import db.MyDatabase;
import mypojo.roompojo;

public class roomadd extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public SharedPreferences sp;
    Fragment f ;
    ImageView iv;
    String img;
    Bitmap bp;
    MyDatabase db;
    static int maxno=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roomadd);
         db=new MyDatabase(roomadd.this,"manish");
        f= new home();
        View v = getLayoutInflater().inflate(R.layout.nav_header_roomadd,null);
        iv = v.findViewById(R.id.profilepic);
        trans(f);
        iv.setImageResource(R.drawable.ic_launcher_background);
//        if (iv == null){
//            Toast.makeText(this,"vgdvgc",Toast.LENGTH_LONG).show();
//        }
        sp=getSharedPreferences("Login",MODE_PRIVATE);
        img =sp.getString("image",null);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roompojo rp = new roompojo();
                rp.setIv(R.drawable.ic_launcher_background);
                if(maxno<8) {
                    home.Al.add(rp);
                    maxno++;
                }
                home.ra.notifyDataSetChanged();
//                ContentValues cv=new ContentValues();
//                cv.put("room_no",home.Al.size());
//                cv.put("detail","jhgkiujh");
//                db.save(cv);



//                Intent i = new Intent(roomadd.this,newroom.class);
//                startActivity(i);
            }
        });

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        iv = hView.findViewById(R.id.profilepic);
        change(img);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.roomadd, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Fragment f = null;
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            f  = new home();
        } else if (id == R.id.profile) {

        } else if (id == R.id.energy) {

        } else if (id == R.id.logout) {
            SharedPreferences.Editor e=sp.edit();
            e.remove("uname");
            e.remove("password");
            e.commit();
            Intent i = new Intent(roomadd.this,MainActivity.class);
            startActivity(i);
        }
       trans(f);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void trans(Fragment f){
        if(f!=null){
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.hh,f);
            Toast.makeText(this,"dvvjvs",Toast.LENGTH_LONG).show();
            fragmentTransaction.commit();
        }
    }

    public void change(String img){
        if(img!=null){
            Toast.makeText(this,"vgdvgc",Toast.LENGTH_LONG).show();
            try {
                byte [] encodeByte= Base64.decode(img, Base64.DEFAULT);
                bp= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
                iv.setImageBitmap(bp);

            } catch(Exception e) {
                Log.i("hello",e.getMessage());
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(this,"Don't have any pic",Toast.LENGTH_LONG).show();
        }
    }
}
