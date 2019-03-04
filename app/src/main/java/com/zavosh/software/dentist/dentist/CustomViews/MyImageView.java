package com.zavosh.software.dentist.dentist.CustomViews;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.squareup.picasso.Picasso;

public class MyImageView extends AppCompatImageView {
    public String imageUrl = "null";
    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setPicasso (String url){
        imageUrl = url;
        Picasso.get().load(url).into(this);
    }
    public void setPicasso (String url , int placeholder , int error ){
        imageUrl = url;
        Picasso.get().load(url).placeholder(placeholder).error(error).into(this);
    }
}
