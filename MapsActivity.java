package com.example.ishitaroychowdhury.finalexam;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    LocationListener locationListenerCustom;

    private GoogleMap mMap;
    ArrayList<FireTrip> places;
    LocationManager locationManager;
    PolylineOptions polylineOptions;
    LatLngBounds.Builder builder = null;
    Boolean flag = false, endFlag = false;
    LatLngBounds bounds;

    UiSettings uiSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().containsKey("data")) {
                places = (ArrayList<FireTrip>) getIntent().getSerializableExtra("data");
                Log.d("data for map", places.toString());
            } else {
                Log.d("demo", "Error");
            }
        } else {
            Log.d("demo", "Error");
        }




        if(places==null){
            Toast.makeText(this, "No Places to View!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        if(places.size()==0){
            Toast.makeText(this, "No Places to View!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }


        //to get location
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        //builder
        builder = new LatLngBounds.Builder();

        //polyline
        polylineOptions = new PolylineOptions();


        locationListenerCustom = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //to get lat and long
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                LatLng latLng1 = new LatLng(latitude, longitude);
                polylineOptions.add(latLng1);

                Geocoder geocoder = new Geocoder(getApplicationContext());
                try {
                    List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                    String str = addressList.get(0).getLocality() + " , ";
                    str += addressList.get(0).getCountryName();

                    mMap.addMarker(new MarkerOptions().position(latLng1).title(str));

                } catch (IOException e) {
                    e.printStackTrace();
                }

                for(FireTrip p:places){
                    LatLng latLng2 = new LatLng(p.getLat(), p.getLon());
                    polylineOptions.add(latLng2);
                    builder.include(latLng2);
                    mMap.addMarker(new MarkerOptions().position(latLng2).title(p.getLocationName()));
                }


                polylineOptions.add(latLng1);
                builder.include(latLng1);

                bounds = builder.build();



                mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 20));
                Polyline polyline = mMap.addPolyline(polylineOptions);

                locationManager.removeUpdates(locationListenerCustom);

            }


            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
            }

            @Override
            public void onProviderEnabled(String s) {
            }

            @Override
            public void onProviderDisabled(String s) {
            }
        };


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mMap.setMyLocationEnabled(true);

        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {

            Toast.makeText(MapsActivity.this, "Stopped location tracking", Toast.LENGTH_SHORT).show();
            if (ActivityCompat.checkSelfPermission(MapsActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MapsActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListenerCustom);
            flag = false;
            endFlag = false;

        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            Toast.makeText(MapsActivity.this, "Stopped location tracking", Toast.LENGTH_SHORT).show();
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListenerCustom);
            flag = false;
            endFlag = false;
        }
    }
}
