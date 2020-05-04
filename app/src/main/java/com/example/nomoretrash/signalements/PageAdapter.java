package com.example.nomoretrash.signalements;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.nomoretrash.signalements.signaler.DescriptionFragment;
import com.example.nomoretrash.signalements.signaler.FinalisationFragment;
import com.example.nomoretrash.signalements.signaler.LocalisationFragment;
import com.example.nomoretrash.signalements.signaler.PhotoFragment;

public class PageAdapter extends FragmentPagerAdapter {


    //Default Constructor
    @Deprecated
    public PageAdapter(FragmentManager mgr) {
        super(mgr);
    }



    @Override
    public int getCount() {
        return(4);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return DescriptionFragment.newInstance();
            case 1:
                return LocalisationFragment.newInstance();
            case 2:
                return PhotoFragment.newInstance();
            case 3:
                return FinalisationFragment.newInstance();
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
            case 2:
                return "Photo";
            case 3:
                return "Validation";
            default:
                return null;
        }
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}