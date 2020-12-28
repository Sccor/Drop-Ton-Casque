package com.example.droptoncasque;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Button;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;
import android.view.Window;
import android.widget.ToggleButton;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.stream.Collectors;


public class LoginActivity extends AppCompatActivity {
    TextView textSwitch;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switch1;
    EditText mailInput;
    EditText pwInput;
    Button log;
    Button menu;
    Window window;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SQLiteDatabase db = null;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        this.textSwitch = (TextView) findViewById(R.id.textSwitch);
        this.switch1 = (Switch) findViewById(R.id.switch1);
        this.mailInput = (EditText) findViewById(R.id.editTextTextEmailAddress);
        this.pwInput = (EditText) findViewById(R.id.editTextTextPassword);
        this.log = (Button) findViewById(R.id.button);
        this.menu = (Button) findViewById(R.id.button_menu);
        this.window = getWindow();

//        try{
//            db=openOrCreateDatabase("StudentDB", SQLiteDatabase.CREATE_IF_NECESSARY, null);
//        }catch(SQLException e) {
//        }
//            db = db.openDatabase("usersDB", null, SQLiteDatabase.CREATE_IF_NECESSARY);
//        Cursor c=db.rawQuery("SELECT * FROM users", null);
//        System.out.println(c);

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("SetTextI18n")
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    System.out.println(isChecked);
                    textSwitch.setText("Particulier");
                    log.setBackgroundColor(Color.parseColor("#FF9A00"));
                    menu.setBackgroundColor(Color.parseColor("#FF9A00"));
                    log.setTextColor(Color.parseColor("#000000"));
                    menu.setTextColor(Color.parseColor("#000000"));
                } else {
                    System.out.println(isChecked);
                    textSwitch.setText("Commerçant");
                    getTheme().applyStyle(R.style.OverlayThemeMain, true);
                    log.setBackgroundColor(Color.parseColor("#AD0C0A"));
                    menu.setBackgroundColor(Color.parseColor("#AD0C0A"));
                    log.setTextColor(Color.parseColor("#FFFFFF"));
                    menu.setTextColor(Color.parseColor("#FFFFFF"));

                }
            }
        });

        menu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });

        log.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view){

                if (logcheck(mailInput.getText().toString(), pwInput.getText().toString())){
                    Toast valid = Toast.makeText(getApplicationContext(),"Redirecting...",Toast.LENGTH_SHORT);
                    valid.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 350);
                    valid.show();
                    boolean check = (boolean) (switch1.isChecked());
                    if (check){
                        Intent particulierActivity = new Intent(getApplicationContext(), ParticulierActivity.class);
                        startActivity(particulierActivity);
                        finish();
                    }else{
                        Intent comActivity = new Intent(getApplicationContext(), ComActivity.class);
                        startActivity(comActivity);
                        finish();
                    }
                }else{
                    Toast wrong = Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT);
                    wrong.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 350);
                    wrong.show();
                }
            }
        });



    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Boolean logcheck(String mail, String pw){
        DataBaseHelper dbHelper = new DataBaseHelper(LoginActivity.this);
        UserModel loggedUser = dbHelper.login(mail, pw);
        if (loggedUser != null){
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("User_ID", loggedUser.getId());
            editor.putString("User_Surname", loggedUser.getPrenom());
            editor.putString("User_Name", loggedUser.getNom());
            editor.putString("User_Mail", loggedUser.getEmail());
            editor.putBoolean("User_Role", loggedUser.getFonction());
            editor.putString("User_Favs", loggedUser.getFavoris().stream().map(Object::toString).collect(Collectors.joining(", ")));
            editor.commit();
            return true;
        }
        return false;
    }
}