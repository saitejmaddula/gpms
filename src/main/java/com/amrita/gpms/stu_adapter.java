package com.amrita.gpms;

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
 * Created by Sumanth on 9/5/2018.
 */

public class stu_adapter extends RecyclerView.Adapter<stu_adapter.stu_viewholder> {
    private Context ctx;
    private List<item_gate> item_gateList;
    static   int bb;

    public stu_adapter(Context ctx, List<item_gate> item_gateList) {
        this.ctx = ctx;
        this.item_gateList = item_gateList;
    }

    @Override
    public stu_adapter.stu_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(ctx);
        View view=layoutInflater.inflate(R.layout.item_gatepass,null);
        return  new stu_viewholder(view,ctx,item_gateList);
    }

    @Override
    public void onBindViewHolder(stu_adapter.stu_viewholder holder, int position) {
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
        return item_gateList.size();
    }
    public  class stu_viewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView regno,gid,apprv;
        Context c;
        List<item_gate> lig=new ArrayList<>();
        public stu_viewholder(View itemView,Context c,List<item_gate> lig ) {
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
            Intent i=new Intent(c,stu_data.class);
            bb=a.getGid();
            this.c.startActivity(i);
        }
    }
}
