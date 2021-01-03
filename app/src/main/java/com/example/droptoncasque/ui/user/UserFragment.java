package com.example.droptoncasque.ui.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.versionedparcelable.CustomVersionedParcelable;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.droptoncasque.ComInfoActivity;
import com.example.droptoncasque.CommerceModel;
import com.example.droptoncasque.DataBaseCommerces;
import com.example.droptoncasque.DataBaseHelper;
import com.example.droptoncasque.R;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A fragment representing a list of Items.
 */
public class UserFragment extends Fragment {

    private UserViewModel userViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        View root = inflater.inflate(R.layout.fragment_user, container, false);
        ListView listFavs = (ListView)  root.findViewById(R.id.list_favs);
//        androidx.recyclerview.widget.RecyclerView listFavs = (androidx.recyclerview.widget.RecyclerView) getView().findViewById(R.id.list_favs);
        DataBaseCommerces databasecom = new DataBaseCommerces(getActivity());
        DataBaseHelper databaseuse = new DataBaseHelper(getActivity());
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Integer userId = sharedPref.getInt("User_ID", -1);
        ArrayList<Integer> favs =  databaseuse.getUser(userId).getFavoris();
        List<CommerceModel> allFavs = databasecom.getAllCommerces();
        List<CommerceModel> curentFavs = null;

        if(favs != null){
            curentFavs = databasecom.getSpeCommerces(favs);
        }

        class Model{
            private String nom;
            private String type;
            private String adresse;

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

            @Override
            public String toString() {
                return nom + '\n' + type + '\n' + adresse + '\n';
            }

            public Model(String nom, String type, String adresse) {
                this.nom = nom;
                this.type = type;
                this.adresse = adresse;
            }
        }
        if (curentFavs != null) {
            ArrayList<Model> listFavos = new ArrayList<Model>();
            for(CommerceModel favos : curentFavs){
                listFavos.add(new Model(favos.getNom(), favos.getType(), favos.getAdresse()));
            }

            ArrayAdapter<Model> arrAdpt = new ArrayAdapter<Model>(Objects.requireNonNull(getActivity()), android.R.layout.simple_list_item_1, listFavos);
            listFavs.setAdapter(arrAdpt);
        }


        listFavs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Model clickedModel = (Model) parent.getItemAtPosition(position);
                Intent infoActivity = new Intent(getActivity(), ComInfoActivity.class);
                infoActivity.putExtra("title", clickedModel.getNom());
                startActivity(infoActivity);
            }
        });


        return root;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final NavController navController = Navigation.findNavController(view);
        if (sharedPref.contains("User_Role")){
            Boolean role = sharedPref.getBoolean("User_Role", true);
            if (role) {
                return;
            } else {
                navController.navigate(R.id.nav_com);
            }

        }
    }

}