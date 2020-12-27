package com.example.droptoncasque;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InscriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_inscription);
        super.onCreate(savedInstanceState);
        Button btnInsc = (Button) findViewById(R.id.btnInscri);
        Button menu = (Button) findViewById(R.id.button_menu);
        btnInsc.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("ShowToast")
            @Override
            public void onClick(View view){
                UserModel newUser;
                try {
                    Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(loginActivity);
                    newUser = new UserModel();
                    Toast.makeText(InscriptionActivity.this, "Created", Toast.LENGTH_SHORT);
                } catch (Exception e) {
                    Intent mapActivity = new Intent(getApplicationContext(), MapsActivity.class);
                    startActivity(mapActivity);
                    Toast.makeText(InscriptionActivity.this, "Error in creation", Toast.LENGTH_SHORT);
                    newUser = new UserModel(-1, "error", "e", "error", false);
                }
                String pass = "Default";
                DataBaseHelper dbHelper = new DataBaseHelper(InscriptionActivity.this);
                final boolean success = dbHelper.addOne(newUser, pass);
                Toast.makeText(InscriptionActivity.this, "Success = " + success, Toast.LENGTH_SHORT);
            }
        });
        menu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });

    }

}
