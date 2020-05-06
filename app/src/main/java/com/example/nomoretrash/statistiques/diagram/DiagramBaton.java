package com.example.nomoretrash.statistiques.diagram;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import androidx.fragment.app.Fragment;

import com.example.nomoretrash.R;

import java.util.ArrayList;
import java.util.List;

public class DiagramBaton extends DiagramFragment {

    private static final int[] MY_COLORS = {Color.rgb(193, 37, 82), Color.rgb(255, 102, 0),
            Color.rgb(245, 199, 0), Color.rgb(106, 150, 31),
            Color.rgb(179, 100, 53), Color.rgb(20, 90, 150)};


    public DiagramBaton() {
    }
    public static DiagramBaton newInstance() {
        return (new DiagramBaton());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_diagram_baton, container, false);

        if (getyData() != null) {
            setUpDiagramBaton(v);
        }
        else {
            BarChart barChart = v.findViewById(R.id.barchart1);
            barChart.setNoDataText("Pas encore assez de données...");
            barChart.setNoDataTextColor(Color.BLACK);
        }

        return v;
    }

    private void setUpDiagramBaton(View v) {
        ArrayList barEntryList = new ArrayList();
        ArrayList type = new ArrayList();

        for (int i = 0; i < getyData().length; i++) {
            barEntryList.add(new BarEntry(i, getyData()[i]));
            type.add(getxData()[i]);
        }

        String legende="";
        for(int j=0; j<getxData().length; j++){
            if(getyData()[j]>0){
                legende+=getxData()[j]+ " ";

            }
        }

        BarDataSet dataSet = new BarDataSet(barEntryList, legende);

        BarData barData = new BarData(dataSet);
        dataSet.setColors(MY_COLORS);
        BarChart barChart = v.findViewById(R.id.barchart1);


        barChart.setNoDataText("Pas encore de statistiques, effectuez d'autres signalements pour en avoir !");
        barChart.setNoDataTextColor(Color.BLACK);
        barChart.setData(barData);
        barChart.getDescription().setEnabled(false);
        barChart.setHighlightPerTapEnabled(true);
        barChart.getData().setDrawValues(false);
        barChart.animateY(1000);
        barChart.invalidate();


    }

}

