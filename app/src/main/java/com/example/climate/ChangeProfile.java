package com.example.climate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChangeProfile extends AppCompatActivity {
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
        username=findViewById(R.id.username).getContext().toString();
        country=findViewById(R.id.country).getContext().toString();
        state=findViewById(R.id.state).getContext().toString();
        city=findViewById(R.id.city).getContext().toString();
        email=findViewById(R.id.email).getContext().toString();
        phone_num=findViewById(R.id.phone_num).getContext().toString();

    }
//save the profile into database
    public void save_profile(View view){
        User user = new User(username,country,state,city,email,phone_num);
        mDatabaseReference.child("Profile").child(username).setValue(user);
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
