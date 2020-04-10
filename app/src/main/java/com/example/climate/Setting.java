package com.example.climate;

import androidx.appcompat.app.AppCompatActivity;
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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.climate.ui.login.LoginActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.sql.Timestamp;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.os.Bundle;

public class Setting extends AppCompatActivity {
    public FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    public DatabaseReference mDatabaseReference = mDatabase.getReference();
    int maxevents;
    int area;
    String mode;
    int mindangerlevel;
    Spinner dropdown1;
    Spinner dropdown2;
    TextView t1,t2,t3,t4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        //mode selector
        dropdown1 = findViewById(R.id.alarm_mode);
        String[] items1 = new String[]{"Select mode...","ring", "shake", "message only"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        dropdown1.setAdapter(adapter1);

        //danger level selector
        dropdown2 = findViewById(R.id.min_dangerlevel);
        String[] items2 = new String[]{"1", "2", "3","4","5"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown2.setAdapter(adapter2);

        maxevents=Integer.parseInt(findViewById(R.id.num_of_events).toString());
        area=Integer.parseInt(findViewById(R.id.alarm_area).toString());

        t1=findViewById(R.id.set1);
        t2=findViewById(R.id.set2);
        t3=findViewById(R.id.set3);
        t4=findViewById(R.id.set4);

    }
    public void save_settings(View view){
        mode=dropdown1.getSelectedItem().toString();
        mindangerlevel=Integer.parseInt(dropdown2.getSelectedItem().toString());
        mDatabaseReference.child("Profile").child("TestUser").child("max_events").setValue(maxevents);
        mDatabaseReference.child("Profile").child("TestUser").child("area").setValue(area);
        mDatabaseReference.child("Profile").child("TestUser").child("mode").setValue(mode);
        mDatabaseReference.child("Profile").child("TestUser").child("min_dangerlevel").setValue(mindangerlevel);
        mDatabaseReference.child("Profile").addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //get profile. since we can't get the username which is logging in, we use an example.
                t1.setText("Numer of events show on the map:"+dataSnapshot.child("Profile").child("TestUser").child("max_events").getValue());
                t2.setText("Alarm area:"+dataSnapshot.child("Profile").child("TestUser").child("area").getValue());
                t3.setText("Alarm mode:"+dataSnapshot.child("Profile").child("TestUser").child("mode").getValue());
                t4.setText("Minimum danger level:"+dataSnapshot.child("Profile").child("TestUser").child("min_dangerlevel").getValue());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        Context context = getApplicationContext();
        CharSequence text = "Saved Successfully!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    public void cancel(View view){

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.menu_settings) {
//            Intent myIntent = new Intent(this, Settings.class);
//            startActivity(myIntent);
//            return true;
//        }
        if (id == R.id.menu_settings) {

            return true;
        }
        if (id == R.id.menu_profile) {
            Intent myIntent = new Intent(this, Profile.class);
            startActivity(myIntent);
            return true;
        }
        if (id == R.id.menu_map) {
            Intent myIntent = new Intent(this, MapsActivity.class);
            startActivity(myIntent);
            return true;
        }
        if (id == R.id.menu_login) {
            Intent myIntent = new Intent(this, LoginActivity.class);
            startActivity(myIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
