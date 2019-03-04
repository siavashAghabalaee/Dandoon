package com.zavosh.software.dentist.dentist.CustomViews;

import android.content.Context;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.zavosh.software.dentist.dentist.Content.MyApp;

public class MyRadioButton extends AppCompatRadioButton {

    public MyRadioButton(Context context) {
        super(context);
        setTypeface(MyApp.appFont);
    }

    public MyRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(MyApp.appFont);
    }

    public MyRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(MyApp.appFont);
    }
}
