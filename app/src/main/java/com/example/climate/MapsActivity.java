package com.example.climate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    public FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    public DatabaseReference mDatabaseReference = mDatabase.getReference();
    private GoogleMap mMap;
    private Button submitBtn;

    public double lat;
    public double lon;
    public String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        submitBtn=findViewById(R.id.submit);

        }
    ArrayList<Double> lats = new ArrayList<Double>();
    ArrayList<Double> lons = new ArrayList<Double>();
    ArrayList<String> types = new ArrayList<String>();

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Intent intent=getIntent();
        lats.add(intent.getDoubleExtra("lat",0.0));
        lons.add(intent.getDoubleExtra("lon",0.0));
        types.add(intent.getStringExtra("type"));


        mMap = googleMap;
        for(int i=0;i<lats.size();i++){
            LatLng mark=new LatLng(lats.get(i),lons.get(i));
            type= types.get(i);
            mMap.addMarker(new MarkerOptions().position(mark).title(type));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(mark));
        }
        LatLng mark2=new LatLng(31,105);

        mMap.addMarker(new MarkerOptions().position(mark2).title("test"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mark2));

//        Context context = getApplicationContext();
//        CharSequence text =
//        int duration = Toast.LENGTH_SHORT;
//
//        Toast toast = Toast.makeText(context, text, duration);
//        toast.show();


    }

    public void upload(View v){
        Intent myIntent = new Intent(this,UpdateForm.class);
        startActivity(myIntent);

    }
    public void refresh(View v){
        Intent myIntent = new Intent(this,MapsActivity.class);
        startActivity(myIntent);
    }

}
