package com.example.climate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChangeProfile extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5,t6;
    public String username;
    public String country;
    public String state;
    public String city;
    public String email;
    public String phone_num;

    public FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    public DatabaseReference mDatabaseReference = mDatabase.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_profile);
        t1=findViewById(R.id.username);
        t2=findViewById(R.id.country);
        t3=findViewById(R.id.state);
        t4=findViewById(R.id.city);
        t5=findViewById(R.id.email);
        t6=findViewById(R.id.phone_num);

    }
//save the profile into database
    public void save_profile(View view){
        username=t1.getText().toString();
        country=t2.getText().toString();
        state=t3.getText().toString();
        city=t4.getText().toString();
        email=t5.getText().toString();
        phone_num=t6.getText().toString();

//        User user = new User(username,country,state,city,email,phone_num);
        mDatabaseReference.child("Profile").child("TestUser").child("username").setValue(username);
        mDatabaseReference.child("Profile").child("TestUser").child("country").setValue(country);
        mDatabaseReference.child("Profile").child("TestUser").child("state").setValue(state);
        mDatabaseReference.child("Profile").child("TestUser").child("city").setValue(city);
        mDatabaseReference.child("Profile").child("TestUser").child("email").setValue(email);
        mDatabaseReference.child("Profile").child("TestUser").child("phone_num").setValue(phone_num);

        Intent myIntent = new Intent(this,Profile.class);

        startActivity(myIntent);

        Context context = getApplicationContext();
        CharSequence text = "Profile Saved!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    public void cancel(View view){
        Intent myIntent = new Intent(this,Profile.class);

        startActivity(myIntent);

    }

}
