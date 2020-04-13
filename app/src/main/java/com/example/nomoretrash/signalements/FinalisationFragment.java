package com.example.nomoretrash.signalements;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.nomoretrash.R;

public class FinalisationFragment extends Fragment {

    private SignalementObject signalementObject;
    ImageView mImageView;

    public FinalisationFragment() {
        //on récupère l'objet signalemnt
        this.signalementObject = DescriptionFragment.getSignalementObject();
    }


    private String recap = "";

    private Boolean part1 = false;
    private Boolean part2 = false;
    private Boolean part3 = false;


    public static FinalisationFragment newInstance() {
        return (new FinalisationFragment());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        recap = "";

        View rootView = inflater.inflate(R.layout.finalisation_fragment, container, false);

        Button boutonFinaliser = rootView.findViewById(R.id.boutonFinir);
        boutonFinaliser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (part1 && part2 && part3) {
                    Toast.makeText(getContext(), "Votre signalement a bien été enregistré !", Toast.LENGTH_LONG).show();
                    FinalisationFragment.this.getActivity().finish();

                } else {
                    Toast.makeText(getContext(), "Des champs dans la  page description sont manquants", Toast.LENGTH_LONG).show();
                }
            }
        });

        setRecap();
        ((TextView) rootView.findViewById(R.id.recap)).setText(recap);

        //Affichage de la photo
        if (PhotoFragment.getImage_uri() != null) {
            mImageView = rootView.findViewById(R.id.photo);
            mImageView.setImageURI(PhotoFragment.getImage_uri());
            mImageView.setRotation(90);
        }


        return rootView;
    }

    public void setRecap() {
        recap = "Recapitulatif : ";
        if (signalementObject.isDECHET_UNIQUE() || signalementObject.isDECHARGE_SAUVAGE()) {
            part1 = true;
            if (signalementObject.isDECHET_UNIQUE())
                recap += "dechet unique";
            else
                recap += "décharge sauvage";
        } else part1 = false;

        if (signalementObject.isVERRE() || signalementObject.isCARTON() || signalementObject.isPAPIER() || signalementObject.isPLASTIQUE() || signalementObject.isMETAL() || signalementObject.isAUTRE()) {
            recap += ", composé de";
            part2 = true;
            if (signalementObject.isVERRE())
                recap += " verre,";
            if (signalementObject.isCARTON())
                recap += " carton,";
            if (signalementObject.isPAPIER())
                recap += " papier,";
            if (signalementObject.isPLASTIQUE())
                recap += " plastique,";
            if (signalementObject.isMETAL())
                recap += " métal,";
            if (signalementObject.isAUTRE())
                // TODO: 12/04/2020 a modifier en fonction de se qu'écrit l'utilisateur
                recap += " autre,";
        } else part2 = false;
        if (signalementObject.isGROS() || signalementObject.isPETIT()) {
            part3 = true;
            if (signalementObject.isGROS())
                recap += " mesurant plus de 30 cm";
            else
                recap += " mesurant moins de 30 cm";
        } else part3 = false;
    }
}

