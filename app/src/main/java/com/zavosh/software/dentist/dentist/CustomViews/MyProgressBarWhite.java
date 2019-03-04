package com.zavosh.software.dentist.dentist.CustomViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.zavosh.software.dentist.dentist.R;

public class MyProgressBarWhite extends ProgressBar {

    public MyProgressBarWhite(Context context) {
        super(context);
        getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.white), android.graphics.PorterDuff.Mode.SRC_ATOP);
    }

    public MyProgressBarWhite(Context context, AttributeSet attrs) {
        super(context, attrs);
        getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.white), android.graphics.PorterDuff.Mode.SRC_ATOP);
    }

    public MyProgressBarWhite(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.white), android.graphics.PorterDuff.Mode.SRC_ATOP);
    }

}
