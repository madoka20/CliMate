package com.example.climate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

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
}
