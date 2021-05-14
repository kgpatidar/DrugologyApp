package com.example.drugology;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.security.PublicKey;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class adapter_recyclerview extends BaseAdapter {
    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        View view =  LayoutInflater.from().inflate(R.layout.carddesign, parent,false);
        return null;
    }

//    String data1[];
//    Context context;
//    public adapter_recyclerview(Context ct,String s1[]) {
//        context = ct;
//        data1=s1;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//       View view =  inflater.inflate(R.layout.carddesign,parent,false);
//
//        return new MyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        holder.mytext1.setText(data1[position]);
//    }
//
//    @Override
//    public int getItemCount() {
//        return data1.length;
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder{
//
//        TextView mytext1;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            mytext1 = itemView.findViewById(R.id.mytext1);
//        }
//    }
}
