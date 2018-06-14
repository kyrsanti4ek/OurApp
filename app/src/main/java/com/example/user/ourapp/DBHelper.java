package com.example.user.ourapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.widget.ImageView;

public class DBHelper extends SQLiteOpenHelper {
    // Класс для создания базы данных,
//    пример заполнения
//    public static final int DATABASE_VERSION=1;
//    public static final String DATABASE_NAME=" photoDB";//название базы данных
//    public static final String TABLE_PHOTO="photo"; //Константы для заголовков столбцов таблицы
//
//    public static final String KEY_TVW="tvw";
//    public static final String KEY_IMV="imv";
//    public static final String KEY_VIEW="view";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    public void queryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
//Insert data

    public void insertData (String shortdesc, String title, byte[] image) {
        SQLiteDatabase database = getWritableDatabase();

// query to insert record in database table
String sql = "INSERT INTO RECORD VALUES (NULL, ?, ?, ?, ?)"; // where "RECORD" is table name in database we will create in MainActivity

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

//
//        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PHOTO);
//
//        onCreate(db);

    }







}
