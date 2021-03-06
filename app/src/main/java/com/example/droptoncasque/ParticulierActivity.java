package com.example.droptoncasque;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ParticulierActivity extends FragmentActivity {

    private Button map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getTheme().applyStyle(R.style.OverlayThemeLime, true);
        setContentView(R.layout.activity_particulier);
        this.map = (Button) findViewById(R.id.map_button);

        map.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent mapActivity = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(mapActivity);
                finish();
            }
        });

    }
}