package com.example.user.ourapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.database.sqlite.SQLiteOpenHelper;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {






    Button btnActTwo;
    DBHelper dbHelper;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // db.execSQL("CREATE TABLE IF NOT EXISTS users (name TEXT, age INTEGER)");

        btnActTwo = (Button) findViewById(R.id.button);
        btnActTwo.setOnClickListener(this);

        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

    }

    @Override
    public void onClick(View v) {



        switch (v.getId()) {
            case R.id.button:

                Intent intent = new Intent(this, Main2Activity_Drawer.class);
                startActivity(intent);

                break;
            default:
                break;
        }
    }
}




