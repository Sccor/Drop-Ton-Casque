package com.example.droptoncasque;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        TextView textSwitch = (TextView) findViewById(R.id.textSwitch);
        Switch switch1 = (Switch) findViewById(R.id.switch1);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("SetTextI18n")
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    System.out.println(isChecked);
                    textSwitch.setText("Particulier");
                } else {
                    System.out.println(isChecked);
                    textSwitch.setText("Commerçant");
                }
            }
        });
        Button menu = (Button) findViewById(R.id.button_menu);
        menu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainActivity);
                finish();
            }
        });

    }

}