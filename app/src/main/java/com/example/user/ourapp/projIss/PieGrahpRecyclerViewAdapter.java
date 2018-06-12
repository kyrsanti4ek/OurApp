package com.example.user.ourapp.projIss;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.ourapp.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class PieGrahpRecyclerViewAdapter extends RecyclerView.Adapter<PieGrahpRecyclerViewAdapter.ViewHolder> {


    Context mCtn;
    List<Project> projectsList;
    private Preferences data;
    public static final int[] MY_COLORS = {
            Color.rgb(91, 19, 23), Color.rgb(255, 0, 0), Color.rgb(214, 79, 68),
            Color.rgb(0, 68, 255), Color.rgb(0, 157, 255)};

    public PieGrahpRecyclerViewAdapter(Context mCtn, List<Project> projectList) {
        data = new Preferences(mCtn);
        this.mCtn = mCtn;
        this.projectsList = projectList;
    }

    public void dataSetChanged(List<Project> projectList) {
        this.projectsList = projectList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtn);
        View view = inflater.inflate(R.layout.pie_graph_item, null);

        PieGrahpRecyclerViewAdapter.ViewHolder vh = new PieGrahpRecyclerViewAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Project project = projectsList.get(position);
        holder.projectName.setText(project.getTitle());
        float[] countSeverity = data.getCountIssues(project.getTitle());
        List<PieEntry> pieEntries = new ArrayList<>();
        ArrayList<String> sSeverity = new ArrayList<>();
        ArrayList<Float> iSeverity = new ArrayList<>();
        ArrayList<Integer> MY_COLORS = new ArrayList<>();

//                Color.rgb(91, 19, 23), Color.rgb(255, 0, 0), Color.rgb(214, 79, 68),
//                Color.rgb(0, 68, 255), Color.rgb(0, 157, 255)};

        if (countSeverity[0] != 0.0) {
            sSeverity.add("Blocker");
            iSeverity.add(countSeverity[0]);
            MY_COLORS.add(Color.rgb(91, 19, 23));
        }
        if (countSeverity[1] != 0.0) {
            sSeverity.add("Critical");
            iSeverity.add(countSeverity[1]);
            MY_COLORS.add(Color.rgb(255, 0, 0));
        }
        if (countSeverity[2] != 0.0) {
            sSeverity.add("Major");
            iSeverity.add(countSeverity[2]);
            MY_COLORS.add(Color.rgb(214, 79, 68));
        }
        if (countSeverity[3] != 0.0) {
            sSeverity.add("Minor");
            iSeverity.add(countSeverity[3]);
            MY_COLORS.add(Color.rgb(0, 68, 255));
        }
        if (countSeverity[4] != 0.0) {
            sSeverity.add("Trivial");
            iSeverity.add(countSeverity[4]);
            MY_COLORS.add(Color.rgb(0, 157, 255));
        }



        for (int i = 0; i < sSeverity.size(); i++) {
            pieEntries.add(new PieEntry(iSeverity.get(i), sSeverity.get(i)));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries, "Severity");
        dataSet.setColors(MY_COLORS);
        PieData pieData = new PieData(dataSet);

        holder.pieChart.setData(pieData);
        holder.pieChart.animateY(1000);
        holder.pieChart.invalidate();
    }

    @Override
    public int getItemCount() {
        return projectsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView projectName;
        PieChart pieChart;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.click_pie_chart_layout);
            pieChart = itemView.findViewById(R.id.chart);
            projectName = itemView.findViewById(R.id.pie_chart_project_name);

        }
    }
}
