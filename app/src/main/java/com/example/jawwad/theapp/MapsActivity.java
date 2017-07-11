package com.example.jawwad.theapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.icu.text.SimpleDateFormat;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Date;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    LatLng myCoordinates;
    Location location;
    LocationManager locationManager;
    MarkerOptions no;
    Marker marker;
    final static int PERMISSION_ALL = 1;
    final static String[] PERMISSIONS = {Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        no = new MarkerOptions().position(new LatLng(53,9)).title("You are Here!");
//        mMap.addMarker(no);
        if (Build.VERSION.SDK_INT >= 23 && !isPermissionGranted()) {
            requestPermissions(PERMISSIONS, PERMISSION_ALL);
        } else requestLocation();
        if (!isLocationEnabled())
            showAlert(1);


    }


    @Override
    protected void onResume() {
        super.onResume();

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
        // Add a marker in Sydney and move the camera
        LatLng fh_C8 = new LatLng(54.331983, 10.180422);
        mMap.addMarker(new MarkerOptions().position(fh_C8).title("Building C08 FH kiel"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(fh_C8));

        LatLng fh_C12 = new LatLng(54.330452, 10.179516);
        mMap.addMarker(new MarkerOptions().position(fh_C12).title("Building C12 FH kiel"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(fh_C12));

        LatLng fh_C13 = new LatLng(54.330389, 10.179988);
        mMap.addMarker(new MarkerOptions().position(fh_C13).title("Building C13 FH kiel"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fh_C13, 17));
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String markerid = marker.getId();
                Log.wtf("mylog", " MARKER_ID_hoi______________"+markerid);
                if (markerid.equals("m2")){
                    Intent i = new Intent(MapsActivity.this, BuildingInfo.class);
                    Log.wtf("mylog", " intent craeated");
                    i.putExtra("markerid", markerid);
                    Log.wtf("mylog", " putExtra Done");
                    startActivity(i);
                }
                else {
                    Toast myToast = Toast.makeText(getApplicationContext(),"Sorry No data available for this building yet!", Toast.LENGTH_LONG);
                    myToast.show();
                }
                return false;
            }
        });

    }

    @Override
    public void onLocationChanged(Location location) {
        myCoordinates = new LatLng(location.getLatitude(), location.getLongitude());
        Log.wtf("MY LOCATIIIIIOOOONNNN_________", location.getLatitude()+" "+ location.getLongitude());
        marker.setPosition(myCoordinates);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myCoordinates));

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    private void requestLocation() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_HIGH);
        String provider = locationManager.getBestProvider(criteria, true);
        locationManager.requestLocationUpdates(provider, 10000, 10, this);
    }


    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

//    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean isPermissionGranted() {
        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
           checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Log.v("mylog", "Permission is granted");
            return true;
        } else {
            Log.v("mylog", "Permission is Denied");
            return false;
        }
    }

    private void showAlert(final int status){
        String message, title, btnText;
        if (status == 1){
            message = "Please Enable Location settings";
            title = "Enable Location";
        }
    }

}
