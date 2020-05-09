package com.example.nomoretrash.signalements.signaler;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.nomoretrash.R;
import com.example.nomoretrash.signalements.SignalementObject;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DescriptionFragment extends Fragment {

    public static SignalementObject signalementObject;



    public DescriptionFragment() {
        //Création d'un nouveau objet Signalement
        signalementObject = new SignalementObject();
        //Ajout de la date de création du signalement
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String result = formatter.format(date);
        signalementObject.setDate(result);
    }

    public static DescriptionFragment newInstance() {
        return (new DescriptionFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.description_fragment, container, false);

        Button boutonAnnuler = rootView.findViewById(R.id.boutonAnnuler);
        boutonAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DescriptionFragment.this.getActivity().finish();
                reinitialisateColorText(rootView);

            }
        });
        //Création des CheckBox
        checkBoxCreation(rootView);

        //Pour cocher automatiquement "autre" si l'editText est completé
        final CheckBox checkboxAutre = rootView.findViewById(R.id.checkbox_autre);
        final EditText textAutre = rootView.findViewById(R.id.preciser);
        textAutre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!String.valueOf(s).equals("")) {
                    checkboxAutre.setChecked(true);
                    signalementObject.changeAUTRE();
                }
                else {
                    checkboxAutre.setChecked(false);
                    signalementObject.changeAUTRE();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!String.valueOf(s).equals("")) {
                    checkboxAutre.setChecked(true);
                    signalementObject.setAUTRE(true);
                }
                else {
                    checkboxAutre.setChecked(false);
                    signalementObject.setAUTRE(false);
                }

            }
        });
        changeColorsText(rootView);//Met en rouge les champs manquants

        return rootView;
    }

    private void reinitialisateColorText(View rootView) {
        FinalisationFragment.reinitialisatePartCompleted();
        TextView infos = rootView.findViewById(R.id.infos_generales);
        TextView type = rootView.findViewById(R.id.type);
        TextView taille = rootView.findViewById(R.id.taille);

        type.setTextColor(Color.BLACK);
    }

    private void changeColorsText(View rootView) {
        TextView type = rootView.findViewById(R.id.type);

        if(FinalisationFragment.notComplete && !FinalisationFragment.part2) {
            type.setTextColor(Color.RED);
        }
    }


    private void checkBoxCreation(View rootView) {
        final RadioButton checkboxDechetUnique = rootView.findViewById(R.id.radio_dechetUnique);
        final RadioButton checkboxDechargeSauvage = rootView.findViewById(R.id.radio_dechargeSauvage);
        CheckBox checkboxVerre = rootView.findViewById(R.id.checkbox_verre);
        CheckBox checkboxCarton = rootView.findViewById(R.id.checkbox_carton);
        CheckBox checkboxPapier = rootView.findViewById(R.id.checkbox_papier);
        CheckBox checkboxPlastique = rootView.findViewById(R.id.checkbox_plastique);
        CheckBox checkboxMetal = rootView.findViewById(R.id.checkbox_metal);
        CheckBox checkboxAutre = rootView.findViewById(R.id.checkbox_autre);
        final RadioButton  checkboxPetit = rootView.findViewById(R.id.radio_petit);
        final RadioButton  checkboxGros = rootView.findViewById(R.id.radio_grand);


        checkboxDechetUnique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signalementObject.setDECHARGE_SAUVAGE(false);
                signalementObject.setDECHET_UNIQUE(true);
            }
        });

        checkboxDechargeSauvage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signalementObject.setDECHARGE_SAUVAGE(true);
                signalementObject.setDECHET_UNIQUE(false);
            }
        });

        checkboxVerre.setOnClickListener(view -> signalementObject.changeVERRE());

        checkboxCarton.setOnClickListener(view -> signalementObject.changeCARTON());

        checkboxPapier.setOnClickListener(view -> signalementObject.changePAPIER());

        checkboxPlastique.setOnClickListener(view -> signalementObject.changePLASTIQUE());

        checkboxMetal.setOnClickListener(view -> signalementObject.changeMETAL());

        checkboxAutre.setOnClickListener(view -> signalementObject.changeAUTRE());

        checkboxGros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signalementObject.setGROS(true);
                signalementObject.setPETIT(false);
            }
        });

        checkboxPetit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signalementObject.setPETIT(true);
                signalementObject.setGROS(false);
            }
        });
    }




    public static SignalementObject getSignalementObject() {
        return signalementObject;
    }
}
