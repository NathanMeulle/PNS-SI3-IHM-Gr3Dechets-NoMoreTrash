package com.example.nomoretrash.signalements;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.nomoretrash.R;

public class LocalisationFragment extends Fragment {
    public LocalisationFragment() {//vide
    }

    public static LocalisationFragment newInstance() {
        return (new LocalisationFragment());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.localisation_fragment, container, false);


        return rootView;
    }

}
