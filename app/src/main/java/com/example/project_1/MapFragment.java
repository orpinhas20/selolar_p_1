package com.example.project_1;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment implements OnMapReadyCallback, OnItemClickCallback {

    private View view;
    private MapView mapView;
    private GoogleMap map;
    private LocationManager locationManager;
    private Marker currentMarker;

    public MapFragment(){ }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Bind the fragment class to the fragment layout:
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        this.mapView = (MapView) view.findViewById(R.id.top_MAP);
        this.mapView.onCreate(savedInstanceState);
        this.mapView.onResume();

        // Register callback when the map ready:
        this.mapView.getMapAsync(this);

        this.view = view;
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
        this.map.getUiSettings().setZoomControlsEnabled(true);
        this.map.getUiSettings().setCompassEnabled(true);
        this.updateMap(App.instance.getPosition());
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        this.mapView.onLowMemory();
    }

    private void updateMap(LatLng newPosition){
        if(newPosition != null) {
            // Remove existing marker if there is:
            if (this.currentMarker != null) {
                this.currentMarker.remove();
            }

            // Add new marker to the map:
            this.currentMarker = this.map
                    .addMarker(new MarkerOptions()
                            .position(newPosition));
            this.map.moveCamera(CameraUpdateFactory.newLatLng(newPosition));

            // Zoom to the location:
            this.map.animateCamera(CameraUpdateFactory.zoomTo(16), 1000, null);
        }
    }

    @Override
    public void displayLocation(LatLng position) {
        updateMap(position);
    }
}


