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
        }


    }

}
