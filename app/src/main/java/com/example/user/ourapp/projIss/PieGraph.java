package com.example.user.ourapp.projIss;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.ourapp.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class PieGraph extends android.app.Fragment{

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    Preferences data;
    float[] iSeverity;
    String[] Severity = {"Blocker", "Critical", "Major", "Minor", "Trivial"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getActivity().getActionBar().setTitle("ISSUES");

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pie_graph, container, false);

        data = new Preferences(getContext());
        iSeverity = data.getCountIssues("BugFinders");
        Log.d("iSeverity", String.valueOf(iSeverity[0] + " " + iSeverity[4]));


        List<PieEntry> pieEntries = new ArrayList<>();
        for (int i = 0; i < Severity.length; i++) {
            pieEntries.add(new PieEntry(iSeverity[i], Severity[i]));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries, "blablablbalbalablbalSeverity");
        PieData pieData = new PieData(dataSet);

        PieChart chart = (PieChart) view.findViewById(R.id.chart);
        chart.setData(pieData);
        chart.invalidate();

        return view;
    }


}
