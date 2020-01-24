package com.aad.category;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ServiseAdepter extends RecyclerView.Adapter<ServiseAdepter.ViewHolder>{

    Context mContext;
    List<ServicePojo> mArray;

    public ServiseAdepter(Context cxt, List<ServicePojo> mArray){
        this.mContext = cxt;
        this.mArray = mArray;
    }

    public  static class ViewHolder extends RecyclerView.ViewHolder{

        TextView text;
        public ViewHolder(View v){
            super(v);

            text= (TextView)v.findViewById(R.id.text);

        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.text.setText(mArray.get(position).getServicesName());

    }

    @Override
    public int getItemCount()
    {
        return mArray.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mRowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_service_pojo, parent, false);
        ViewHolder vh = new ViewHolder(mRowView);

        return vh;
    }
}