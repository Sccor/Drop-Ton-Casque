package com.example.droptoncasque;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
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
    private ArrayList<CommerceModel> commerces;

    public static class Pair<S, I>{
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
            return s + "," + i;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        int index = 0;
        LatLng posEfrei = new LatLng(48.788759834312756, 2.363766951205992);
        mMap = googleMap;
        DataBaseCommerces dataBC = new DataBaseCommerces(MapsActivity.this);
        List<CommerceModel> everyone = dataBC.getAllCommerces();
        for (CommerceModel commerce : everyone){
            LatLng pos = new LatLng(commerce.getCoord().getS(), commerce.getCoord().getI());
            mMap.addMarker(new MarkerOptions()
                    .position(pos)
                    .snippet(commerce.getAdresse() + "\n Cliquez pour plus d'informations")
                    .title(commerce.getNom()));
        }
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(posEfrei));
        float zoomLevel = 16.0f;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posEfrei, zoomLevel));

        mMap.setOnInfoWindowClickListener(this);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

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