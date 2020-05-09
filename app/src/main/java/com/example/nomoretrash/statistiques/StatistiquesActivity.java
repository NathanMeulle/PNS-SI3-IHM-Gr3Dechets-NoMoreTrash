package com.example.nomoretrash.statistiques;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nomoretrash.R;
import com.example.nomoretrash.statistiques.diagram.DiagramBaton;
import com.example.nomoretrash.statistiques.diagram.DiagramCircle;
import com.example.nomoretrash.statistiques.diagram.DiagramFragment;

import java.util.ArrayList;
import java.util.List;

public class StatistiquesActivity extends AppCompatActivity {

    private int nbVerre;
    private int nbCarton;
    private int nbPapier;
    private int nbPlastique;
    private int nbMetal;
    private int nbAutre;

    private static final int BATON = 1;
    private static final int CIRCULAIRE = 2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistiques);


        final Button buttonRetour = findViewById(R.id.boutonRetour);
        buttonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StatistiquesActivity.this.finish();

            }
        });

        final Button buttonCercle = findViewById(R.id.boutonCercle);
        buttonCercle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    createDiagram(CIRCULAIRE);

                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                getSupportFragmentManager().beginTransaction().replace((R.id.fragment_stat), new DiagramCircle()).commit();
            }
        });

        final Button buttonBaton = findViewById(R.id.boutonBaton);
        buttonBaton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    createDiagram(BATON);

                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                getSupportFragmentManager().beginTransaction().replace((R.id.fragment_stat), new DiagramBaton()).commit();

            }
        });

        Intent intent = getIntent();
        if (intent.hasExtra("ma_liste_de_signalements")) {
            ArrayList<String> res = intent.getStringArrayListExtra("ma_liste_de_signalements");
            setStat(res);
        }


    }

    private void setStat(ArrayList<String> res) {
        nbVerre = 0;
        nbCarton = 0;
        nbPapier = 0;
        nbPlastique = 0;
        nbMetal = 0;
        nbAutre = 0;
        for (String str : res) {
            if (str.contains("verre"))
                nbVerre++;
            if (str.contains("carton"))
                nbCarton++;
            if (str.contains("papier"))
                nbPapier++;
            if (str.contains("plastique"))
                nbPlastique++;
            if (str.contains("métal"))
                nbMetal++;
            if (str.contains("autre"))
                nbAutre++;
        }
        float[] ydata = {nbVerre, nbCarton, nbPapier, nbPlastique, nbMetal, nbAutre};
        String[] xdata = { "Verre", "Carton", "Papier", "Plastique", "Metal", "Autre" };
        List<String> xSol = new ArrayList<>();
        List<Float> ySol = new ArrayList<>();

        for(int i =0; i<6; i++){
            if(ydata[i]!=0){
                xSol.add(xdata[i]);
                ySol.add(ydata[i]);
            }
        }
        DiagramFragment.xData = xSol.toArray(new String[xSol.size()]);
        final float[] ySolArray = new float[ySol.size()];
        int index = 0;
        for (final Float value: ySol) {
            ySolArray[index++] = value;
        }

        DiagramFragment.yData = ySolArray;
    }

    static DiagramFragment createDiagram(int type) throws Throwable {
        switch (type){
            case BATON: return DiagramBaton.newInstance();
            case CIRCULAIRE: return DiagramCircle.newInstance();
            default: throw new Throwable("erreur type incorrect");
        }
    }

}
