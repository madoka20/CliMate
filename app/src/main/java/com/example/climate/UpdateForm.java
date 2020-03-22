package com.example.climate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class UpdateForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_form);

        //type selector
        Spinner dropdown1 = findViewById(R.id.type_selector);
        String[] items1 = new String[]{"Select type...","Snow storm", "Earthquake", "Volcano eruption"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        dropdown1.setAdapter(adapter1);

        //danger level selector
        Spinner dropdown2 = findViewById(R.id.danger_level_selector);
        String[] items2 = new String[]{"Select danger level...","1", "2", "3","4","5"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown2.setAdapter(adapter2);
    }

}
