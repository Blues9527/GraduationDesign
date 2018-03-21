package com.blues.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blues.been.Chat;
import com.example.blues.myapplication.R;

import java.util.List;

/**
 * Created by Administrator on 2018/3/15.
 */

public class MultipleItemAdapter extends RecyclerView.Adapter<MultipleItemAdapter.ViewHolder> {

    private final Context context;
    private List<Chat> list;

    public MultipleItemAdapter(Context context, List<Chat> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public MultipleItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_rv, null, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MultipleItemAdapter.ViewHolder holder, int position) {

        holder.pic_chat.setImageResource(list.get(position).getPic_chat());
        holder.title_chat.setText(list.get(position).getTitle_chat());
        holder.context_chat.setText(list.get(position).getContext_chat());
        holder.time_chat.setText(list.get(position).getTime_chat());
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title_chat, context_chat, time_chat;
        ImageView pic_chat;

        public ViewHolder(View itemView) {
            super(itemView);
            title_chat = (TextView) itemView.findViewById(R.id.title_chat);
            context_chat = (TextView) itemView.findViewById(R.id.context_chat);
            time_chat = (TextView) itemView.findViewById(R.id.time_chat);
            pic_chat = (ImageView) itemView.findViewById(R.id.pic_chat);
        }
    }

}
