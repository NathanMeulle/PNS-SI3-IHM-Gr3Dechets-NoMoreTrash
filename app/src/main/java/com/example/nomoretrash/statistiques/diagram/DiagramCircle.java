package com.example.nomoretrash.statistiques.diagram;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nomoretrash.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class DiagramCircle extends DiagramFragment {


    private static final int[] MY_COLORS = {Color.rgb(193, 37, 82), Color.rgb(255, 102, 0),
            Color.rgb(245, 199, 0), Color.rgb(106, 150, 31),
            Color.rgb(179, 100, 53), Color.rgb(20, 90, 150)};

    PieChart pieChart;


    public DiagramCircle() {
    }

    public static DiagramCircle newInstance() {
        return (new DiagramCircle());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_diagram_circle, container, false);


        if (getyData() != null && getyData().length!=0) {
            setUpDiagramCircle(v);
        }
        else {
            PieChart pieChart = v.findViewById(R.id.pieChart1);
            pieChart.setNoDataText("Pas encore assez de données...");
            pieChart.setNoDataTextColor(Color.BLACK);
        }

        return v;
    }

    private void setUpDiagramCircle(View v) {
        //Mise en forme des données
        List<PieEntry> pieEntryList = new ArrayList<>();
        for (int i = 0; i < getyData().length; i++) {
            pieEntryList.add(new PieEntry(getyData()[i], getxData()[i]));
        }

        PieDataSet dataSet = new PieDataSet(pieEntryList, "");

        PieData pieData = new PieData(dataSet);
        dataSet.setColors(MY_COLORS);

        //Création du graph
        pieChart = v.findViewById(R.id.pieChart1);
        pieChart.setNoDataText("Pas encore de statistiques, effectuez d'autres signalements pour en avoir !");
        pieChart.setNoDataTextColor(Color.BLACK);
        pieChart.setData(pieData);
        pieChart.setUsePercentValues(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.setRotationEnabled(false);
        pieChart.setHighlightPerTapEnabled(true);
        pieChart.getData().setDrawValues(false);
        if(Configuration.ORIENTATION_LANDSCAPE==getActivity().getResources().getConfiguration().orientation) {
            pieChart.getLegend().setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
            pieChart.getLegend().setOrientation(Legend.LegendOrientation.VERTICAL);
            pieChart.setCenterTextSize(22);
        }
        else if(Configuration.ORIENTATION_PORTRAIT==getActivity().getResources().getConfiguration().orientation) {
            pieChart.getLegend().setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
            pieChart.getLegend().setOrientation(Legend.LegendOrientation.HORIZONTAL);
            pieChart.setCenterTextSize(26);
        }

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                if (e == null)
                    return;
                pieChart.setCenterText(pieEntryList.get((int)h.getX()).getLabel() + " : " + Math.round(100*pieEntryList.get((int)h.getX()).getValue()/pieData.getYValueSum()) + "%");
                pieChart.setCenterTextColor(Color.rgb(0,0,0));
            }

            @Override
            public void onNothingSelected() {
            }
        });
        pieChart.setEntryLabelColor(Color.rgb(0,0,0));
        pieChart.setEntryLabelTextSize(18);
        pieChart.getLegend().setTextSize(21-getyData().length);
        pieChart.animateY(1000);


        pieChart.invalidate();
    }

}
