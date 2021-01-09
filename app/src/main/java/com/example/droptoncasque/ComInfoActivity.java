package com.example.droptoncasque;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

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
        Button btnSend = (Button) findViewById(R.id.btnSend);
        ToggleButton buttonFavorite = (ToggleButton) findViewById(R.id.button_favorite);

        Intent intent = this.getIntent();
        String nom = intent.getStringExtra("title");
        DataBaseCommerces dataBC = new DataBaseCommerces(ComInfoActivity.this);
        DataBaseHelper dataUS = new DataBaseHelper(ComInfoActivity.this);
        CommerceModel commerce = dataBC.getCommerce(nom);

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

        ScaleAnimation scaleAnimation = new ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f, Animation.RELATIVE_TO_SELF, 0.7f, Animation.RELATIVE_TO_SELF, 0.7f);
        scaleAnimation.setDuration(500);
        BounceInterpolator bounceInterpolator = new BounceInterpolator();
        scaleAnimation.setInterpolator(bounceInterpolator);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Integer userId = sharedPref.getInt("User_ID", -1);
        UserModel user = dataUS.getUser(userId);
        if(user.getFavoris() != null){
            if(user.getFavoris().contains(commerce.getId())){
                buttonFavorite.setChecked(true);
            }
        }

        btnSend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(ComInfoActivity.this, "Envoie de votre réservation...", Toast.LENGTH_LONG).show();
            }
        });

        buttonFavorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                //animation
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Integer userId = sharedPref.getInt("User_ID", -1);
                compoundButton.startAnimation(scaleAnimation);

                if (isChecked){
                    System.out.println("AJOUT");
                    System.out.println("Avant ajout : " + dataUS.getUser(userId));
                    Boolean success = dataUS.addFavoris(userId, commerce.getId());
                    if(!success){
                        Toast.makeText(ComInfoActivity.this, "Déjà dans vos favoris", Toast.LENGTH_SHORT).show();
                    }
                    System.out.println("Après ajout : " + dataUS.getUser(userId));
                }else{
                    System.out.println("SUPPRESSION");
                    System.out.println("Avant Suppression : " + dataUS.getUser(userId));
                    Boolean success = dataUS.deleteFav(userId, commerce.getId());
                    System.out.println("Après Suppression : " + dataUS.getUser(userId));

                }
            }

        });

    }

}
