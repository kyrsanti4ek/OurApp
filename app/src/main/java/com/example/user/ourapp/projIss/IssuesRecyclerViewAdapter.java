package com.example.user.ourapp.projIss;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.Preference;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.ourapp.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.user.ourapp.R.drawable.oval_blocker;

public class IssuesRecyclerViewAdapter extends RecyclerView.Adapter<IssuesRecyclerViewAdapter.ViewHolder>{

    private Context mCtx;
    private List<Issues> issuesList;
    private String projectName;
    private Preferences data;
    FragmentManager f_manager;
    IssuesRecyclerViewAdapter adapter;

    public IssuesRecyclerViewAdapter(Context mCtx, List<Issues> issuesList, String projectName, FragmentManager f_manager) {
        data = new Preferences(mCtx);
        this.projectName = projectName;
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
        this.f_manager = f_manager;
    }

    public void dataSetChanged(List<Issues> issuesList) {
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
        notifyDataSetChanged();
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
    public void onBindViewHolder(@NonNull IssuesRecyclerViewAdapter.ViewHolder holder, final int position) {
        final Issues issues = issuesList.get(position);
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
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mCtx, "OnClick Called at position " + position, Toast.LENGTH_SHORT).show();
                DialogFragment newFragment = new ChangeIssuesDialogFragment(adapter, mCtx);
                Bundle bundle = new Bundle();
                bundle.putString("projectName", projectName);
                bundle.putString("Status", issues.getStatus());
                bundle.putString("Severity", issues.getSeverity());
                bundle.putString("Priority", issues.getPriority());
                bundle.putString("Summary", issues.getSummary());
                bundle.putString("Description", issues.getDescription());
                bundle.putInt("Id", issues.getId());
                Log.d("projectName", projectName);
                newFragment.setArguments(bundle);
                newFragment.show(f_manager, "changeIssues");
                dataSetChanged(data.getIssuesData());


            }
        });
        holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(mCtx, "OnLongClick Called at position " + position, Toast.LENGTH_SHORT).show();

                removeItem(issues, position);

                return true;
            }
        });

        Log.d("IssuesId", String.valueOf(issues.getId()));
    }

    private void removeItem(Issues issues, int position ) {

        data.deleteIssues(issues.getId()-1);
        issuesList.remove(issues);
        notifyItemRemoved(position);

    }


    @Override
    public int getItemCount() {
        return issuesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView issuesSummary, issuesSeverity, issuesStatus;
        View shape;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.click_issues_layout);
            issuesSummary = itemView.findViewById(R.id.issues_summary);
            issuesSeverity = itemView.findViewById(R.id.issues_severity);
            issuesStatus = itemView.findViewById(R.id.issues_status_value);
            shape = itemView.findViewById(R.id.shape);
        }
    }


}

