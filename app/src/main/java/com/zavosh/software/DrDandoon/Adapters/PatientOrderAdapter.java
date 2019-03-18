package com.zavosh.software.DrDandoon.Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.zavosh.software.DrDandoon.CustomViews.MyImageView;
import com.zavosh.software.DrDandoon.CustomViews.MyTextView;
import com.zavosh.software.DrDandoon.MyInterfaces.MyClickListener;
import com.zavosh.software.DrDandoon.MyInterfaces.MyDialog;
import com.zavosh.software.DrDandoon.R;
import com.zavosh.software.DrDandoon.Retrofit.PatientOrderListRequest.PatientOrder;

import java.util.List;

public class PatientOrderAdapter extends RecyclerView.Adapter {
    private List<PatientOrder> list ;
    private Activity activity;
    private final int TYPE_HEADER = 0;
    private final int TYPE_FOOTER = 1;
    private final int TYPE_ITEM = 2;
    public MyClickListener myClickListener;

    public PatientOrderAdapter(List<PatientOrder> list, Activity activity) {
        this.list = list;
        this.activity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        //if (viewType == TYPE_HEADER){
        //    View headerItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_history, parent, false);
        //    return new HeaderViewHolder(headerItem);
        //}
        if (viewType == TYPE_ITEM){
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_patient, parent, false);
            return new ItemViewHolder(itemView);
        }
        //if (viewType == TYPE_FOOTER){
        //    View space = LayoutInflater.from(parent.getContext()).inflate(R.layout.space, parent, false);
        //    return new HeaderViewHolder(space);
        //}
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ItemViewHolder){
            ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
            PatientOrder patientOrder = list.get(position);
            itemViewHolder.tv_date.setText(patientOrder.getCreationDate());
            itemViewHolder.tv_toothNumber.setText(patientOrder.getNumber());
            if (Boolean.parseBoolean(patientOrder.getIsAccept())){
                itemViewHolder.tv_status.setText(activity.getString(R.string.accepted));
            }else {
                itemViewHolder.tv_status.setText(activity.getString(R.string.wait));
            }

            itemViewHolder.iv_tooth.setPicasso(patientOrder.getImage());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public MyTextView tv_date,tv_toothNumber,tv_status;
        public MyImageView iv_tooth;
        public LinearLayout ll_item;
        public ItemViewHolder( View itemView) {
            super(itemView);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_toothNumber = itemView.findViewById(R.id.tv_toothNumber);
            tv_date = itemView.findViewById(R.id.tv_date);
            iv_tooth = itemView.findViewById(R.id.iv_tooth);
            ll_item = itemView.findViewById(R.id.ll_item);
            ll_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myClickListener.onMyClickListener(getAdapterPosition());
                }
            });
        }
    }

}
