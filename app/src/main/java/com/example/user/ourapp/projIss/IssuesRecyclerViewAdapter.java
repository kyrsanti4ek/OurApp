package com.example.user.ourapp.projIss;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.ourapp.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.user.ourapp.R.drawable.oval_blocker;

public class IssuesRecyclerViewAdapter extends RecyclerView.Adapter<IssuesRecyclerViewAdapter.ViewHolder>{

    private Context mCtx;
    private List<Issues> issuesList;

    public IssuesRecyclerViewAdapter(Context mCtx, List<Issues> issuesList, String projectName) {
        this.mCtx = mCtx;
        List<Issues> iss1 = issuesList;
        List<Issues> iss2 = new ArrayList<>();
        Log.d("IssuesList", issuesList.toString());
        for(Issues s : iss1){
            Log.d("IssuesList", s.toString());
            if (s.getProject().equals(projectName)) {
                iss2.add(s);
            }
        }
        this.issuesList = iss2;
    }

    @NonNull
    @Override
    public IssuesRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.issues_fragment_item, null);

        IssuesRecyclerViewAdapter.ViewHolder vh = new IssuesRecyclerViewAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull IssuesRecyclerViewAdapter.ViewHolder holder, int position) {
        Issues issues = issuesList.get(position);
        holder.issuesSummary.setText(issues.getSummary());
        holder.issuesSeverity.setText(issues.getSeverity());
        holder.issuesStatus.setText(issues.getStatus());
        switch (issues.getSeverity()){
            case "Blocker":
                holder.shape.setBackgroundResource(R.drawable.oval_blocker);
                break;
            case "Critical":
                holder.shape.setBackgroundResource(R.drawable.oval_critical);
                break;
            case "Major":
                holder.shape.setBackgroundResource(R.drawable.oval_major);
                break;
            case "Minor":
                holder.shape.setBackgroundResource(R.drawable.oval_minor);
                break;
            case "Trivial":
                holder.shape.setBackgroundResource(R.drawable.oval_trivial);
                break;
        }
    }


    @Override
    public int getItemCount() {
        return issuesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView issuesSummary, issuesSeverity, issuesStatus, issues;
        View shape;

        public ViewHolder(View itemView) {
            super(itemView);

            issuesSummary = itemView.findViewById(R.id.issues_summary);
            issuesSeverity = itemView.findViewById(R.id.issues_severity);
            issuesStatus = itemView.findViewById(R.id.issues_status_value);
            shape = itemView.findViewById(R.id.shape);
        }
    }

}

