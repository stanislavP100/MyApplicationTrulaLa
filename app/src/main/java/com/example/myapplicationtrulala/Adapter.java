package com.example.myapplicationtrulala;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.net.URL;

public class Adapter extends RecyclerView.Adapter<Adapter.NumberViewHolder> {



    private static final String TAG = Adapter.class.getSimpleName();

    private static int viewHolderCount;

    private Context context;

    private Bitmap bb;

    private int mNumberItems;

    private String [] strings;

    public Adapter(Context context, String [] st, Bitmap image) {
        mNumberItems = st.length;
        this.context=context;
        strings=st;
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

        System.out.println(viewHolderCount);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {

        Log.d(TAG, "#" + position);

        holder.setDetails(strings, position, bb);

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

            imageView=(ImageView) itemView.findViewById(R.id.my_image);

            viewHolderIndex = (TextView) itemView.findViewById(R.id.text_view_holder);
        }


        void setDetails(String [] strings, int p, Bitmap img) {
            viewHolderIndex.setText(strings[p]);
            imageView.setImageBitmap(img);
        }



    }


}
