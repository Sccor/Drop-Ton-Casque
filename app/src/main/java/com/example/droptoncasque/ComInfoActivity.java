package com.example.droptoncasque;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.SupportMapFragment;

public class ComInfoActivity  extends AppCompatActivity {



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_info);

        TextView infoNom = (TextView) findViewById(R.id.infoNom);
        TextView infoType = (TextView) findViewById(R.id.infoType);
        TextView infoAdresse = (TextView) findViewById(R.id.infoAdresse);
        TextView infoMail = (TextView) findViewById(R.id.infoMail);
        TextView infoTel = (TextView) findViewById(R.id.infoTel);
        TextView infoUrl = (TextView) findViewById(R.id.infoUrl);
        TextView infoHoraire = (TextView) findViewById(R.id.infoHoraire);

        Intent intent = this.getIntent();
        String nom = intent.getStringExtra("title");
        DataBaseCommerces dataBC = new DataBaseCommerces(ComInfoActivity.this);
        CommerceModel commerce = dataBC.getCommerce(nom);
        Toast.makeText(this, commerce.toString(),
            Toast.LENGTH_LONG).show();

        infoNom.setText(commerce.getNom());
        infoType.setText(commerce.getType());
        infoAdresse.setText(commerce.getAdresse());

        if(commerce.getContact_email().equals("Inconnu")){
            infoMail.setText("Aucun e-mail renseigné");
        }else{
            infoMail.setText(commerce.getContact_email());
        }

        if(commerce.getContact_telephone().equals("Inconnu")){
            infoTel.setText("Aucun numéro de téléphone");
        }else{
            infoTel.setText(commerce.getContact_telephone());
        }

        if(commerce.getUrl().equals("Inconnu")){
            infoUrl.setText("Aucun site internet renseigné");
        }else{
            infoUrl.setText(commerce.getUrl());
        }

        infoHoraire.setText(commerce.getHoraire());
    }



}
