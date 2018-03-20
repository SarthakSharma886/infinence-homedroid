package myadapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sarth.nyasihui.R;
import java.util.ArrayList;

import mypojo.roompojo;

/**
 * Created by sarth on 09-03-2018.
 */

public class RoomAdapter extends BaseAdapter {
    Activity obj;
    int res;
//    Switch s;
    static int position;
    ArrayList<roompojo> Al;
   public RoomAdapter(Activity obj, int res, ArrayList<roompojo> Al){
       this.obj=obj;
       this.Al=Al;
       this.res=res;
    }
    @Override
    public int getCount() {
        return Al.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View  v= LayoutInflater.from(obj).inflate(res,parent,false);

//        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                Log.i("switchvalue",Boolean.toString(s.isChecked()));
//                Log.i("switchposition",Integer.toString(RoomAdapter.position));
//            }
//        });


//        ImageView iv=v.findViewById(R.id.imageView2);
        roompojo rp =Al.get(position);
//        iv.setImageResource(rp.getIv());
        return  v;
    }
}
