package com.example.droptoncasque.ui.user;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.versionedparcelable.CustomVersionedParcelable;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.droptoncasque.CommerceModel;
import com.example.droptoncasque.DataBaseCommerces;
import com.example.droptoncasque.DataBaseHelper;
import com.example.droptoncasque.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class UserFragment extends Fragment {

    private UserViewModel userViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        View root = inflater.inflate(R.layout.fragment_user, container, false);
        androidx.recyclerview.widget.RecyclerView listFavs = (androidx.recyclerview.widget.RecyclerView) getView().findViewById(R.id.list_favs);
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
//        ArrayAdapter arrAdpt = new ArrayAdapter<CommerceModel>(getActivity(), android.R.layout.simple_list_item_1, curentFavs);
//        listFavs.setAdapter(arrAdpt);

//        Toast.makeText(getActivity(), curentFavs.toString(), Toast.LENGTH_LONG).show();

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