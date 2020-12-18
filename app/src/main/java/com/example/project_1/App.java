package com.example.project_1;

import android.annotation.SuppressLint;
import android.app.Application;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class App extends Application implements LocationListener {

    private final long LOCATION_REFRESH_TIME = 1; // 1 Minutes
    private final float LOCATION_REFRESH_DISTANCE = 1; // 10 Meters

    public static App instance;
    private ArrayList<Record> recordsList;
    private boolean isLocationEnabled = false;
    private LocationManager locationManager;
    private LatLng currentPosition;

    public App() {}

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    @SuppressLint("MissingPermission")
    public void setLocationStatus(boolean isEnabled){
        this.isLocationEnabled = isEnabled;
        if(isEnabled){
            this.locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            this.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    LOCATION_REFRESH_TIME, LOCATION_REFRESH_DISTANCE, this);
        }
    }

    public boolean getLocationStatus(){
        return this.isLocationEnabled;
    }

    private void setPosition(double lat, double lng){
        this.currentPosition = new LatLng(lat, lng);
    }

    public LatLng getPosition(){
        return this.currentPosition;
    }

    public void saveRecordToSP(Record record) {

        recordsList = getSavedRecordsFromSP();
        if (recordsList != null) {
            if (recordsList.size() < Const.RECORDS_TOP_TEN) {
                recordsList.add(record);
            } else {
                int curScore = record.getScore();

                if (curScore > recordsList.get(recordsList.size() - 1).getScore()) {

                    recordsList.set(recordsList.size() - 1, record);
                }
            }
        }

        sortRecordList();
        Gson gson = new Gson();
        String json = gson.toJson(recordsList);
        SharedPreference.saveString(Const.RECORDS_LIST_KEY, json);
    }

    public ArrayList<Record> getSavedRecordsFromSP() {

        Type type = new TypeToken<ArrayList<Record>>() {
        }.getType();
        Gson gson = new Gson();
        String json = SharedPreference.getSavedString(Const.RECORDS_LIST_KEY);

        if (!json.isEmpty()) {
            recordsList = gson.fromJson(json, type);
        } else {
            recordsList = new ArrayList<>();
        }

        return recordsList;
    }

    public void sortRecordList() {
        Collections.sort(recordsList, new Comparator<Record>() {
            @Override
            public int compare(Record o1, Record o2) {
                return Integer.valueOf(o2.getScore()).compareTo(Integer.valueOf(o1.getScore()));
            }
        });

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        if(this.isLocationEnabled){
            this.setPosition(location.getLatitude(), location.getLongitude());
        }
    }
}