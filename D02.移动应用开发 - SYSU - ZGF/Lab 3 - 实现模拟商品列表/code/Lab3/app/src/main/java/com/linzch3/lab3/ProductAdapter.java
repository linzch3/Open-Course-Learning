package com.linzch3.lab3;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by linzch3 on 2017/10/21.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<Product> mProductList;
    private OnItemClickListener mOnItemClickListener;

    public  ProductAdapter(List<Product> productList){
        mProductList = productList;
    }

    /**********实现RecyclerView子项点击需自行实现的函数**********/
    public interface OnItemClickListener{
        /*定义点击和长按的方法，在子类中实现*/
        void onClick(int position);
        void onLongClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        /*设置监听器*/
        this.mOnItemClickListener = onItemClickListener;
    }

    public void removeItem(int position){
        /*在商品列表中删除下标为position的商品*/
        mProductList.remove(position);
        notifyItemRemoved(position);
    }
    /**********实现RecyclerView子项点击需自行实现的函数**********/

    /**********继承RecyclerView.Adapter必须重写的三个方法**********/
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /*创建ViewHolder实例*/
       View view = LayoutInflater.from(parent.getContext())
                                 .inflate(R.layout.product_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        /*对RecyclerView子项的数据进行赋值，在每个子项被滚动到屏幕内执行*/
        Product product = mProductList.get(position);
        holder.productName.setText(product.getName());
        holder.productName_firstLetter.setText(product.getName_firstLetter());
        if(mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    mOnItemClickListener.onClick(holder.getAdapterPosition());
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onLongClick(holder.getAdapterPosition());
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        /*得到RecyclerView的子项个数*/
        return mProductList.size();
    }
    /**********继承RecyclerView.Adapter必须重写的三个方法**********/

    /**********ViewHolder静态子类实现**********/
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView productName;
        TextView productName_firstLetter;

        public ViewHolder(View view){
            super(view);
            productName = (TextView)view.findViewById(R.id.product_name);
            productName_firstLetter = (TextView)view.findViewById(R.id.product_name_firstLetter);
        }
    }
    /**********ViewHolder静态子类实现**********/
}
