package com.zavosh.software.dentist.dentist.Adapters;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.zavosh.software.dentist.dentist.CustomViews.MyTextView;
import com.zavosh.software.dentist.dentist.R;
import com.zavosh.software.dentist.dentist.Retrofit.FilterItemRequest.FilterItem;

import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class FilterAdapter extends ArrayAdapter<FilterItem> {
    private List<FilterItem> list;
    private Context context;
    public FilterAdapter(Context context,  List<FilterItem> objects) {
        super(context, 0, objects);
        list = objects;
        this.context = context;
    }

    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.item_filter, null);
        MyTextView tv_filter = rootView.findViewById(R.id.tv_filter);
        FilterItem filterItem = list.get(position);

        tv_filter.setText(filterItem.getTitle());

        return rootView;
    }
}
