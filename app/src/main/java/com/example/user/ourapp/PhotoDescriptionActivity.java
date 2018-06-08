package com.example.user.ourapp;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PhotoDescriptionActivity extends AppCompatActivity implements View.OnClickListener {

    TextView clickText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_description);

//    getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        clickText = (TextView) findViewById(R.id.nameOf);
        clickText.setOnClickListener(this);

        textView = (TextView) findViewById(R.id.textView);
        textView.setText(getIntent().getStringExtra("description"));

        ImageView imv = findViewById(R.id.circle_profile);
        TextView tvw = findViewById(R.id.textView);

        Bitmap bitmap = BitmapFactory.decodeByteArray(
                getIntent().getByteArrayExtra("Image"), 0, getIntent().getByteArrayExtra("Image").length);
        imv.setImageBitmap(bitmap);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}



