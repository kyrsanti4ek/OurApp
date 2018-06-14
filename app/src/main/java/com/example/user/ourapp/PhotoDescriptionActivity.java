package com.example.user.ourapp;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PhotoDescriptionActivity extends AppCompatActivity {

    TextView textView;
    ImageView imv, view;


    public static SQLiteOpenHelper sqLiteOpenHelper;


    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_description);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textView = (TextView) findViewById(R.id.textView);
        textView.setText(getIntent().getStringExtra("description"));
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));

        imv = findViewById(R.id.circle_profile);
        TextView tvw = findViewById(R.id.textView);
        view = (ImageView) findViewById(R.id.view);


        Bitmap bitmap = BitmapFactory.decodeByteArray(
                getIntent().getByteArrayExtra("Image"), 0, getIntent().getByteArrayExtra("Image").length);
        imv.setImageBitmap(bitmap);
        view.setImageBitmap(bitmap);


    }

//    private void init() {
//        imv = findViewById(R.id.circle_profile);
//        TextView tvw = findViewById(R.id.textView);
//        view = (ImageView) findViewById(R.id.view);
//    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

}

