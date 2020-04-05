package com.example.nomoretrash;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.nomoretrash.signalements.DescriptionFragment;
import com.example.nomoretrash.signalements.LocalisationFragment;

public class PageAdapter extends FragmentPagerAdapter {


    //Default Constructor
    public PageAdapter(FragmentManager mgr) {
        super(mgr);
    }

    @Override
    public int getCount() {
        return(2);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return DescriptionFragment.newInstance();
            case 1:
                return LocalisationFragment.newInstance();
//            case 2:
//                return PhotoFragment.newInstance();
//            case 3:
//                return FinalisationFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Description";
            case 1:
                return "Localisation";
//            case 2:
//                return "Photo";
//            case 3:
//                return "Finalisation";
            default:
                return null;
        }
    }
}