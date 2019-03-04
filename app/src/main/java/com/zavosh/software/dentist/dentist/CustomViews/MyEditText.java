package com.zavosh.software.dentist.dentist.CustomViews;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import com.zavosh.software.dentist.dentist.Content.MyApp;


/**
 * Created by Mr.Asus on 6/27/2018.
 */

public class MyEditText extends AppCompatEditText {
    public MyEditText(Context context) {
        super(context);
        setTypeface(MyApp.appFont);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(MyApp.appFont);
    }

    public String text(){
        return filtered();
    }

    public String filtered(){
        if (getText() != null) {
            return getText().toString()
                    .replace("۰","0")
                    .replace("۱","1")
                    .replace("۲","2")
                    .replace("۳","3")
                    .replace("۴","4")
                    .replace("۵","5")
                    .replace("۶","6")
                    .replace("۷","7")
                    .replace("۸","8")
                    .replace("۹","9")
                    .trim();
        }else{
            return "";
        }
    }

}
