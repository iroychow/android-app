package com.example.ishitaroychowdhury.finalexam;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by ishitaroychowdhury on 12/11/17.
 */

public class RV2Adapter extends RecyclerView.Adapter<RV2Adapter.ViewHolder> {

    ArrayList<Bitmap> mdata;

    public RV2Adapter(ArrayList<Bitmap> mdata) {
this.mdata=mdata;
    }

    @Override
    public RV2Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv2_items, parent, false);   //inflate xml file
        RV2Adapter.ViewHolder viewHolder = new RV2Adapter.ViewHolder(view);
        return viewHolder;

    }


    @Override
    public void onBindViewHolder(RV2Adapter.ViewHolder holder, int position) {



    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView rv2;
        ImageButton del;

        public ViewHolder(View itemView) {
            super(itemView);

            rv2=itemView.findViewById(R.id.rv2imageview);
            del=itemView.findViewById(R.id.rv2deletebtn);
        }


    }


}


