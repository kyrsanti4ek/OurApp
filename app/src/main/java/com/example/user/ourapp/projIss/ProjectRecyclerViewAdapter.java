package com.example.user.ourapp.projIss;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.ourapp.R;


import java.util.List;


public class ProjectRecyclerViewAdapter extends RecyclerView.Adapter<ProjectRecyclerViewAdapter.ViewHolder> {

    private Context mCtx;
    private List<Project> projectList;

    public ProjectRecyclerViewAdapter(Context mCtx, List<Project> projectList) {
        this.mCtx = mCtx;
        this.projectList = projectList;
    }

    @NonNull
    @Override
    public ProjectRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.fragment_item, null);

        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectRecyclerViewAdapter.ViewHolder holder, int position) {
        Project project = projectList.get(position);
        holder.projectTextViewTitle.setText(project.getTitle());
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView projectTextViewTitle;

        public ViewHolder(View itemView) {
            super(itemView);

            projectTextViewTitle = itemView.findViewById(R.id.projectTextViewTitle);
        }
    }

}
