package com.example.climate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.example.climate.ui.login.LoginActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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





    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mDatabaseReference.child("Events").addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) { //never called?

//                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                Toast.makeText(MapsActivity.this, "Total Events: " + dataSnapshot.getChildrenCount(), Toast.LENGTH_SHORT);
//                while(items.hasNext()){
//                    DataSnapshot item=items.next();
//                    type=item.child("type").getValue().toString();
//                    lat=Double.valueOf(item.child("lat").getValue().toString());
//                    lon=Double.valueOf(item.child("lon").getValue().toString());
//                    types.add(type);
//                    lats.add(lat);
//                    lons.add(lon);
//                }
//
                //iterate the data and show markers to map
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    type = snapshot.child("type").getValue().toString();
                    lat = Double.valueOf(snapshot.child("lat").getValue().toString());
                    lon = Double.valueOf(snapshot.child("lon").getValue().toString());
                    LatLng mark=new LatLng(lat,lon);

                    mMap.addMarker(new MarkerOptions().position(mark).title(type));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(mark));
                }

//                        mDatabaseReference.child("Events").removeEventListener(this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



//        LatLng mark2=new LatLng(31,105);
//
//        mMap.addMarker(new MarkerOptions().position(mark2).title("test"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(mark2));

//        Context context = getApplicationContext();
//        CharSequence text =
//        int duration = Toast.LENGTH_SHORT;
//
//        Toast toast = Toast.makeText(context, text, duration);
//        toast.show();

    }
//move to upload event activity
    public void upload(View v){
        Intent myIntent = new Intent(this,UpdateForm.class);
        startActivity(myIntent);

    }
    //refresh the map
    public void refresh(View v){
        Intent myIntent = new Intent(this,MapsActivity.class);
        startActivity(myIntent);
    }

    //go to login page

    public void moveToLogin(View view) {
        Intent myIntent = new Intent(this, LoginActivity.class);
        startActivity(myIntent);
    }
}
