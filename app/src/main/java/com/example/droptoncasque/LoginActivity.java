package com.example.droptoncasque;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Button;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;
import android.view.Window;
import android.widget.ToggleButton;



public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        TextView textSwitch = (TextView) findViewById(R.id.textSwitch);
        Switch switch1 = (Switch) findViewById(R.id.switch1);
        EditText mailInput = (EditText) findViewById(R.id.editTextTextEmailAddress);
        EditText pwInput = (EditText) findViewById(R.id.editTextTextPassword);
        Button log = (Button) findViewById(R.id.button);
        Button menu = (Button) findViewById(R.id.button_menu);
        Window window = getWindow();

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("SetTextI18n")
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    System.out.println(isChecked);
                    textSwitch.setText("Particulier");
                    log.setBackgroundColor(Color.parseColor("#C8BC1C"));
                    menu.setBackgroundColor(Color.parseColor("#C8BC1C"));
                    log.setTextColor(Color.parseColor("#000000"));
                    menu.setTextColor(Color.parseColor("#000000"));
                } else {
                    System.out.println(isChecked);
                    textSwitch.setText("Commerçant");
                    getTheme().applyStyle(R.style.OverlayThemeMain, true);
                    log.setBackgroundColor(Color.parseColor("#AD0C0A"));
                    menu.setBackgroundColor(Color.parseColor("#AD0C0A"));
                    log.setTextColor(Color.parseColor("#FFFFFF"));
                    menu.setTextColor(Color.parseColor("#FFFFFF"));

                }
            }
        });

        menu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });

        log.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                if (mailInput.getText().toString().equals("admin") && pwInput.getText().toString().equals("admin")){
                    Toast valid = Toast.makeText(getApplicationContext(),"Redirecting...",Toast.LENGTH_SHORT);
                    valid.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 350);
                    valid.show();
                    boolean check = (boolean) (switch1.isChecked());
                    if (check){
                        System.out.println("To user page");
                    }else{
                        Intent comActivity = new Intent(getApplicationContext(), ComActivity.class);
                        startActivity(comActivity);
                        finish();
                    }
                }else{
                    Toast wrong = Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT);
                    wrong.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 350);
                    wrong.show();
                }
            }
        });

    }

}