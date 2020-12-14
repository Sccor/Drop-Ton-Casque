package com.example.droptoncasque;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private Button menu;
    private ArrayList<Commerce> commerces;

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

    public class Commerce {
        private Integer id;
        private String nom;
        private String type;
        private String adresse;
        private Pair<Double,Double> coord;
        private String contact_email;
        private String contact_telephone;
        private String url;

        public Commerce(Integer id, String nom, String type, String adresse, Pair<Double, Double> coord, String contact_email, String contact_telephone, String url) {
            this.id = id;
            this.nom = nom;
            this.type = type;
            this.adresse = adresse;
            this.coord = coord;
            this.contact_email= contact_email;
            this.contact_telephone = contact_telephone;
            this.url = url;

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

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getContact_email() {
            return contact_email;
        }

        public void setContact_email(String contact_email) {
            this.contact_email = contact_email;
        }

        public String getContact_telephone() {
            return contact_telephone;
        }

        public void setContact_telephone(String contact_telephone) {
            this.contact_telephone = contact_telephone;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "Commerce{" +
                    "id=" + id +
                    ", nom='" + nom + '\'' +
                    ", type='" + type + '\'' +
                    ", adresse='" + adresse + '\'' +
                    ", coord=" + coord +
                    ", contact_email='" + contact_email + '\'' +
                    ", contact_telephone='" + contact_telephone + '\'' +
                    '}';
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        commerces = new ArrayList<Commerce>();
        Commerce efrei = new Commerce(1,"Efrei Paris", "École d'ingénieur", "30- 32 Avenue de la République 94800 Villejuif", new Pair<Double, Double>(48.788759834312756, 2.363766951205992),"admissions@efrei.fr" , "+33 188 289 000", "https://www.efrei.fr/");
        commerces.add(efrei);
        System.out.println(efrei);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        this.menu = (Button) findViewById(R.id.map_button_menu);
        menu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Commerce efrei = null;
        int index = 0;
        for (int i = 0; i < commerces.size(); i++){
            if(commerces.get(i).getId() == 1){
                index = i;
                efrei  = commerces.get(index);
            }
        }

        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        LatLng posEfrei = new LatLng(efrei.coord.getS(), efrei.coord.getI());
        mMap.addMarker(new MarkerOptions()
                .position(posEfrei)
                .snippet(efrei.adresse + "\n Cliquez pour plus d'informations")
                .title(efrei.nom));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(posEfrei));
        float zoomLevel = 16.0f;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posEfrei, zoomLevel));

        mMap.setOnInfoWindowClickListener(this);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        System.out.println(marker);
//        Commerce efrei = null;
//        int index = 0;
//        for (int i = 0; i < commerces.size(); i++){
//            if(commerces.get(i).getId() == 1){
//                index = i;
//                efrei  = commerces.get(index);
//            }
//        }
        System.out.println(marker);
        System.out.println("Heyy");
        Commerce efrei = new Commerce(1,"Efrei Paris", "École d'ingénieur", "30- 32 Avenue de la République 94800 Villejuif", new Pair<Double, Double>(48.788759834312756, 2.363766951205992),"admissions@efrei.fr" , "+33 188 289 000", "https://www.efrei.fr/");
        Toast.makeText(this, "Info window clicked",
                Toast.LENGTH_SHORT).show();

        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        View popupView = inflater.inflate(R.layout.popup_window, null);
//            TextView comTitle = (TextView) findViewById(R.id.com_title);
//            System.out.println(efrei);
//            comTitle.setText("Efrei PARIS");
            // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                TextView title = (TextView) findViewById(R.id.com_title);
//                title.setText(efrei.getNom());
//                TextView type = (TextView) findViewById(R.id.com_type);
//                type.setText(efrei.getNom());
//                TextView content = (TextView) findViewById(R.id.com_content);
//                content.setText(efrei.getNom());
//                TextView email = (TextView) findViewById(R.id.com_email);
//                email.setText(efrei.getContact_email());
//                TextView tel = (TextView) findViewById(R.id.com_telephone);
//                tel.setText(efrei.getContact_telephone());
//                TextView url = (TextView) findViewById(R.id.com_url);
//                url.setText(efrei.getNom());
//            }
//        });
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);


        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
        }
}