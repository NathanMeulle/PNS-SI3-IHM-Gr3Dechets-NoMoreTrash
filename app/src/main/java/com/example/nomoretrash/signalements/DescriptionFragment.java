package com.example.nomoretrash.signalements;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.util.Log;


import androidx.fragment.app.Fragment;

import com.example.nomoretrash.R;

public class DescriptionFragment extends Fragment {

    private FinalisationFragment finalisationFragment;
    public static boolean DECHET_UNIQUE = false;


    public DescriptionFragment() {//vide
    }

    public static DescriptionFragment newInstance() {
        return (new DescriptionFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.description_fragment, container, false);


        Button boutonAnnuler = rootView.findViewById(R.id.boutonAnnuler);
        boutonAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DescriptionFragment.this.getActivity().finish();

            }
        });

        CheckBox checkBox_dechet_unique = rootView.findViewById(R.id.checkbox_dechet_unique);
        CheckBox checkbox_decharge_sauvage = rootView.findViewById(R.id.checkbox_decharge_sauvage);
        CheckBox checkbox_verre = rootView.findViewById(R.id.checkbox_verre);
        CheckBox checkbox_carton = rootView.findViewById(R.id.checkbox_carton);
        CheckBox checkbox_papier = rootView.findViewById(R.id.checkbox_papier);
        CheckBox checkbox_plastique = rootView.findViewById(R.id.checkbox_plastique);
        CheckBox checkbox_metal = rootView.findViewById(R.id.checkbox_metal);
        CheckBox checkbox_autre = rootView.findViewById(R.id.checkbox_autre);
        CheckBox checkbox_petit = rootView.findViewById(R.id.checkbox_petit);
        CheckBox checkbox_gros = rootView.findViewById(R.id.checkbox_gros);


        checkBox_dechet_unique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DECHET_UNIQUE = true;
                Log.d(DECHET_UNIQUE+"", DECHET_UNIQUE+"");
            }
        });






        return rootView;

    }

    public static boolean getDechetUnique() {
        return DECHET_UNIQUE;
    }
}
