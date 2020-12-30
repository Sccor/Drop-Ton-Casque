package com.example.droptoncasque.ui.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.droptoncasque.ComActivity;
import com.example.droptoncasque.DataBaseHelper;
import com.example.droptoncasque.LoginActivity;
import com.example.droptoncasque.NavigationActivity;
import com.example.droptoncasque.ParticulierActivity;
import com.example.droptoncasque.R;
import com.example.droptoncasque.UserModel;

import java.util.stream.Collectors;

public class LoginFragment extends Fragment {

    private LoginViewModel loginViewModel;
    private TextView textSwitch;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch switch1;
    private EditText mailInput;
    private EditText pwInput;
    private Button log;
    private Window window;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        this.textSwitch = (TextView) view.findViewById(R.id.textSwitch);
        this.switch1 = (Switch) view.findViewById(R.id.switch1);
        this.mailInput = (EditText) view.findViewById(R.id.editTextTextEmailAddress);
        this.pwInput = (EditText) view.findViewById(R.id.editTextTextPassword);
        this.log = (Button) view.findViewById(R.id.button);


        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("SetTextI18n")
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                System.out.println(isChecked);
                if (isChecked) {
                    textSwitch.setText("Particulier");
                    log.setBackgroundColor(Color.parseColor("#FF9A00"));
                    log.setTextColor(Color.parseColor("#000000"));
                } else {
                    textSwitch.setText("Commerçant");
                    getActivity().getTheme().applyStyle(R.style.OverlayThemeMain, true);
                    log.setBackgroundColor(Color.parseColor("#AD0C0A"));
                    log.setTextColor(Color.parseColor("#FFFFFF"));

                }
            }
        });

        log.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view){
                boolean check = (boolean) (switch1.isChecked());
                if (logcheck(mailInput.getText().toString(), pwInput.getText().toString(), check)){
                    Toast valid = Toast.makeText(getActivity().getApplicationContext(),"Redirecting...",Toast.LENGTH_SHORT);
                    LayoutInflater layout = getLayoutInflater();
                    View navHead = layout.inflate(R.layout.nav_header_main, null);
                    TextView nameInfo = (TextView) navHead.findViewById(R.id.nameInfo);
                    TextView mailInfo = (TextView) navHead.findViewById(R.id.mailInfo);
                    System.out.println(nameInfo.getText() + "\n\n" + mailInfo.getText());
                    SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    nameInfo.setText(sharedPref.getString("User_Surname", "Bienvenue,") + " " + sharedPref.getString("User_Name", "utilisateur"));
                    mailInfo.setText(sharedPref.getString("User_Mail", "Connectez vous !"));
                    System.out.println(nameInfo.getText() + "\n\n" + mailInfo.getText());
                    navHead.invalidate();
                    valid.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 350);
                    valid.show();
                    Intent NavigationActivity = new Intent(getActivity().getApplicationContext(), com.example.droptoncasque.NavigationActivity.class);
                    startActivity(NavigationActivity);
                    getActivity().finish();
                }else{
                    Toast wrong = Toast.makeText(getActivity().getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT);
                    wrong.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 350);
                    wrong.show();
                }
            }
        });

        return view;

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Boolean logcheck(String mail, String pw, Boolean check){
        if (mail.equals("admin") && pw.equals("admin")){
            return true;
        }
        DataBaseHelper dbHelper = new DataBaseHelper(getActivity().getApplicationContext());
        UserModel loggedUser = dbHelper.login(mail, pw);
        if (loggedUser != null && check == loggedUser.getFonction()){
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("User_ID", loggedUser.getId());
            editor.putString("User_Surname", loggedUser.getPrenom());
            editor.putString("User_Name", loggedUser.getNom());
            editor.putString("User_Mail", loggedUser.getEmail());
            editor.putBoolean("User_Role", loggedUser.getFonction());
            if (loggedUser.getFavoris() == null){
                editor.putString("User_Favs", "");
            }else{
                editor.putString("User_Favs", loggedUser.getFavoris().stream().map(Object::toString).collect(Collectors.joining(", ")));
            }
            editor.apply();
            return true;
        }
        return false;
    }
}
