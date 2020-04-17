package com.example.nomoretrash.signalements;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


import androidx.fragment.app.Fragment;

import com.example.nomoretrash.R;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DescriptionFragment extends Fragment {

    public static SignalementObject signalementObject;



    public DescriptionFragment() {
        //Création d'un nouveau objet Signalement
        signalementObject = new SignalementObject();
        //Ajout de la date de création du signalement
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/YYYY HH:mm");
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
        final CheckBox checkbox_autre = rootView.findViewById(R.id.checkbox_autre);
        final EditText text_autre = rootView.findViewById(R.id.preciser);
        text_autre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!String.valueOf(s).equals("")) {
                    checkbox_autre.setChecked(true);
                    signalementObject.changeAUTRE();

                }

                else {
                    checkbox_autre.setChecked(false);
                    signalementObject.changeAUTRE();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!String.valueOf(s).equals("")) {
                    checkbox_autre.setChecked(true);
                    signalementObject.setAUTRE(true);
                }
                else {
                    checkbox_autre.setChecked(false);
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

        infos.setTextColor(Color.BLACK);
        type.setTextColor(Color.BLACK);
        taille.setTextColor(Color.BLACK);
    }

    private void changeColorsText(View rootView) {
        TextView infos = rootView.findViewById(R.id.infos_generales);
        TextView type = rootView.findViewById(R.id.type);
        TextView taille = rootView.findViewById(R.id.taille);

        if(FinalisationFragment.notComplete && !FinalisationFragment.part1) {
            infos.setTextColor(Color.RED);
        }

        if(FinalisationFragment.notComplete && !FinalisationFragment.part2) {
            type.setTextColor(Color.RED);
        }
        if(FinalisationFragment.notComplete && !FinalisationFragment.part3) {
            taille.setTextColor(Color.RED);
        }
    }


    private void checkBoxCreation(View rootView) {
        final CheckBox checkBox_dechet_unique = rootView.findViewById(R.id.checkbox_dechet_unique);
        final CheckBox checkbox_decharge_sauvage = rootView.findViewById(R.id.checkbox_decharge_sauvage);
        CheckBox checkbox_verre = rootView.findViewById(R.id.checkbox_verre);
        CheckBox checkbox_carton = rootView.findViewById(R.id.checkbox_carton);
        CheckBox checkbox_papier = rootView.findViewById(R.id.checkbox_papier);
        CheckBox checkbox_plastique = rootView.findViewById(R.id.checkbox_plastique);
        CheckBox checkbox_metal = rootView.findViewById(R.id.checkbox_metal);
        CheckBox checkbox_autre = rootView.findViewById(R.id.checkbox_autre);
        final CheckBox checkbox_petit = rootView.findViewById(R.id.checkbox_petit);
        final CheckBox checkbox_gros = rootView.findViewById(R.id.checkbox_gros);


        checkBox_dechet_unique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signalementObject.changeDECHET_UNIQUE();
                if (signalementObject.isDECHET_UNIQUE() && signalementObject.isDECHARGE_SAUVAGE()) {
                    signalementObject.changeDECHARGE_SAUVAGE();
                    checkbox_decharge_sauvage.setChecked(false);
                }
            }
        });

        checkbox_decharge_sauvage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signalementObject.changeDECHARGE_SAUVAGE();
                if (signalementObject.isDECHET_UNIQUE() && signalementObject.isDECHARGE_SAUVAGE()) {
                    signalementObject.changeDECHET_UNIQUE();
                    checkBox_dechet_unique.setChecked(false);
                }
            }
        });

        checkbox_verre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signalementObject.changeVERRE();
            }
        });

        checkbox_carton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signalementObject.changeCARTON();
            }
        });

        checkbox_papier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signalementObject.changePAPIER();
            }
        });

        checkbox_plastique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signalementObject.changePLASTIQUE();
            }
        });

        checkbox_metal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signalementObject.changeMETAL();
            }
        });

        checkbox_autre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signalementObject.changeAUTRE();
            }
        });

        checkbox_gros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signalementObject.changeGROS();
                if (signalementObject.isGROS() && signalementObject.isPETIT())
                    signalementObject.changePETIT();
                    checkbox_petit.setChecked(false);
            }
        });

        checkbox_petit.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                signalementObject.changePETIT();
                if (signalementObject.isGROS() && signalementObject.isPETIT())
                    signalementObject.changeGROS();
                    checkbox_gros.setChecked(false);
            }
        });
    }




    public static SignalementObject getSignalementObject() {
        return signalementObject;
    }
}
