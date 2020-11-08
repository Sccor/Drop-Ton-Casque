package com.example.droptoncasque;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    private Button menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        this.menu = (Button) findViewById(R.id.button_menu);
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