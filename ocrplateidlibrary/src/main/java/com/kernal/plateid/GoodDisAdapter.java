package com.kernal.plateid;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kernal.plateid.interfacee.ListItemClickListener;
import com.kernal.plateid.my.Good;

import java.util.ArrayList;

public class GoodDisAdapter extends RecyclerView.Adapter<GoodDisAdapter.GoodDisViewHolder> {
    private ArrayList<Good> mGoods;
    private ListItemClickListener listItemClickListener;

    public GoodDisAdapter(ArrayList<Good> goods, ListItemClickListener listener){
        mGoods=goods;
        listItemClickListener=listener;
    }
    public void remove(int position){
        mGoods.remove(position);
        notifyDataSetChanged();
    }
    public String getName(int position){
        return mGoods.get(position).getName();
    }
    @Override
    public GoodDisViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext())
                .inflate(R.layout.distribution_form_item,parent,false);
        GoodDisViewHolder viewHolder=new GoodDisViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GoodDisViewHolder holder, int position) {
        holder.mWarehouse.setText(mGoods.get(position).getWarehouse());
        holder.mName.setText(mGoods.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mGoods.size();
    }

    class GoodDisViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mName,mWarehouse;
        public GoodDisViewHolder(View itemView) {
            super(itemView);
            mName=(TextView)itemView.findViewById(R.id.tv_name);
            mWarehouse=(TextView)itemView.findViewById(R.id.tv_warehouse);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listItemClickListener.onListItemClick(getAdapterPosition());
        }
    }
}
