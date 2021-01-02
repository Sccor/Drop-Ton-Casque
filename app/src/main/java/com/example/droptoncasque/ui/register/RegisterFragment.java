package com.example.droptoncasque.ui.register;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import com.example.droptoncasque.DataBaseHelper;
import com.example.droptoncasque.InscriptionActivity;
import com.example.droptoncasque.R;
import com.example.droptoncasque.UserModel;

public class RegisterFragment extends Fragment  {

    private RegisterViewModel registerViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        super.onCreate(savedInstanceState);
        Button btnInsc = (Button) view.findViewById(R.id.btnInscri);
        EditText Nom = view.findViewById(R.id.PersonName);
        EditText Prenom = view.findViewById(R.id.PersonSurname);
        EditText Email = view.findViewById(R.id.EmailAddress);
        EditText Mdp = view.findViewById(R.id.Password1);
        EditText Cmdp = view.findViewById(R.id.Password2);
        TextView textSwitch = (TextView) view.findViewById(R.id.textSwitch);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch switch1 = (Switch) view.findViewById(R.id.switch1);

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("SetTextI18n")
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    textSwitch.setText("Particulier");
                    btnInsc.setBackgroundColor(Color.parseColor("#FF9A00"));
                    btnInsc.setTextColor(Color.parseColor("#000000"));
                } else {
                    textSwitch.setText("Commerçant");
                    getActivity().getTheme().applyStyle(R.style.OverlayThemeMain, true);
                    btnInsc.setBackgroundColor(Color.parseColor("#AD0C0A"));
                    btnInsc.setTextColor(Color.parseColor("#FFFFFF"));

                }
            }
        });

        btnInsc.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view){
                UserModel newUser;
                try {
                    if(Mdp.getText().toString().equals(Cmdp.getText().toString())){
                        if(textSwitch.getText().toString().equals("Particulier")){
                            newUser = new UserModel(-1, Nom.getText().toString(), Prenom.getText().toString(), Email.getText().toString(), 1, "");
                        }else{
                            newUser = new UserModel(-1, Nom.getText().toString(), Prenom.getText().toString(), Email.getText().toString(), 0, "");
                        }
                        DataBaseHelper dbHelper = new DataBaseHelper(getActivity().getApplicationContext());
                        dbHelper.addOne(newUser, Mdp.getText().toString());
                        Toast valid = Toast.makeText(getActivity().getApplicationContext(),"Redirecting...",Toast.LENGTH_SHORT);
                        valid.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 350);
                        valid.show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    newUser = new UserModel(-1, "error", "e", "error", 1, "");
                    Toast wrong = Toast.makeText(getActivity().getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT);
                    wrong.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 350);
                    wrong.show();
                }
            }
        });

        return view;
    }
}