package com.example.user.ourapp.projIss;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.ourapp.R;


import java.util.List;


public class ProjectRecyclerViewAdapter extends RecyclerView.Adapter<ProjectRecyclerViewAdapter.ViewHolder> {

    private Context mCtx;
    private List<Project> projectList;
    private Preferences data;
    FragmentManager f_manager;

    public ProjectRecyclerViewAdapter(Context mCtx, List<Project> projectList, FragmentManager f_manager) {
        data = new Preferences(mCtx);
        this.mCtx = mCtx;
        this.projectList = projectList;
        this.f_manager = f_manager;
    }

    public void dataSetChanged(List<Project> projectList){
        this.projectList = projectList;
        notifyDataSetChanged();
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
    public void onBindViewHolder(@NonNull ProjectRecyclerViewAdapter.ViewHolder holder,final int position) {
        final Project project = projectList.get(position);

        holder.projectTextViewTitle.setText(project.getTitle());
        holder.click_issues_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("click", "Вошел");
                Log.d("click", "getFragmentManager");
                f_manager.beginTransaction().replace(R.id.fragment_cont, new IssuesFragment(project.getTitle())).addToBackStack(null).commit();
            }
        });
        holder.click_issues_layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(mCtx, "OnLongClick Called at position " + position, Toast.LENGTH_SHORT).show();

                removeItem(project, position);
                return true;
            }
        });
    }

    private void removeItem(Project project, int position ) {
        Log.d("projectId", String.valueOf(project.getId()));

        data.deleteProject(project.getId());
        projectList.remove(project);
        notifyItemRemoved(position);

    }

//    private void fragmentJump(Project mItemSelected) {
//        mFragment = new IssuesFragment("BugFinders");
//        mBundle = new Bundle();
//        mBundle.putParcelable("item_selected_key", mItemSelected);
//        mFragment.setArguments(mBundle);
//        switchContent(R.id.frag1, mFragment);
//    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView projectTextViewTitle;
        LinearLayout click_issues_layout;

        public ViewHolder(View itemView) {
            super(itemView);

            projectTextViewTitle = itemView.findViewById(R.id.projectTextViewTitle);
            click_issues_layout = itemView.findViewById(R.id.click_project_layout);
        }
    }

}
