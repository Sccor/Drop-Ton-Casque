package com.example.droptoncasque;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private Button log;
    private Button map;
    private Button inscri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.log = (Button) findViewById(R.id.logBut);
        this.map = (Button) findViewById(R.id.map_button);
        this.inscri = (Button) findViewById(R.id.inscriBut);
        log.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view){
                Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(loginActivity);
            }
        });
        map.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view){
                Intent mapActivity = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(mapActivity);
            }
        });
        inscri.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view){
                Intent inscriptionActivity = new Intent(getApplicationContext(), InscriptionActivity.class);
                startActivity(inscriptionActivity);
            }
        });

    }
}