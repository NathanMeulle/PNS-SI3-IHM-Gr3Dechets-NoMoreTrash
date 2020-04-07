package com.example.nomoretrash.signalements;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.nomoretrash.R;

public class DescriptionFragment extends Fragment {
    public DescriptionFragment() {//vide
    }

    public static DescriptionFragment newInstance() {
        return (new DescriptionFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.description_fragment, container, false);


        return rootView;
    }


}
