package com.example.user.ourapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PhotoDescriptionActivity extends AppCompatActivity {

    TextView clickText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_description);

//        clickText = (TextView) findViewById(R.id.textViewTitle);
//        clickText.setOnClickListener(this);
//
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.textViewTitle:
//                Intent intent = new Intent(this, PhotoDescriptionActivity.class);
//                startActivity(intent);
//                break;
//            default:
//                break;
//        }
//    }
//}
        ImageView imv = findViewById(R.id.circle_profile);
        Bitmap bitmap = BitmapFactory.decodeByteArray(
                getIntent().getByteArrayExtra("Image"), 0, getIntent().getByteArrayExtra("Image").length);
        imv.setImageBitmap(bitmap);
    }
}

