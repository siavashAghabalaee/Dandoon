package com.zavosh.software.dentist.dentist.Activities.MVP_ImageZoom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zavosh.software.dentist.dentist.Content.Content;
import com.zavosh.software.dentist.dentist.CustomViews.MyImageView;
import com.zavosh.software.dentist.dentist.CustomViews.MyImageViewZoom;
import com.zavosh.software.dentist.dentist.R;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;

public class ZoomImageActivity extends AppCompatActivity {

    private MyImageViewZoom imageZoom;
    private MyImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_image);

        String imageUrl = getIntent().getExtras().getString(Content.IMAGE_KEY);
        imageZoom = findViewById(R.id.imageZoom);
        back = findViewById(R.id.iv_back);

        imageZoom.setPicasso(imageUrl);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
