package com.example.droptoncasque;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InscriptionActivity extends AppCompatActivity {

    private Button btnInsc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.btnInsc = (Button) findViewById(R.id.buttonInscrip);

        btnInsc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View w){
                UserModel newUser = null;
                try {
                    newUser = new UserModel();
                    Toast.makeText(InscriptionActivity.this, "Created", Toast.LENGTH_SHORT);
                } catch (Exception e) {
                    Toast.makeText(InscriptionActivity.this, "Error in creation", Toast.LENGTH_SHORT);
                    newUser = new UserModel(-1, "error", "e", "error", false);
                }
                String pass = "Default";
                DataBaseHelper dbHelper = new DataBaseHelper(InscriptionActivity.this);
                final boolean success = dbHelper.addOne(newUser, pass);
                Toast.makeText(InscriptionActivity.this, "Success = " + success, Toast.LENGTH_SHORT);
            }
        });
    }

}
