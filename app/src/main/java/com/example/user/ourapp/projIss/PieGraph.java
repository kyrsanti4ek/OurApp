package com.example.user.ourapp.projIss;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.ourapp.R;

import java.util.List;

public class PieGraph extends android.app.Fragment{

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;


    Preferences data;
    List<Project> projectList;
    RecyclerView recyclerView;
    PieGrahpRecyclerViewAdapter adapter;

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

        data = new Preferences(getContext());

        projectList = data.getProjectData();
        View view = inflater.inflate(R.layout.pie_graph_list, container, false);


        recyclerView = (RecyclerView) view.findViewById(R.id.pie_graph_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new PieGrahpRecyclerViewAdapter(getActivity().getApplicationContext(), projectList);
        recyclerView.setAdapter(adapter);

//        iSeverity = data.getCountIssues("BugFinders");
//        Log.d("iSeverity", String.valueOf(iSeverity[0] + " " + iSeverity[4]));
//
//
//        List<PieEntry> pieEntries = new ArrayList<>();
//        for (int i = 0; i < Severity.length; i++) {
//            pieEntries.add(new PieEntry(iSeverity[i], Severity[i]));
//        }
//
//        PieDataSet dataSet = new PieDataSet(pieEntries, "blablablbalbalablbalSeverity");
//        PieData pieData = new PieData(dataSet);
//
//        PieChart chart = (PieChart) view.findViewById(R.id.chart);
//        chart.setData(pieData);
//        chart.invalidate();

        return view;
    }


}
