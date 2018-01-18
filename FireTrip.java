package com.example.ishitaroychowdhury.finalexam;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ishitaroychowdhury on 12/11/17.
 */

public class FireTrip implements Serializable {
    String albumname;
    String date;
    String locationName;
    Double lat,lon;
    String key;



    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("albumnane", albumname);
        result.put("locationName", locationName);
        result.put("lat", lat);
        result.put("date",date);
        result.put("lon", lon);
        result.put("key", key);

        return result;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    @Override
    public String toString() {
        return "FireTrip{" +
                "albumname='" + albumname + '\'' +
                ", date=" + date +
                ", locationName='" + locationName + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", key='" + key + '\'' +
                '}';
    }

    public String getAlbumname() {
        return albumname;
    }

    public void setAlbumname(String albumname) {
        this.albumname = albumname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String  date) {
        this.date = date;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}
