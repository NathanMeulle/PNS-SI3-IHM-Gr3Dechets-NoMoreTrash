package com.example.nomoretrash.signalements;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.nomoretrash.PageAdapter;
import com.example.nomoretrash.R;
import com.google.android.material.tabs.TabLayout;

public class SignalementActivity extends AppCompatActivity {

    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signalements);

        //Configure ViewPager
        this.configureViewPagerAndTabs();


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
