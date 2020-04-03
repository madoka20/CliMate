package com.example.climate;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.sql.Timestamp;

public class UpdateForm extends AppCompatActivity {
    public FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    public DatabaseReference mDatabaseReference = mDatabase.getReference();
    public int userid=1;
    public String eventid;
    public String type;

    public Timestamp time;
    public int dangerlevel=1;


    public TextView latTextView;
    public TextView lonTextView;
    public double lat;
    public double lon;
    public String description;
    public TextView destv;
    int PERMISSION_ID = 44;
    FusedLocationProviderClient mFusedLocationClient;
    Spinner dropdown1;
    Spinner dropdown2;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_form);

        latTextView=(TextView) findViewById(R.id.lat);
        lonTextView=(TextView) findViewById(R.id.lon);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();



        //time
        time=new Timestamp(System.currentTimeMillis());
        int date=time.getDate();
        int hour=time.getHours();
        int minute=time.getMinutes();
        int second=time.getSeconds();
        eventid=Integer.toString(date)+Integer.toString(hour)+Integer.toString(minute)+Integer.toString(second);
        //type selector
        dropdown1 = findViewById(R.id.type_selector);
        String[] items1 = new String[]{"Select type...","Snow storm", "Earthquake", "Volcano eruption"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        dropdown1.setAdapter(adapter1);

        //danger level selector
        dropdown2 = findViewById(R.id.danger_level_selector);
        String[] items2 = new String[]{"1", "2", "3","4","5"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown2.setAdapter(adapter2);

        destv=findViewById(R.id.description);
    }

    public void upload_event(View v){


        type=dropdown1.getSelectedItem().toString();

        dangerlevel=Integer.parseInt(dropdown2.getSelectedItem().toString()) ;

        description=destv.getText().toString();

        Events event=new Events(type,lat,lon,time,dangerlevel,description);
        mDatabaseReference.child("Events").child(eventid).setValue(event);

        Intent myIntent = new Intent(this,MapsActivity.class);
        myIntent.putExtra("lat",lat);
        myIntent.putExtra("lon",lon);
        myIntent.putExtra("type",type);
        startActivity(myIntent);

        Context context = getApplicationContext();
        CharSequence text = "Update Successfully!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }



    public void cancel(View v){
        Intent myIntent = new Intent(this,MapsActivity.class);
        startActivity(myIntent);
    }




//get location functions
@SuppressLint("MissingPermission")
private void getLastLocation(){
    if (checkPermissions()) {
        if (isLocationEnabled()) {
            mFusedLocationClient.getLastLocation().addOnCompleteListener(
                    new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            Location location = task.getResult();
                            if (location == null) {
                                requestNewLocationData();
                            } else {
                                latTextView.setText(location.getLatitude()+"");
                                lonTextView.setText(location.getLongitude()+"");
                                lat=location.getLatitude();
                                lon=location.getLongitude();
                            }
                        }
                    }
            );
        } else {
            Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }
    } else {
        requestPermissions();
    }
}


    @SuppressLint("MissingPermission")
    private void requestNewLocationData(){

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );

    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            latTextView.setText(mLastLocation.getLatitude()+"");
            lonTextView.setText(mLastLocation.getLongitude()+"");
        }
    };

    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if (checkPermissions()) {
            getLastLocation();
        }

    }

}
