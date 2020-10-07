package com.example.myapplicationtrulala;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.NumberViewHolder> {



    private static final String TAG = Adapter.class.getSimpleName();

    private static int viewHolderCount;

    private Context context;

    private ArrayList<Bitmap> bb;

    private int mNumberItems;

    private ArrayList<Product> products;

    private String [] strings;

    public Adapter(Context context, ArrayList<Product> product, ArrayList <Bitmap> image) {
        mNumberItems = product.size();
        this.context=context;
        this.products=product;
        viewHolderCount = 0;
        bb=image;
    }

    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.number_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);


        viewHolderCount++;

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {

        holder.setDetails(products, position,bb);

    }


    @Override
    public int getItemCount() {

        return mNumberItems;
    }


    class NumberViewHolder extends RecyclerView.ViewHolder {


        TextView viewHolderIndex;
        ImageView imageView;


        public NumberViewHolder(View itemView) {
            super(itemView);

            imageView= itemView.findViewById(R.id.my_image);

            viewHolderIndex = itemView.findViewById(R.id.text_view_holder);
        }


        void setDetails(ArrayList<Product> products, int p,ArrayList<Bitmap> img) {
            viewHolderIndex.setText(products.get(p).getName());
            imageView.setImageBitmap(img.get(p));
        }



    }


}
