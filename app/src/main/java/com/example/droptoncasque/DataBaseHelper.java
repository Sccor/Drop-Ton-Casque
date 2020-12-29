package com.example.droptoncasque;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

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
        if(cursor.moveToFirst() && BCrypt.checkpw(pw, cursor.getString(4))){
            ArrayList<Integer> favs = new ArrayList<>();
            if (cursor.getString(6).equals("")){
                favs = null;
            }else{
                ArrayList<String> arr = new ArrayList<String>(Arrays.asList(cursor.getString(6).split(", ")));
                for (Object str : arr){
                    favs.add(Integer.parseInt((String) str));
                }
            }
             loggedUser = new UserModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), (cursor.getInt(5) == 1), favs);

        }
        System.out.println("\n\n\n user found : " + loggedUser + "\n\n\n");
        cursor.close();
        db.close();
        return loggedUser;
    }


}
