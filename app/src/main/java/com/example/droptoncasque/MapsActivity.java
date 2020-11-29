package com.example.droptoncasque;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button menu;

    public class Pair<S, I>{
        private S s;
        private I i;

        public Pair(S s, I i) {
            this.s = s;
            this.i = i;
        }
        public S getS() {return s;}
        public void setS(S s) {this.s = s;}
        public I getI() {return i;}
        public void setI(I i) {this.i = i;}

        @Override
        public String toString() {
            return "(" + s + ", " + i + ")";
        }
    }

    public class commerce {
        private String nom;
        private String type;
        private String adresse;
        private Pair<Double,Double> coord;

        public commerce(String nom, String type, String adresse, Pair<Double, Double> coord) {
            this.nom = nom;
            this.type = type;
            this.adresse = adresse;
            this.coord = coord;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAdresse() {
            return adresse;
        }

        public void setAdresse(String adresse) {
            this.adresse = adresse;
        }

        public Pair<Double, Double> getCoord() {
            return coord;
        }

        public void setCoord(Pair<Double, Double> coord) {
            this.coord = coord;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        this.menu = (Button) findViewById(R.id.map_button_menu);
        menu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainActivity);
                finish();
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        commerce efrei = new commerce("Efrei Paris", "École d'ingénieur", "30- 32 Avenue de la République, 94800 Villejuif", new Pair<Double, Double>(48.788759834312756, 2.363766951205992));
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        LatLng posEfrei = new LatLng(efrei.coord.getS(), efrei.coord.getI());
        mMap.addMarker(new MarkerOptions()
                .position(posEfrei)
                .snippet(efrei.adresse)
                .title(efrei.nom));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(posEfrei));
        float zoomLevel = 16.0f;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posEfrei, zoomLevel));

    }
}