package com.linzch3.lab9.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linzch3.lab9.R;
import com.linzch3.lab9.model.Github;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linzch3 on 17/12/19.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private OnRecyclerViewItemClickListener mItemClickListener = null;
    private List<Github> mUserList = new ArrayList<>();

    /**********实现RecyclerView子项点击需自行实现的函数**********/
    public interface OnRecyclerViewItemClickListener {
        /*定义点击和长按的方法，在子类中实现*/
        void onClick(int position);
        void onLongClick(int position);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener onItemClickListener) {
        this.mItemClickListener = onItemClickListener; /*设置监听器*/
    }

    public void appendItem(Github github)
    {
        mUserList.add(github);
        notifyDataSetChanged();
    }

    public Github getItem(int position) {
        return mUserList.get(position);
    }

    public void removeItem(int position) {
        mUserList.remove(position);
        notifyItemRemoved(position);
    }

    public void removeAllItem() {
        mUserList.clear();
        notifyDataSetChanged();
    }

    /**********实现RecyclerView子项点击需自行实现的函数**********/

    /**********继承RecyclerView.Adapter必须重写的三个方法**********/
    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                if(mItemClickListener != null)
                    mItemClickListener.onClick(holder.getAdapterPosition());
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v)
            {
                if(mItemClickListener != null)
                    mItemClickListener.onLongClick(holder.getAdapterPosition());
                return false;
            }
        });
        return holder;
    }


    @Override
    public void onBindViewHolder(final CardAdapter.ViewHolder holder, int position) {
        Github github = mUserList.get(position);
        holder.login.setText(github.getLogin());
        holder.id.setText(String.valueOf(github.getId()));
        holder.blog.setText(github.getBlog());
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }
    /**********继承RecyclerView.Adapter必须重写的三个方法**********/

    /**********ViewHolder静态子类实现**********/
    static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView id, login, blog;

        ViewHolder(View view)
        {
            super(view);
            login = view.findViewById(R.id.user_name);
            id = view.findViewById(R.id.user_id);
            blog = view.findViewById(R.id.user_blog);
        }
    }
    /**********ViewHolder静态子类实现**********/
}
