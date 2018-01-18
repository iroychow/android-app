package com.example.ishitaroychowdhury.finalexam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter2;
    ImageButton add;
    Button travelmap, exit;
    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<FireTrip> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        add=findViewById(R.id.imgbtnadd);
        exit=findViewById(R.id.btnexit);
        travelmap=findViewById(R.id.btntravelmap);
        database= FirebaseDatabase.getInstance();
        myRef=database.getReference("tripsalbum");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,CreateANewAlbumActivity.class);
                startActivity(intent);
            }
        });


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 results = new ArrayList<FireTrip>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    FireTrip rb = ds.getValue(FireTrip.class);
                    rb.key = ds.getKey();
                    results.add(rb);
                    //Log.d("demo", "RESULTS"+ ds.toString());
                    //Log.d("demo", "RESULTS"+ rb.toString());

                    recyclerView =(RecyclerView) findViewById(R.id.rv1);
                    recyclerView.setHasFixedSize(true);


                    //layoutManager = new LinearLayoutManager(MainActivity.this);
                    //layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL,false); //for horizontal layout
                    layoutManager=new GridLayoutManager(MainActivity.this,2); //grid layout

                    recyclerView.setLayoutManager(layoutManager);

                    adapter2= new TripsAlbumAdapter(results);
                    recyclerView.setAdapter(adapter2);

                }
                Log.d("demo", "RESULTS"+ results.toString());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.d("error",databaseError.toString());
            }
        });

travelmap.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent= new Intent(MainActivity.this,MapsActivity.class);
        intent.putExtra("data",results);
        startActivity(intent);
    }
});

exit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        System.exit(0);
    }
});

    }
}
