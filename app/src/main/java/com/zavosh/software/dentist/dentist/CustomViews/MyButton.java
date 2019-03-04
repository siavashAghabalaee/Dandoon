package com.zavosh.software.dentist.dentist.CustomViews;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.zavosh.software.dentist.dentist.Content.MyApp;

public class MyButton extends AppCompatButton {
    public MyButton(Context context) {
        super(context);
        setTypeface(MyApp.appFont);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(MyApp.appFont);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(MyApp.appFont);
    }
}
