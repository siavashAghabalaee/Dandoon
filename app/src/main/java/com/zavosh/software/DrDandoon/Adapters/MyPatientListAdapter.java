package com.zavosh.software.DrDandoon.Adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zavosh.software.DrDandoon.CustomViews.MyButton;
import com.zavosh.software.DrDandoon.CustomViews.MyImageView;
import com.zavosh.software.DrDandoon.CustomViews.MyTextView;
import com.zavosh.software.DrDandoon.MyInterfaces.MyClickListener;
import com.zavosh.software.DrDandoon.MyInterfaces.MyDialog;
import com.zavosh.software.DrDandoon.R;
import com.zavosh.software.DrDandoon.Retrofit.MyPatientListRequest.MyPatientListResult;

import java.util.List;

public class MyPatientListAdapter extends RecyclerView.Adapter {
    List<MyPatientListResult> list ;
    private Activity activity;
    private final int TYPE_HEADER = 0;
    private final int TYPE_FOOTER = 1;
    private final int TYPE_ITEM = 2;
    public MyClickListener myClickListener;
    public MyDialog myDialog;

    public MyPatientListAdapter(List<MyPatientListResult> list, Activity activity) {
        this.list = list;
        this.activity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //if (viewType == TYPE_HEADER){
        //    View headerItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_history, parent, false);
        //    return new HeaderViewHolder(headerItem);
        //}
        if (viewType == TYPE_ITEM){
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_patient, parent, false);
            return new ItemViewHolder(itemView);
        }
        //if (viewType == TYPE_FOOTER){
        //    View space = LayoutInflater.from(parent.getContext()).inflate(R.layout.space, parent, false);
        //    return new HeaderViewHolder(space);
        //}
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ItemViewHolder){
            ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
            MyPatientListResult patientItemResult = list.get(position);
            itemViewHolder.tv_ageType.setText(patientItemResult.getKidStatus());
            itemViewHolder.tv_group.setText(patientItemResult.getTypeTitle());
            itemViewHolder.tv_toothNumber.setText(patientItemResult.getNumber());
            itemViewHolder.iv_tooth.setPicasso(patientItemResult.getImage());
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
        public MyTextView tv_ageType, tv_toothNumber , tv_group;
        public MyImageView iv_tooth;
        public LinearLayout ll_item;
        public MyButton btn_detail,btn_info;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_ageType = itemView.findViewById(R.id.tv_ageType);
            tv_toothNumber = itemView.findViewById(R.id.tv_toothNumber);
            tv_group = itemView.findViewById(R.id.tv_group);
            iv_tooth = itemView.findViewById(R.id.iv_tooth);
            ll_item = itemView.findViewById(R.id.ll_item);
            btn_detail = itemView.findViewById(R.id.btn_detail);
            btn_info = itemView.findViewById(R.id.btn_info);

            btn_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myClickListener.onMyClickListener(getAdapterPosition());
                }
            });
            btn_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyPatientListResult myPatientListResult = list.get(getAdapterPosition());
                    myDialog.showDialog(myPatientListResult.getCustomerName(),myPatientListResult.getCustomerCellNumber());
                }
            });
        }
    }
}
