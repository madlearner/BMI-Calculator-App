package com.mad.bmicalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.sql.Date;

/**
 * Created by madhavisoni on 6/27/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    Context context;
    SQLiteDatabase db;

    DatabaseHandler(Context context) {
        super(context, "bmidb", null, 1);
        this.context = context;
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String s1 = "create table bmi(bmi double, date Date)";
        db.execSQL(s1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addBmi(double res, String formattedDate) {
        ContentValues cv = new ContentValues();
        cv.put("bmi", res);
        cv.put("date", formattedDate);
        long rid = db.insert("bmi", null, cv);
        if (rid < 0) {
            Toast.makeText(context, "Can't Save ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Saved Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public String getBmi() {
//        Cursor cursor=db.query("bmi",new String[]{"resbmi","formattedDate"},null,null,null,null,null);
//        int bmiColumn=cursor.getColumnIndex("resbmi");
//        int dateColumn=cursor.getColumnIndex("formattedDate");
//
//        cursor.moveToFirst();
//
//        if (cursor!=null && (cursor.getCount()>0)){
//            do {
//                String bmi=cursor.getString(bmiColumn);
//                String date=cursor.getString(dateColumn);
//            }while(cursor.moveToNext());
//        }
//        else {
//        return;}
//    }
        StringBuffer sb= new StringBuffer();
        Cursor cursor= db.query("bmi",null,null,null,null,null,null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            do {
                String b=cursor.getString(0);
                String d=cursor.getString(1);
                sb.append("Date: "+d+"\n"+"Bmi: "+b+"\n\n");
            }while (cursor.moveToNext());
        }
        else {return "No records to display";}
        return sb.toString();
    }
}
