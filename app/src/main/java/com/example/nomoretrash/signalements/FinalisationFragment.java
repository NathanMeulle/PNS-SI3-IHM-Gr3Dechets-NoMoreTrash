package com.example.nomoretrash.signalements;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.nomoretrash.R;

public class FinalisationFragment extends Fragment {
    public FinalisationFragment(){
        //vide
    }

    public static FinalisationFragment newInstance() {
        return (new FinalisationFragment());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.finalisation_fragment, container, false);


        return rootView;
    }
}
