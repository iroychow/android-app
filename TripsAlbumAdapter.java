package com.example.ishitaroychowdhury.finalexam;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by ishitaroychowdhury on 12/11/17.
 */

////////////////////////////RV with checkbox and two textviews
public class TripsAlbumAdapter extends RecyclerView.Adapter<TripsAlbumAdapter.ViewHolder> {


    ArrayList<FireTrip> mdata;
    FirebaseDatabase database;
    DatabaseReference myRef;

    public TripsAlbumAdapter(ArrayList<FireTrip> mdata) {
this.mdata=mdata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv1_items, parent, false);   //inflate xml file
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        FireTrip firetrip= mdata.get(position);
        database= FirebaseDatabase.getInstance();
        myRef=database.getReference("tripsalbum").child(firetrip.getKey());
       // myRef=myRef.getDatabase().getReference("tripsalbum").child(firetrip.getKey());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });

        holder.locationname.setText(firetrip.getLocationName());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mdata.remove(position);
                myRef.removeValue();
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mdata.size());

            }
        });


    }

    @Override
    public int getItemCount() {

        return mdata.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView locationname;
        ImageButton delete;


        public ViewHolder(View itemView) {
            super(itemView);

            locationname= itemView.findViewById(R.id.rv1placename);
            delete=itemView.findViewById(R.id.rv1btndelete);

        }


    }


}


