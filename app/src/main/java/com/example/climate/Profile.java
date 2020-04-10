package com.example.climate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.climate.ui.login.LoginActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5,t6;
    String s1,s2,s3,s4,s5,s6;
    public FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    public DatabaseReference mDatabaseReference = mDatabase.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        t1=findViewById(R.id.username);
        t1=findViewById(R.id.country);
        t1=findViewById(R.id.state);
        t1=findViewById(R.id.city);
        t1=findViewById(R.id.email);
        t1=findViewById(R.id.phone_num);
        //get profile from database
        mDatabaseReference.child("Profile").addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //get profile. since we can't get the username which is logging in, we use an example.
                s1 = dataSnapshot.child("TestUser").child("username").getValue().toString();
                s2 = dataSnapshot.child("TestUser").child("country").getValue().toString();
                s3 = dataSnapshot.child("TestUser").child("state").getValue().toString();
                s4 = dataSnapshot.child("TestUser").child("city").getValue().toString();
                s5 = dataSnapshot.child("TestUser").child("email").getValue().toString();
                s6 = dataSnapshot.child("TestUser").child("phone_num").getValue().toString();
                t1.setText(s1);
                t2.setText(s2);
                t3.setText(s3);
                t4.setText(s4);
                t5.setText(s5);
                t6.setText(s6);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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
            Intent myIntent = new Intent(this, Setting.class);
            startActivity(myIntent);
            return true;
        }
        if (id == R.id.menu_profile) {

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

    public void moveToCp(View view) {
        Intent myIntent = new Intent(this, ChangeProfile.class);
        startActivity(myIntent);
    }
}
