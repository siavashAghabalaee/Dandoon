package com.zavosh.software.dentist.dentist.CustomViews;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.zavosh.software.dentist.dentist.Content.MyApp;


/**
 * Created by Mr.Asus on 7/21/2018.
 */

public class MyTextView extends AppCompatTextView {


    public MyTextView(Context context) {
        super(context);
        setTypeface(MyApp.appFont);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(MyApp.appFont);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(MyApp.appFont);
    }
}
