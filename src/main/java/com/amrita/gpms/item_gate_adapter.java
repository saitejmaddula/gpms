package com.amrita.gpms;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sumanth on 8/29/2018.
 */

public class item_gate_adapter extends RecyclerView.Adapter<item_gate_adapter.item_gate_viewholder> {
        private Context ctx;
        private List<item_gate> item_gateList;
        static   int b;

    public item_gate_adapter(Context ctx, List<item_gate> item_gateList) {
        this.ctx = ctx;
        this.item_gateList = item_gateList;
    }

    @Override
    public item_gate_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(ctx);
        View view=layoutInflater.inflate(R.layout.item_gatepass,null);
        return new item_gate_viewholder(view,ctx,item_gateList);
    }

    //@SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(item_gate_viewholder holder, int position) {
        item_gate itemGate=item_gateList.get(position);
        if(String.valueOf(itemGate.getAppr()).equals("NOT_APPROVED"))
            holder.apprv.setTextColor(Color.rgb(255,0,0));
        if(String.valueOf(itemGate.getAppr()).equals("HOLD"))
            holder.apprv.setTextColor(Color.rgb(255,255,0));
        holder.regno.setText(String.valueOf(itemGate.getName()));
        holder.apprv.setText(String.valueOf(itemGate.getAppr()));
        holder.gid.setText(String.valueOf(itemGate.getGid()));

    }

    @Override
    public int getItemCount() {

        return item_gateList.size() ;
    }

    public  class item_gate_viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView regno,gid,apprv;
        Context c;
        List<item_gate> lig=new ArrayList<>();
        public item_gate_viewholder(View itemView,Context c,List<item_gate> lig)  {
            super(itemView);
            itemView.setOnClickListener(this);
            this.c=c;
            this.lig=lig;
            regno=(TextView)itemView.findViewById(R.id.tv_user);
            apprv=(TextView)itemView.findViewById(R.id.tv_gid);
            gid=(TextView)itemView.findViewById(R.id.tv_gid1);
        }


        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            item_gate a=this.lig.get(position);
            Intent i=new Intent(c,warden_approve.class);
            b=a.getGid();
            this.c.startActivity(i);
        }
    }
}
