package com.example.nomoretrash.signalements;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.nomoretrash.MainActivity;
import com.example.nomoretrash.PageAdapter;
import com.example.nomoretrash.R;
import com.example.nomoretrash.statistiques.StatistiquesActivity;
import com.google.android.material.tabs.TabLayout;

public class SignalementActivity extends AppCompatActivity {

    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signalements);

        //Configure ViewPager
        this.configureViewPagerAndTabs();

        final Button buttonSuivant = findViewById(R.id.boutonSuivant);
        buttonSuivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pager.getCurrentItem()==3)
                    SignalementActivity.this.finish();
                else {
                    pager.arrowScroll(View.FOCUS_RIGHT);
                }

            }
        });

        final Button buttonRetour = findViewById(R.id.boutonRetour);
        buttonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pager.getCurrentItem()==0)
                    SignalementActivity.this.finish();
                else {
                    pager.arrowScroll(View.FOCUS_LEFT);
                }


            }
        });


    }

    private void configureViewPagerAndTabs(){
        //Get ViewPager from layout
        pager = (ViewPager)findViewById(R.id.activity_main_viewpager);
        //Set Adapter PageAdapter and glue it together
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()));

        TabLayout tabs= (TabLayout)findViewById(R.id.activity_main_tabs);
        tabs.setupWithViewPager(pager);
        tabs.setTabMode(TabLayout.MODE_FIXED);
    }


    }
