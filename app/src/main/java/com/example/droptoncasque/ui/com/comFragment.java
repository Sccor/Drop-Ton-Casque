package com.example.droptoncasque.ui.com;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.droptoncasque.ComActivity;
import com.example.droptoncasque.CommerceModel;
import com.example.droptoncasque.DataBaseCommerces;
import com.example.droptoncasque.MainActivity;
import com.example.droptoncasque.MapsActivity;
import com.example.droptoncasque.R;
import com.example.droptoncasque.ui.user.UserViewModel;

import java.util.List;

public class comFragment extends Fragment {

    private ComViewModel ComViewModel;

    public static comFragment newInstance() {
        return new comFragment();
    }
    DataBaseCommerces dbHelper = new DataBaseCommerces(getContext());

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ComViewModel = new ViewModelProvider(this).get(ComViewModel.class);
        View root = inflater.inflate(R.layout.fragment_com, container, false);
        Button menu = (Button) root.findViewById(R.id.button_menu);
        Button add = (Button) root.findViewById(R.id.btnAjout);

        EditText Nom = root.findViewById(R.id.comNom);
        EditText Type = root.findViewById(R.id.comType);
        EditText Email = root.findViewById(R.id.comEmail);
        EditText Tel = root.findViewById(R.id.comTel);
        EditText Adresse = root.findViewById(R.id.comAdresse);
        EditText Url = root.findViewById(R.id.comUrl);
        EditText Horaires = root.findViewById(R.id.comHoraire);

        add.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view){
                CommerceModel newCommerce;
                try {
                    newCommerce = new CommerceModel(-1, Nom.getText().toString(), Type.getText().toString(), Adresse.getText().toString(), Email.getText().toString(), Tel.getText().toString(), Url.getText().toString(), Horaires.getText().toString(), new MapsActivity.Pair<Double, Double>(48.79, (Double)(Math.random() * ((2.40 - 2.35)+1))+2.35));
                    final boolean success = dbHelper.addOne(newCommerce);
                    Toast valid = Toast.makeText(getContext().getApplicationContext(),"Added",Toast.LENGTH_SHORT);
                    valid.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 350);
                    valid.show();

                } catch (Exception e) {
                    newCommerce = new CommerceModel(-1, "error", null, null, null, null,null, null, null);
                    Toast wrong = Toast.makeText(getContext().getApplicationContext(),"Error",Toast.LENGTH_SHORT);
                    System.out.println(e.toString());
                    wrong.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 350);
                    wrong.show();
                }
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ComViewModel = new ViewModelProvider(this).get(ComViewModel.class);
        // TODO: Use the ViewModel
    }

}