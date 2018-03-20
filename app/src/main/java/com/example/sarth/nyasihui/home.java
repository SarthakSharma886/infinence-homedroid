package com.example.sarth.nyasihui;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import db.MyDatabase;
import myadapters.RoomAdapter;
import mypojo.roompojo;

import static android.content.Context.MODE_PRIVATE;

public class home extends Fragment {
    static RoomAdapter ra;
    GridView gv;
    Switch s;
  static   ArrayList<roompojo> Al=new ArrayList<>();
    public SharedPreferences sp;
    View v;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sp = this.getActivity().getSharedPreferences("Login",MODE_PRIVATE);
        gv = v.findViewById(R.id.gv1);
        roompojo rp = new roompojo();
        rp.setIv(R.drawable.ic_launcher_background);



//
//        MyDatabase db=new MyDatabase(getActivity(),"manish");
//        int i=db.getMax();
//        for(int j=0;j<=i;j++){
//         Al.add(rp);
//        }

        ra = new RoomAdapter(getActivity(),R.layout.roomgridlayout,Al);
        gv.setAdapter(ra);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int status=0;
                s=view.findViewById(R.id.switch1);
                s.toggle();
                Map<String, Integer> params = new HashMap();


                int pin = GPIO.getPinNo(position);
                params.put("pin", pin);
                if(s.isChecked()){
                    status=1;
                }else{
                    status=0;
                }
                Toast.makeText(getActivity(),""+status,Toast.LENGTH_SHORT).show();
                params.put("value", status);
                Toast.makeText(getActivity(),""+pin,Toast.LENGTH_SHORT).show();
                JsonObjectRequest postRequest = new JsonObjectRequest( "http://192.168.43.98/rpc/GPIO.Write",
                        new JSONObject(params),new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.i("boi",error.toString());
                    }
                });
                RequestQueue queue= Volley.newRequestQueue(getActivity());
                queue.add(postRequest);
            }

        });
        gv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Al.remove(position);
                ra.notifyDataSetChanged();
                roomadd.maxno--;
                return false;
            }
        });
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.content_roomadd,container,false);
        return v;


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("home");
    }
}
