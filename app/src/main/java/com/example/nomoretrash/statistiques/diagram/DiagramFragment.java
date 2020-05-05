package com.example.nomoretrash.statistiques.diagram;


import androidx.fragment.app.Fragment;

public abstract class DiagramFragment extends Fragment {
    public static float[] yData = null;
    public static String[] xData = { "Verre", "Carton", "Papier", "Plastique", "Metal", "Autre" };


    public float[] getyData() {
        return yData;
    }

    public String[] getxData() {
        return xData;
    }
}