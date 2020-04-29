package com.example.nomoretrash;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.nomoretrash.signalements.MesSignalementsActivity;
import com.example.nomoretrash.signalements.SignalementActivity;
import com.example.nomoretrash.signalements.SignalementObject;
import com.example.nomoretrash.statistiques.StatistiquesActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements SignalementsObjectsList {

    public static final int ActivitySignalementRequestCode = 2;
    private ArrayList<String> signalementsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signalementsList = new ArrayList<>();

        final Button buttonStat = findViewById(R.id.boutonStatistiques);
        buttonStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StatistiquesActivity.class);
                startActivity(intent);
            }
        });

        final Button buttonSignalement = findViewById(R.id.boutonSignalement);
        buttonSignalement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignalementActivity.class);
                startActivityForResult(intent, ActivitySignalementRequestCode);
                //startActivity(intent);
            }
        });

        final Button button = findViewById(R.id.boutonMesSignalements);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MesSignalementsActivity.class);
                intent.putExtra("ma_liste_de_signalements", setRecap(SignalementsObjectsList.signalementsObjetsArray));
                startActivity(intent);
            }
        });

        //Section aide
        TextView textView = findViewById(R.id.contact);

        String text = "Une question ? Un problème ? Cliquez ici !";
        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(intent);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);
                ds.setUnderlineText(false);
            }
        };
        ss.setSpan(clickableSpan, 0, 41, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (ActivitySignalementRequestCode): {
                if (resultCode == Activity.RESULT_OK) {
                    String returnValue = data.getStringExtra("mon_signalement");
                    signalementsList.add(returnValue);
                    Log.d("recap_signalement : ", returnValue);
                }
                break;
            }
        }
    }

    public ArrayList<String> setRecap(ArrayList<SignalementObject> signalementObjectArray) {
        ArrayList<String> res = new ArrayList<>();
        for(SignalementObject signalementObject : signalementObjectArray) {
            String recap = "Recapitulatif : ";
            if (signalementObject.isDECHET_UNIQUE() || signalementObject.isDECHARGE_SAUVAGE()) {
                if (signalementObject.isDECHET_UNIQUE())
                    recap += "dechet unique";
                else
                    recap += "décharge sauvage";

                if (signalementObject.isVERRE() || signalementObject.isCARTON() || signalementObject.isPAPIER() || signalementObject.isPLASTIQUE() || signalementObject.isMETAL() || signalementObject.isAUTRE()) {
                    recap += ", composé de";
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
                        recap += " autre,";
                    // TODO: 12/04/2020 a modifier en fonction de se qu'écrit l'utilisateur
                    if (signalementObject.isGROS() || signalementObject.isPETIT()) {
                        if (signalementObject.isGROS())
                            recap += " mesurant plus de 30 cm";
                        else
                            recap += " mesurant moins de 30 cm";

                        if (signalementObject.haveLocalisation()) {
                            recap += "\n" + signalementObject.getLocalisation();
                        } else {
                            recap += "\nLocalisation non renseignée";
                        }
                    }
                }
                recap+="\nStatut :" + signalementObject.getStatus();
            }
            res.add(recap);
        }
        return res;
    }
}


