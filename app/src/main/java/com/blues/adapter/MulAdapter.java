package com.blues.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blues.been.IteractionEntity;
import com.example.blues.myapplication.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Administrator on 2018/3/19.
 */

public class MulAdapter extends RecyclerView.Adapter<MulAdapter.ViewHolder> {

    private Context mContext;
    private List<IteractionEntity> list;

    public MulAdapter(Context context, List<IteractionEntity> list) {
        this.mContext = context;
        this.list = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_iteraction_rv, null, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MulAdapter.ViewHolder holder, int position) {
        holder.pic_iteaction.setImageResource(list.get(position).getPic_iteraction());
        holder.title_iteraction.setText(list.get(position).getTitle_iteraction());
        holder.context_iteraction.setText(list.get(position).getContext_iteraction());
        holder.view_iteraction.setImageResource(list.get(position).getView_iteraction());
        holder.time_iteraction.setText(list.get(position).getTime_iteraction());
        holder.soucre_iteraction.setText(list.get(position).getSource_iteraction());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title_iteraction, context_iteraction, time_iteraction, soucre_iteraction;
        ImageView pic_iteaction, view_iteraction;

        public ViewHolder(View itemView) {
            super(itemView);
            title_iteraction = itemView.findViewById(R.id.title_iteraction);
            context_iteraction = itemView.findViewById(R.id.context_iteraction);
            time_iteraction = itemView.findViewById(R.id.time_iteraction);
            soucre_iteraction = itemView.findViewById(R.id.source_iteraction);

            pic_iteaction = itemView.findViewById(R.id.pic_iteraction);
            view_iteraction = itemView.findViewById(R.id.view_iteraction);
        }
    }
}
