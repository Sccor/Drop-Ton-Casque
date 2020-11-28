package com.example.droptoncasque;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;
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
                finish();
            }
        });




        Button log = (Button) findViewById(R.id.button);

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
                        System.out.println("To vendor page");
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