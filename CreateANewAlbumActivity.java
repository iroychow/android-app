package com.example.ishitaroychowdhury.finalexam;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class CreateANewAlbumActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter2;
    EditText albumnane;
    EditText etdate;
    Calendar calender;
    ImageButton addImagefromlocal;
    Integer REQUEST_CAMERA = 1, SELECT_FILE = 0;
    byte[] byteArray;
    ImageView ivtemp;
    EditText etgeoplacename;
    String finalurl = "";
    ImageButton search;
    Gson gson;
    AsyncHttpClient client;
    GeocodeAPIFormat places;
    GeocodeAPIFormat.ResultsBean.GeometryBean.LocationBean locationbean;
    ArrayList<GeocodeAPIFormat.ResultsBean> arrayList;
    GeocodeAPIFormat.ResultsBean results;
    Double lat, lon;
Button save;
String key="";
Button cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_anew_album);

        albumnane = findViewById(R.id.etalbumname);
        etdate = findViewById(R.id.etdate);
        addImagefromlocal = findViewById(R.id.addimagefromlocal);
        ivtemp = findViewById(R.id.ivtemp);
        etgeoplacename = findViewById(R.id.etgeoplacename);
        search = findViewById(R.id.imgsearch);

        database= FirebaseDatabase.getInstance();
        myRef=database.getReference();
        key=myRef.child("tripsalbum").push().getKey();

        client = new AsyncHttpClient();
        places = new GeocodeAPIFormat();
        locationbean = new GeocodeAPIFormat.ResultsBean.GeometryBean.LocationBean();
        save=findViewById(R.id.btnsave);
        cancel=findViewById(R.id.btncancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(CreateANewAlbumActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        /*recyclerView =(RecyclerView) findViewById(R.id.rv1);
        recyclerView.setHasFixedSize(true);


        //layoutManager = new LinearLayoutManager(MainActivity.this);
        layoutManager = new LinearLayoutManager(CreateANewAlbumActivity.this, LinearLayoutManager.HORIZONTAL,false); //for horizontal layout
        //layoutManager=new GridLayoutManager(CreateANewAlbumActivity.this,2); //grid layout

        recyclerView.setLayoutManager(layoutManager);

        //adapter2= new TripsAlbumAdapter(results);
        recyclerView.setAdapter(adapter2);
*/
        addImagefromlocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalurl = "https://maps.googleapis.com/maps/api/geocode/json?address=" + etgeoplacename.getText() + "&key=AIzaSyAvbUiIXeVbeOo3vttn8hXvZMaZZo0w1RU";
                Log.d("url", finalurl);

                client.get(CreateANewAlbumActivity.this, finalurl, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                        String resp = new String(responseBody);
                        gson= new Gson();
                        places = gson.fromJson(resp, GeocodeAPIFormat.class);
                        arrayList = (ArrayList<GeocodeAPIFormat.ResultsBean>) places.getResults();
                        Log.d("demo", arrayList.toString());
                        //etgeoplacename.setText();

                       /* results= (GeocodeAPIFormat.ResultsBean) places.getResults();
                        lat=results.getGeometry().getLocation().getLat();
                        lon=results.getGeometry().getLocation().getLng();*/

                       for(GeocodeAPIFormat.ResultsBean geocodeAPIFormat: arrayList){
                           etgeoplacename.setText(geocodeAPIFormat.getFormatted_address());
                           lat=geocodeAPIFormat.getGeometry().getLocation().getLat();
                           lon=geocodeAPIFormat.getGeometry().getLocation().getLng();

                       }
                        Toast.makeText(CreateANewAlbumActivity.this, "Recieved location", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });
            }


        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FireTrip firetrip= new FireTrip();
                firetrip.setLat(lat);
                firetrip.setLon(lon);
                firetrip.setAlbumname(albumnane.getText().toString());
                firetrip.setLocationName(etgeoplacename.getText().toString());
                firetrip.setDate(etdate.getText().toString());
                firetrip.setKey(key);

                Map<String,Object> postValues= firetrip.toMap();
                Map<String,Object> childUpdates= new HashMap<>();
                childUpdates.put("tripsalbum/"+key,postValues);
                myRef.updateChildren(childUpdates);

                Toast.makeText(CreateANewAlbumActivity.this, "Data saved in Firebase", Toast.LENGTH_SHORT).show();

            }
        });

        calender = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                calender.set(Calendar.YEAR, year);
                calender.set(Calendar.MONTH, monthOfYear);
                calender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                date1();
            }

        };

        etdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datepickerdialog = new DatePickerDialog(CreateANewAlbumActivity.this, date, calender
                        .get(Calendar.YEAR), calender.get(Calendar.MONTH),
                        calender.get(Calendar.DAY_OF_MONTH));
                Calendar c = Calendar.getInstance();
                c.set(1850, 1, 1);
                datepickerdialog.getDatePicker().setMinDate(c.getTimeInMillis());
                datepickerdialog.show();
            }
        });


        etdate.setFocusable(false);

    }

    private void date1() {
        String format = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.US);
        etdate.setText(simpleDateFormat.format(calender.getTime()));
    }

    public void selectImage() {
        final CharSequence[] items = {"Camera", "Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(CreateANewAlbumActivity.this);
        builder.setTitle("Add Image");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (items[i].equals("Camera")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[i].equals("Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent.createChooser(intent, "Select File"), SELECT_FILE);
                } else if (items[i].equals("Cancel")) {
                    dialogInterface.dismiss();
                }

            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {

                Bundle bundle = data.getExtras();
                final Bitmap bmp = (Bitmap) bundle.get("data");
                ivtemp.setImageBitmap(bmp);

                //convert bitmap to byte
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byteArray = stream.toByteArray();
                Log.d("demo", byteArray.toString());
            }

            //gallery
            else if (requestCode == SELECT_FILE) {
                Uri selectedImageUri = data.getData();
                ivtemp.setImageURI(selectedImageUri);
                //convert URI to byte array
                InputStream iStream = null;
                try {
                    iStream = getContentResolver().openInputStream(selectedImageUri);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    byte[] inputData = getBytes(iStream);
                    byteArray = inputData;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

}
