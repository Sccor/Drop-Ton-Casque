package com.example.droptoncasque;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String USERS_TABLE = "USERS_TABLE";
    public static final String COLUMN_USER_NOM = "USER_NOM";
    public static final String COLUMN_USER_PRENOM = "USER_PRENOM";
    public static final String COLUMN_USER_EMAIL = "USER_EMAIL";
    public static final String COLUMN_USER_PASSWORD = "USER_PASSWORD";
    public static final String COLUMN_USER_FONCTION = "USER_FONCTION";
    public static final String COLUMN_USER_ID = "USER_ID";
    public static final String COLUMN_USER_FAVORIS = "USER_FAVORIS";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "users.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createStatement = "CREATE TABLE " + USERS_TABLE + " (" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_NOM + " TEXT, " + COLUMN_USER_PRENOM + " TEXT, " + COLUMN_USER_EMAIL + " TEXT, " + COLUMN_USER_PASSWORD + " TEXT, " + COLUMN_USER_FONCTION + " BOOL, " + COLUMN_USER_FAVORIS + " TEXT)";
        db.execSQL(createStatement);

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USER_NOM, "MANGEARD");
        cv.put(COLUMN_USER_PRENOM, "PHILIPPE");
        cv.put(COLUMN_USER_EMAIL, "philippe.mangeard@efrei.net");
        cv.put(COLUMN_USER_FONCTION, 0);
        cv.put(COLUMN_USER_FAVORIS, "8");
        cv.put(COLUMN_USER_PASSWORD, "admin");
        long insert = db.insert(USERS_TABLE, null, cv);

        cv.put(COLUMN_USER_NOM, "MELINE");
        cv.put(COLUMN_USER_PRENOM, "STAN");
        cv.put(COLUMN_USER_EMAIL, "stan.meline@efrei.net");
        cv.put(COLUMN_USER_FONCTION, 0);
        cv.put(COLUMN_USER_FAVORIS, "8");
        cv.put(COLUMN_USER_PASSWORD, "admin");
        insert = db.insert(USERS_TABLE, null, cv);

        cv.put(COLUMN_USER_NOM, "MANCEAU");
        cv.put(COLUMN_USER_PRENOM, "LUC");
        cv.put(COLUMN_USER_EMAIL, "luc.manceau@efrei.net");
        cv.put(COLUMN_USER_FONCTION, 0);
        cv.put(COLUMN_USER_FAVORIS, "8");
        cv.put(COLUMN_USER_PASSWORD, "admin");
        insert = db.insert(USERS_TABLE, null, cv);

        cv.put(COLUMN_USER_NOM, "STRILING");
        cv.put(COLUMN_USER_PRENOM, "ALIX");
        cv.put(COLUMN_USER_EMAIL, "alix.stirling@efrei.net");
        cv.put(COLUMN_USER_FONCTION, 0);
        cv.put(COLUMN_USER_FAVORIS, "8");
        cv.put(COLUMN_USER_PASSWORD, "admin");
        insert = db.insert(USERS_TABLE, null, cv);

        cv.put(COLUMN_USER_NOM, "HUREL");
        cv.put(COLUMN_USER_PRENOM, "MATHILDE");
        cv.put(COLUMN_USER_EMAIL, "mathilde.hurel@efrei.net");
        cv.put(COLUMN_USER_FONCTION, 0);
        cv.put(COLUMN_USER_FAVORIS, "8");
        cv.put(COLUMN_USER_PASSWORD, "admin");
        insert = db.insert(USERS_TABLE, null, cv);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean addOne(UserModel newUser, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER_NOM, newUser.getNom());
        cv.put(COLUMN_USER_PRENOM, newUser.getPrenom());
        cv.put(COLUMN_USER_EMAIL, newUser.getEmail());
        cv.put(COLUMN_USER_FONCTION, newUser.getFonction());
        if (newUser.getFavoris() != null){
            cv.put(COLUMN_USER_FAVORIS, newUser.getFavoris().stream().map(Object::toString).collect(Collectors.joining(", ")));
        }else{
            cv.put(COLUMN_USER_FAVORIS, "");

        }
        cv.put(COLUMN_USER_PASSWORD, BCrypt.hashpw(password, BCrypt.gensalt()));

        long insert = db.insert(USERS_TABLE, null, cv);
        if(insert == -1){
            return false;
        }else{
            return true;
        }
    }

    public UserModel login(String mail, String pw){

        String queryString = "SELECT * FROM " + USERS_TABLE + " WHERE USER_EMAIL=?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, new String[] {mail});
        UserModel loggedUser = null;
        System.out.println("CURSOR : " + cursor);
        if(cursor.moveToFirst() && BCrypt.checkpw(pw, cursor.getString(4))){
             loggedUser = new UserModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                     cursor.getString(3), cursor.getInt(5), cursor.getString(6));
        }
        System.out.println("\n\n\n User found : " + loggedUser + "\n\n\n");
        cursor.close();
        db.close();
        return loggedUser;
    }

    public boolean addFavoris(Integer userId, Integer idNewFav){
        String oldFavoris = null;
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            String queryString = "SELECT * FROM USERS_TABLE WHERE USER_ID=?";
            Cursor cursor = db.rawQuery(queryString, new String[]{userId.toString()});
            if(cursor.moveToFirst()) {
                oldFavoris = cursor.getString(6);
            }
            String newFavoris = oldFavoris + "," + idNewFav;
            queryString = "UPDATE USERS_TABLE SET " + COLUMN_USER_FAVORIS + "=? WHERE " + COLUMN_USER_ID + "=?";
            System.out.println(new String[]{newFavoris, String.valueOf(userId)});
            db.rawQuery(queryString, new String[]{newFavoris, userId.toString()});
            System.out.println("J'AJOUTE !!!!");

            return true;
        }catch(Exception e){
            return false;
        }

    }

    public UserModel getUser(Integer id){

        String queryString = "SELECT * FROM " + USERS_TABLE + " WHERE " +  COLUMN_USER_ID + "=?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, new String[]{id.toString()});
        UserModel newUser = null;
        if(cursor.moveToFirst()){
            String userNom = cursor.getString(1);
            String userPrenom = cursor.getString(2);
            String userEmail = cursor.getString(3);
            Integer userFonction = cursor.getInt(5);
            String userFavoris = cursor.getString(6);
            newUser = new UserModel(id, userNom, userPrenom, userEmail, userFonction, userFavoris);
        }

        cursor.close();
        db.close();

        return newUser;

    }

}
