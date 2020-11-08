package com.example.droptoncasque;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView log;
    private Button map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.log = (ImageView) findViewById(R.id.log);
        this.map = (Button) findViewById(R.id.map_button);
        log.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(loginActivity);
                finish();
            }
        });
        map.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent mapActivity = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(mapActivity);
                finish();
            }
        });
    }
}