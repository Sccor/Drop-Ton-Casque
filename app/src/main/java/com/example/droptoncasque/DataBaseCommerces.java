package com.example.droptoncasque;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataBaseCommerces extends SQLiteOpenHelper {

    public static final String COMMERCES_TABLE = "COMMERCES_TABLE";
    public static final String COLUMN_COMMERCE_NOM = "COMMERCE_NOM";
    public static final String COLUMN_COMMERCE_TYPE = "COMMERCE_TYPE";
    public static final String COLUMN_COMMERCE_ADRESSE = "COMMERCE_ADRESSE";
    public static final String COLUMN_COMMERCE_CONTACT_EMAIL = "COMMERCE_CONTACT_EMAIL";
    public static final String COLUMN_COMMERCE_CONTACT_TELEPHONE = "COMMERCE_CONTACT_TELEPHONE";
    public static final String COLUMN_COMMERCE_ID = "COMMERCE_ID";
    public static final String COLUMN_COMMERCE_COORD = "COMMERCE_COORD";
    public static final String COLUMN_COMMERCE_URL = "COMMERCE_URL";

    public DataBaseCommerces(@Nullable Context context) {
        super(context, "commerces.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createStatement = "CREATE TABLE " + COMMERCES_TABLE + " (" + COLUMN_COMMERCE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_COMMERCE_NOM + " TEXT, " + COLUMN_COMMERCE_TYPE + " TEXT, " + COLUMN_COMMERCE_ADRESSE + " TEXT, " + COLUMN_COMMERCE_CONTACT_EMAIL + " TEXT, " + COLUMN_COMMERCE_CONTACT_TELEPHONE + " TEXT, " + COLUMN_COMMERCE_URL + " TEXT, " + COLUMN_COMMERCE_COORD + " TEXT)";
        db.execSQL(createStatement);

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_COMMERCE_NOM, "O terminus");
        cv.put(COLUMN_COMMERCE_TYPE, "Fast Food");
        cv.put(COLUMN_COMMERCE_ADRESSE, "26 Avenue de la République, 94800 Villejuif");
        cv.put(COLUMN_COMMERCE_CONTACT_EMAIL, "Inconnu");
        cv.put(COLUMN_COMMERCE_CONTACT_TELEPHONE, "Inconnu");
        cv.put(COLUMN_COMMERCE_URL, "Inconnu");
        cv.put(COLUMN_COMMERCE_COORD, "48.7885155,2.3645214");
        long insert = db.insert(COMMERCES_TABLE, null, cv);

        cv = new ContentValues();
        cv.put(COLUMN_COMMERCE_NOM, "La fabrique");
        cv.put(COLUMN_COMMERCE_TYPE, "Boulangerie");
        cv.put(COLUMN_COMMERCE_ADRESSE, "5 Avenue Louis Aragon, 94800 Villejuif");
        cv.put(COLUMN_COMMERCE_CONTACT_EMAIL, "Inconnu");
        cv.put(COLUMN_COMMERCE_CONTACT_TELEPHONE, "+33188280795");
        cv.put(COLUMN_COMMERCE_URL, "Inconnu");
        cv.put(COLUMN_COMMERCE_COORD, "48.7886521,2.3675418");
        insert = db.insert(COMMERCES_TABLE, null, cv);

        cv = new ContentValues();
        cv.put(COLUMN_COMMERCE_NOM, "7e Avenue");
        cv.put(COLUMN_COMMERCE_TYPE, "Concessionaire Moto");
        cv.put(COLUMN_COMMERCE_ADRESSE, "147 Avenue de Paris, 94800 Villejuif");
        cv.put(COLUMN_COMMERCE_CONTACT_EMAIL, "Inconnu");
        cv.put(COLUMN_COMMERCE_CONTACT_TELEPHONE, "+33147269444");
        cv.put(COLUMN_COMMERCE_URL, "https://www.honda-7emeavenue.com/");
        cv.put(COLUMN_COMMERCE_COORD, "48.7990472,2.3661356");
        insert = db.insert(COMMERCES_TABLE, null, cv);

        cv = new ContentValues();
        cv.put(COLUMN_COMMERCE_NOM, "Casino Shop");
        cv.put(COLUMN_COMMERCE_TYPE, "Supermarché");
        cv.put(COLUMN_COMMERCE_ADRESSE, "Avenue Louis Aragon, 94800 Villejuif");
        cv.put(COLUMN_COMMERCE_CONTACT_EMAIL, "Inconnu");
        cv.put(COLUMN_COMMERCE_CONTACT_TELEPHONE, "Inconnu");
        cv.put(COLUMN_COMMERCE_URL, "http://www.casinoshop.fr/");
        cv.put(COLUMN_COMMERCE_COORD, "48.7878012,2.3671122");
        insert = db.insert(COMMERCES_TABLE, null, cv);

        cv = new ContentValues();
        cv.put(COLUMN_COMMERCE_NOM, "Centre de lavage Auto/moto Novajet Villejuif");
        cv.put(COLUMN_COMMERCE_TYPE, "Centre de Lavage");
        cv.put(COLUMN_COMMERCE_ADRESSE, "88 Rue Auguste Perret, 94800 Villejuif");
        cv.put(COLUMN_COMMERCE_CONTACT_EMAIL, "Inconnu");
        cv.put(COLUMN_COMMERCE_CONTACT_TELEPHONE, "+33149588920");
        cv.put(COLUMN_COMMERCE_URL, "https://www.facebook.com/nova.jet.90");
        cv.put(COLUMN_COMMERCE_COORD, "48.7888417,2.3608633");
        insert = db.insert(COMMERCES_TABLE, null, cv);

        cv = new ContentValues();
        cv.put(COLUMN_COMMERCE_NOM, "Franprix");
        cv.put(COLUMN_COMMERCE_TYPE, "Supermarché");
        cv.put(COLUMN_COMMERCE_ADRESSE, "7 Avenue de la République, 94800 Villejuif");
        cv.put(COLUMN_COMMERCE_CONTACT_EMAIL, "Inconnu");
        cv.put(COLUMN_COMMERCE_CONTACT_TELEPHONE, "+33143908505");
        cv.put(COLUMN_COMMERCE_URL, "https://www.franprix.fr/magasins/6958");
        cv.put(COLUMN_COMMERCE_COORD, "48.7895693,2.3648678");
        insert = db.insert(COMMERCES_TABLE, null, cv);

        cv = new ContentValues();
        cv.put(COLUMN_COMMERCE_NOM, "Le Rallye");
        cv.put(COLUMN_COMMERCE_TYPE, "Bar Tabac");
        cv.put(COLUMN_COMMERCE_ADRESSE, "77 Rue Jean Jaurès, 94800 Villejuif");
        cv.put(COLUMN_COMMERCE_CONTACT_EMAIL, "Inconnu");
        cv.put(COLUMN_COMMERCE_CONTACT_TELEPHONE, "Inconnu");
        cv.put(COLUMN_COMMERCE_URL, "Inconnu");
        cv.put(COLUMN_COMMERCE_COORD, "48.79304,2.3659996");
        insert = db.insert(COMMERCES_TABLE, null, cv);

        cv = new ContentValues();
        cv.put(COLUMN_COMMERCE_NOM, "Efrei PARIS");
        cv.put(COLUMN_COMMERCE_TYPE, "Ecole d'ingénieur");
        cv.put(COLUMN_COMMERCE_ADRESSE, "30- 32 Avenue de la République 94800 Villejuif");
        cv.put(COLUMN_COMMERCE_CONTACT_EMAIL, "admissions@efrei.fr");
        cv.put(COLUMN_COMMERCE_CONTACT_TELEPHONE, "+33188289000");
        cv.put(COLUMN_COMMERCE_URL, "https://www.efrei.fr/");
        cv.put(COLUMN_COMMERCE_COORD, "48.788759834312756,2.363766951205992");
        insert = db.insert(COMMERCES_TABLE, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean addOne(CommerceModel newCOMMERCE){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_COMMERCE_NOM, newCOMMERCE.getNom());
        cv.put(COLUMN_COMMERCE_TYPE, newCOMMERCE.getType());
        cv.put(COLUMN_COMMERCE_ADRESSE, newCOMMERCE.getAdresse());
        cv.put(COLUMN_COMMERCE_CONTACT_EMAIL, newCOMMERCE.getContact_email());
        cv.put(COLUMN_COMMERCE_CONTACT_TELEPHONE, newCOMMERCE.getContact_telephone());
        cv.put(COLUMN_COMMERCE_URL, newCOMMERCE.getUrl());
        cv.put(COLUMN_COMMERCE_COORD, newCOMMERCE.getCoord().toString());


        long insert = db.insert(COMMERCES_TABLE, null, cv);
        if(insert == -1){
            return false;
        }else{
            return true;
        }
    }

    public List<CommerceModel> getAllCommerces(){

        List<CommerceModel> commerces =  new ArrayList<CommerceModel>();

        String queryString = "SELECT * FROM " + COMMERCES_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do{
                int commerceId = cursor.getInt(0);
                String commerceNom = cursor.getString(1);
                String commerceType = cursor.getString(2);
                String commerceAdresse = cursor.getString(3);
                String commerceEmail = cursor.getString(4);
                String commerceTelephone = cursor.getString(5);
                String commerceUrl = cursor.getString(6);
                String commerceCoord = cursor.getString(7);
                String[] arr = commerceCoord.split(",");
                Double coord1 = Double.valueOf(arr[0]);
                Double coord2 = Double.valueOf(arr[1]);

                MapsActivity.Pair<Double, Double> coord = new MapsActivity.Pair<Double, Double>(coord1, coord2);
                CommerceModel newCommerce = new CommerceModel(commerceId, commerceNom, commerceType, commerceAdresse, commerceEmail, commerceTelephone, commerceUrl, coord);
                commerces.add(newCommerce);

            }while (cursor.moveToNext());

        }

        cursor.close();
        db.close();

        return commerces;

    }

}
