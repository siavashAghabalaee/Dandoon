package com.zavosh.software.dentist.dentist.CustomViews;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.AttributeSet;

import com.zavosh.software.dentist.dentist.Content.MyApp;

public class MyCheckBox extends AppCompatCheckBox {


    public MyCheckBox(Context context) {
        super(context);
        setTypeface(MyApp.appFont);
    }

    public MyCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(MyApp.appFont);
    }

    public MyCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(MyApp.appFont);
    }
}
