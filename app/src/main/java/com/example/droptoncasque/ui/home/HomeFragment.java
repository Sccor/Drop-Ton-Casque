package com.example.droptoncasque.ui.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.droptoncasque.R;
import com.google.android.material.navigation.NavigationView;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if (sharedPref.contains("User_Role")){
            Menu nav_menu = navigationView.getMenu();
            nav_menu.findItem(R.id.nav_login).setVisible(false);
            nav_menu.findItem(R.id.nav_register).setVisible(false);
            nav_menu.findItem(R.id.nav_user).setVisible(true);
            nav_menu.findItem(R.id.nav_com).setVisible(!sharedPref.getBoolean("User_Role", true));
        }else{
            Menu nav_menu = navigationView.getMenu();
            nav_menu.findItem(R.id.nav_login).setVisible(true);
            nav_menu.findItem(R.id.nav_register).setVisible(true);
            nav_menu.findItem(R.id.nav_user).setVisible(false);
            nav_menu.findItem(R.id.nav_com).setVisible(false);
        }
        return root;
    }



}