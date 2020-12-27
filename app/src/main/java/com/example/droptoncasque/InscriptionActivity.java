package com.example.droptoncasque;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class InscriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_inscription);
        super.onCreate(savedInstanceState);
        Button btnInsc = (Button) findViewById(R.id.btnInscri);
        Button menu = (Button) findViewById(R.id.button_menu);
        EditText Nom = findViewById(R.id.PersonName);
        EditText Prenom = findViewById(R.id.PersonSurname);
        EditText Email = findViewById(R.id.EmailAddress);
        EditText Mdp = findViewById(R.id.Password1);
        EditText Cmdp = findViewById(R.id.Password2);
        TextView textSwitch = (TextView) findViewById(R.id.textSwitch);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch switch1 = (Switch) findViewById(R.id.switch1);

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("SetTextI18n")
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    textSwitch.setText("Particulier");
                    btnInsc.setBackgroundColor(Color.parseColor("#FF9A00"));
                    menu.setBackgroundColor(Color.parseColor("#FF9A00"));
                    btnInsc.setTextColor(Color.parseColor("#000000"));
                    menu.setTextColor(Color.parseColor("#000000"));
                } else {
                    textSwitch.setText("Commerçant");
                    getTheme().applyStyle(R.style.OverlayThemeMain, true);
                    btnInsc.setBackgroundColor(Color.parseColor("#AD0C0A"));
                    menu.setBackgroundColor(Color.parseColor("#AD0C0A"));
                    btnInsc.setTextColor(Color.parseColor("#FFFFFF"));
                    menu.setTextColor(Color.parseColor("#FFFFFF"));

                }
            }
        });

        btnInsc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                UserModel newUser;
                try {
                    if(Mdp.getText().toString().equals(Cmdp.getText().toString())){
                        newUser = new UserModel(-1, Nom.getText().toString(), Prenom.getText().toString(), Email.getText().toString(), textSwitch.getText().toString().equals("Particulier"), new ArrayList<Integer>());
                        DataBaseHelper dbHelper = new DataBaseHelper(InscriptionActivity.this);
                        final boolean success = dbHelper.addOne(newUser, Mdp.getText().toString());
                        Toast valid = Toast.makeText(getApplicationContext(),"Done !",Toast.LENGTH_SHORT);
                    }
                } catch (Exception e) {
                    newUser = new UserModel(-1, "error", "e", "error", true, null);
                    Toast wrong = Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT);
                }
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
