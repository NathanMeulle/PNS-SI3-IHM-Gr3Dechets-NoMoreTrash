package com.example.nomoretrash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nomoretrash.map.MainMapActivity;
import com.example.nomoretrash.signalements.MesSignalementsActivity;
import com.example.nomoretrash.signalements.SignalementActivity;
import com.example.nomoretrash.statistiques.StatistiquesActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                startActivity(intent);
            }
        });

        final Button button = findViewById(R.id.boutonMesSignalements);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MesSignalementsActivity.class);
                startActivity(intent);
            }
        });

        final Button buttonMap = findViewById(R.id.boutonMap);
        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainMapActivity.class);
                startActivity(intent);
            }
        });
    }
}

