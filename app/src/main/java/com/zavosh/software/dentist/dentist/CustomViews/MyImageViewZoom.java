package com.zavosh.software.dentist.dentist.CustomViews;

import android.content.Context;
import android.util.AttributeSet;

import com.squareup.picasso.Picasso;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;

public class MyImageViewZoom extends ImageViewTouch {
    public String imageUrl = "null";

    public MyImageViewZoom(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageViewZoom(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
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
