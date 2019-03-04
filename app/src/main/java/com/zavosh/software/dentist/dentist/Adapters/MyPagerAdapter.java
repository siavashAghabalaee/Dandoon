package com.zavosh.software.dentist.dentist.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zavosh.software.dentist.dentist.CustomViews.MyImageView;
import com.zavosh.software.dentist.dentist.R;

import java.util.List;

public class MyPagerAdapter extends PagerAdapter  {
    private List<String> imageList;
    private LayoutInflater inflater;
    private Context context;


    public MyPagerAdapter(Context context,List<String> images) {
        this.context = context;
        this.imageList=images;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
        //return IMAGES.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.item_pager_header, view, false);

        assert imageLayout != null;
        final MyImageView imageView = imageLayout
                .findViewById(R.id.image);


        //imageView.setImageResource(IMAGES.get(position%IMAGES.size()));
        imageView.setPicasso(imageList.get(position%imageList.size()));

        view.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

}
