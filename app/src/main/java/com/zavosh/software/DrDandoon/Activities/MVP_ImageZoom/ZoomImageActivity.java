package com.zavosh.software.DrDandoon.Activities.MVP_ImageZoom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zavosh.software.DrDandoon.Content.Content;
import com.zavosh.software.DrDandoon.CustomViews.MyImageView;
import com.zavosh.software.DrDandoon.CustomViews.MyImageViewZoom;
import com.zavosh.software.DrDandoon.R;

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

        imageZoom.setPicasso(imageUrl,ZoomImageActivity.this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
