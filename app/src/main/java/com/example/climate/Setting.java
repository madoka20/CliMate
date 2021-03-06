package com.example.climate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.climate.ui.login.LoginActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    TextView ma,ar;
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

        ma=findViewById(R.id.num_of_events);
        ar=findViewById(R.id.alarm_area);



        t1=findViewById(R.id.set1);
        t2=findViewById(R.id.set2);
        t3=findViewById(R.id.set3);
        t4=findViewById(R.id.set4);
        mDatabaseReference.child("Profile").addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //get profile. since we can't get the username which is logging in, we use an example.
                t1.setText("Numer of events show on the map:"+dataSnapshot.child("TestUser").child("max_events").getValue().toString());
                t2.setText("Alarm area:"+dataSnapshot.child("TestUser").child("area").getValue().toString());
                t3.setText("Alarm mode:"+dataSnapshot.child("TestUser").child("mode").getValue().toString());
                t4.setText("Minimum danger level:"+dataSnapshot.child("TestUser").child("min_dangerlevel").getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void save_settings(View view){
        maxevents=Integer.parseInt(ma.getText().toString());
        area=Integer.parseInt(ar.getText().toString());
        mode=dropdown1.getSelectedItem().toString();
        mindangerlevel=Integer.parseInt(dropdown2.getSelectedItem().toString());
        mDatabaseReference.child("Profile").child("TestUser").child("max_events").setValue(maxevents);
        mDatabaseReference.child("Profile").child("TestUser").child("area").setValue(area);
        mDatabaseReference.child("Profile").child("TestUser").child("mode").setValue(mode);
        mDatabaseReference.child("Profile").child("TestUser").child("min_dangerlevel").setValue(mindangerlevel);



        Context context = getApplicationContext();
        CharSequence text = "Saved Successfully!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    public void cancel(View view){

            Intent myIntent = new Intent(this, MapsActivity.class);
            startActivity(myIntent);

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
