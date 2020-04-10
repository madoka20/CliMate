package com.example.climate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.climate.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.climate.ui.login.LoginActivity;

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
        setContentView(R.layout.activity_sign_up);
        username=findViewById(R.id.username).getContext().toString();
        country=findViewById(R.id.country).getContext().toString();
        state=findViewById(R.id.state).getContext().toString();
        city=findViewById(R.id.city).getContext().toString();
        email=findViewById(R.id.email).getContext().toString();
        phone_num=findViewById(R.id.phone_num).getContext().toString();

    }

    public void save_profile(View view){
        User user = new User(username,country,state,city,email,phone_num);
        mDatabaseReference.child("Profile").child(username).setValue(user);
        Intent myIntent = new Intent(this,MapsActivity.class);

        startActivity(myIntent);

        Context context = getApplicationContext();
        CharSequence text = "Profile Saved!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    public void cancel(View view){
        Intent myIntent = new Intent(this,MapsActivity.class);

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
        if (id == R.id.menu_signup) {

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
