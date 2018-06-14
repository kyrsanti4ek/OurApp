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
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = " photoDB";//название базы данных
    public static final String TABLE_NAME = "photo"; //Константы для заголовков столбцов таблицы

    public static final String COL_1 = "ID";
    public static final String COL_2 = "TITLE";
    public static final String COL_3 = "SHORTDESC";
    public static final String COL_4 = "IMAGE";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT , TITLE TEXT ,SHORTDESC TEXT , IMAGE IMAGE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean insertData(String title, String shortdesc, byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, title);
        contentValues.put(COL_3, shortdesc);
        contentValues.put(COL_4, image);
        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
}
//    public Cursor getAllData() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor res = db.rawQuery("Select * from " + TABLE_NAME, null);
//        return res;
//    }


//    public boolean insertData(String title, String shortdesc, byte[] image) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_2,title);
//        contentValues.put(COL_3,shortdesc);
//        contentValues.put(COL_4,image);
//        long result = db.insert(TABLE_NAME,null,contentValues);
//
//        // To Check Whether Data is Inserted in DataBase
//        if(result==1){
//            return false;
//        } else{
//            return true;
//        }
//    }
//    public void queryData(String sql) {
//        SQLiteDatabase database = getWritableDatabase();
//        database.execSQL(sql);
//    }
////Insert data
//
//    public void insertData (String shortdesc, String title, byte[] image) {
//        SQLiteDatabase database = getWritableDatabase();
//
//// query to insert record in database table
//String sql = "INSERT INTO RECORD VALUES (NULL, ?, ?, ?, ?)"; // where "RECORD" is table name in database we will create in MainActivity
//
//        SQLiteStatement statement = database.compileStatement(sql);
//        statement.clearBindings();
//
//        statement.bindString(1, shortdesc);
//        statement.bindString(2, title);
//        statement.bindBlob(3, image);
//
//        statement.executeInsert();
//    }
//    //updateData
//    public  void updateData(String shortdesc, String title, byte[] image, int id) {
//        SQLiteDatabase database = getWritableDatabase();
//        //query to update record
//        String sql = "UPDATE RECORD SET shortdesc=?, title=?, image=? WHERE id=?";
//
//        SQLiteStatement statement = database.compileStatement(sql);
//
//        statement.bindString(1, shortdesc);
//        statement.bindString(2, title);
//        statement.bindBlob(3, image);
//        statement.bindDouble(5, (double)id);
//
//        statement.execute();
//        database.close();
//    }
//
//    //deleteData
//    public void deleteData(int id){
//        SQLiteDatabase database = getWritableDatabase();
//        //query to delete record using id
//        String sql = "DELETE FROM RECORD WHERE id=?";
//
//        SQLiteStatement statement = database.compileStatement(sql);
//        statement.clearBindings();
//        statement.bindDouble(1, (double)id);
//
//
//        statement.execute();
//        database.close();
//    }
//
//    public Cursor getData(String sql) {
//        SQLiteDatabase database = getReadableDatabase();
//        return database.rawQuery(sql, null);
//    }
