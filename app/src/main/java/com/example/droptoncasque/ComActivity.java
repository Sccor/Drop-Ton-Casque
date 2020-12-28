package com.example.droptoncasque;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class ComActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_com);

        Button menu = (Button) findViewById(R.id.button_menu);
        Button add = (Button) findViewById(R.id.btnAjout);
        Button testQuery = (Button) findViewById(R.id.testQuery);

        EditText Nom = findViewById(R.id.comNom);
        EditText Type = findViewById(R.id.comType);
        EditText Email = findViewById(R.id.comEmail);
        EditText Tel = findViewById(R.id.comTel);
        EditText Adresse = findViewById(R.id.comAdresse);
        EditText Url = findViewById(R.id.comUrl);



        menu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainActivity);
                finish();
            }
        });
        testQuery.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                DataBaseCommerces dataBC = new DataBaseCommerces(ComActivity.this);
                List<CommerceModel> everyone = dataBC.getAllCommerces();
                Toast.makeText(ComActivity.this, everyone.toString(), Toast.LENGTH_LONG);
            }
        });

        add.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view){
                CommerceModel newCommerce;
                try {
                    newCommerce = new CommerceModel(-1, Nom.getText().toString(), Type.getText().toString(), Adresse.getText().toString(), Email.getText().toString(), Tel.getText().toString(), Url.getText().toString(), new MapsActivity.Pair<Double, Double>(48.79, (Double)(Math.random() * ((2.40 - 2.35)+1)+2.35)));
                    DataBaseCommerces dbHelper = new DataBaseCommerces(ComActivity.this);
                    final boolean success = dbHelper.addOne(newCommerce);
                    Toast valid = Toast.makeText(getApplicationContext(),"Added",Toast.LENGTH_SHORT);
                    valid.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 350);
                    valid.show();

                } catch (Exception e) {
                    newCommerce = new CommerceModel(-1, "error", null, null, null, null, null, null);
                    Toast wrong = Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT);
                    wrong.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 350);
                    wrong.show();
                }
            }
        });
    }
}