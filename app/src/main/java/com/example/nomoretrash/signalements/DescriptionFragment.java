package com.example.nomoretrash.signalements;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


import androidx.fragment.app.Fragment;

import com.example.nomoretrash.R;


public class DescriptionFragment extends Fragment {

    public static boolean DECHET_UNIQUE = false;
    public static boolean DECHARGE_SAUVAGE = false;



    public static boolean VERRE = false;
    public static boolean CARTON = false;
    public static boolean PAPIER = false;
    public static boolean PLASTIQUE = false;
    public static boolean METAL = false;
    public static boolean AUTRE = false;


    public static boolean GROS = false;
    public static boolean PETIT = false;



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
        //Création des CheckBox
        checkBoxCreation(rootView);

        final CheckBox checkbox_autre = rootView.findViewById(R.id.checkbox_autre);

        EditText text_autre = rootView.findViewById(R.id.preciser);
        text_autre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!String.valueOf(s).equals(""))
                    checkbox_autre.setChecked(true);
                else
                    checkbox_autre.setChecked(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return rootView;
    }



    private void checkBoxCreation(View rootView) {
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
                if (!DECHET_UNIQUE)
                    DECHET_UNIQUE = true;
                else
                    DECHET_UNIQUE = false;

                Log.d("DECHET_UNIQUE", DECHET_UNIQUE + "");
            }
        });

        checkbox_decharge_sauvage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DECHARGE_SAUVAGE = !DECHARGE_SAUVAGE;

                Log.d("DECHARGE_SAUVAGE", DECHARGE_SAUVAGE + "");
            }
        });

        checkbox_verre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VERRE = !VERRE;

                Log.d("VERRE", VERRE + "");
            }
        });

        checkbox_carton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CARTON= !CARTON;

                Log.d("CARTON", CARTON + "");
            }
        });

        checkbox_papier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PAPIER = !PAPIER;

                Log.d("PAPIER", PAPIER + "");
            }
        });

        checkbox_plastique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PLASTIQUE = !PLASTIQUE;

                Log.d("PLASTIQUE", PLASTIQUE + "");
            }
        });

        checkbox_metal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                METAL = !METAL;

                Log.d("METAL", METAL + "");
            }
        });

        checkbox_autre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AUTRE = !AUTRE ;

                Log.d("AUTRE", AUTRE + "");
            }
        });

        checkbox_gros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GROS = !GROS;

                Log.d("GROS", GROS + "");
            }
        });

        checkbox_petit.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                PETIT = !PETIT;


                Log.d("PETIT", PETIT + "");
            }
        });
    /*
    ########################### GETTERS ###########################
     */
    }
    public static boolean isDechetUnique() {
        return DECHET_UNIQUE;
    }

    public static boolean isDechargeSauvage() {
        return DECHARGE_SAUVAGE;
    }

    public static boolean isVERRE() {
        return VERRE;
    }

    public static boolean isCARTON() {
        return CARTON;
    }

    public static boolean isPAPIER() {
        return PAPIER;
    }

    public static boolean isPLASTIQUE() {
        return PLASTIQUE;
    }

    public static boolean isMETAL() {
        return METAL;
    }

    public static boolean isAUTRE() {
        return AUTRE;
    }

    public static boolean isPETIT() {
        return PETIT;
    }

    public static boolean isGROS() {
        return GROS;
    }
}
