package com.example.nomoretrash.signalements;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.nomoretrash.PageAdapter;
import com.example.nomoretrash.R;
import com.google.android.material.tabs.TabLayout;

public class SignalementActivity extends AppCompatActivity {

    public static ViewPager pager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signalements);

        //Configure ViewPager
        this.configureViewPagerAndTabs();

        //PageAdapter permettant de mettre à jour les fragments
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int CurrentFragment = 0;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                CurrentFragment = position;
                mPagerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


    }

    @Override
    public void onBackPressed() {//méthode pour changer la fonction du bouton retour
        int item = pager.getCurrentItem();
        if (item == 0)
            this.finish(); // si on sur description on revient sur l'écran d'accueil
        else
            pager.setCurrentItem(item - 1); //on revient sur la page précedente

    }

    private void configureViewPagerAndTabs() {
        //Get ViewPager from layout
        pager = (ViewPager) findViewById(R.id.activity_main_viewpager);
        //Set Adapter PageAdapter and glue it together
        mPagerAdapter = new PageAdapter(getSupportFragmentManager());
        pager.setAdapter(mPagerAdapter);

        TabLayout tabs = (TabLayout) findViewById(R.id.activity_main_tabs);
        tabs.setupWithViewPager(pager);
        tabs.setTabMode(TabLayout.MODE_AUTO);
    }


}
