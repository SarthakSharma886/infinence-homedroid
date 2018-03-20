package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper {

   // public static final String DB_NAME="Home";
    public static  final int DB_VERSION=1;

    public MyDatabase(Context context,String uname) {
        super(context, uname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Room_Detail(room_no integer,detail text)");
        db.execSQL("create table switch_detail(room_no integer,switch_no integer, detail text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public  void save(ContentValues cv){
       SQLiteDatabase db=getWritableDatabase();


       db.insert("Room_Detail",null,cv);



    }

    public int getMax()
    {
        SQLiteDatabase db=getReadableDatabase();

        Cursor c=db.rawQuery("select max(room_no) from Room_Detail",null);
        c.moveToNext();
        int d=c.getInt(0);
        return d;


    }
//    public void delete(){
//        this.deleteDatabase();
//    }
}
