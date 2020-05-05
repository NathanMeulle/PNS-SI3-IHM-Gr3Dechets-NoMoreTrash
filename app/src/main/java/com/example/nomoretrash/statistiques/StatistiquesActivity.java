package com.example.nomoretrash.statistiques;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nomoretrash.R;

import java.util.ArrayList;
import java.util.HashMap;

public class StatistiquesActivity extends AppCompatActivity {

    private int nbVerre;
    private int nbCarton;
    private int nbPapier;
    private int nbPlastique;
    private int nbMetal;
    private int nbAutre;

    private static final int Baton = 1;
    private static final int Circulaire = 2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistiques);

        final Button buttonStat = findViewById(R.id.boutonRetour);
        buttonStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StatistiquesActivity.this.finish();

            }
        });

        Intent intent = getIntent();
        if (intent.hasExtra("ma_liste_de_signalements")) {
            ArrayList<String> res = intent.getStringArrayListExtra("ma_liste_de_signalements");
            HashMap hashmap = setStat(res);
            try {
                createDiagram(1,hashmap);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }


    }

    private HashMap setStat(ArrayList<String> res) {
        nbVerre = 0;
        nbCarton = 0;
        nbPapier = 0;
        nbPlastique = 0;
        nbMetal = 0;
        nbAutre = 0;
        HashMap hashMap = new HashMap();

        for (String str : res) {
            if (str.contains("verre")) {
                nbVerre++;
            }
            if (str.contains("carton")) {
                nbCarton++;
            }
            if (str.contains("papier")) {
                nbPapier++;
            }
            if (str.contains("plastique")) {
                nbPlastique++;
            }
            if (str.contains("métal")) {
                nbMetal++;
            }
            if (str.contains("autre")) {
                nbAutre++;
            }
        }
        hashMap.put("verre",nbVerre);
        hashMap.put("carton",nbCarton);
        hashMap.put("papier",nbPapier);
        hashMap.put("plastique",nbPlastique);
        hashMap.put("metal",nbMetal);
        hashMap.put("autre",nbAutre);

        return hashMap;


    }

    static Diagram createDiagram(int type, HashMap hm) throws Throwable {
        switch (type){
            case Baton: return new diagramBaton(hm);
            case Circulaire: return new diagramCirculaire(hm);
            default: throw new Throwable("pas fait");
        }
    }
}
