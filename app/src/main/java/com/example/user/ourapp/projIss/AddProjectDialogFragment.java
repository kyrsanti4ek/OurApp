package com.example.user.ourapp.projIss;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.ourapp.R;

@SuppressLint("ValidFragment")
public class AddProjectDialogFragment extends DialogFragment {

EditText projectName;
Button cancel, add_new;
private String sProject;
ProjectRecyclerViewAdapter adapter;

    @SuppressLint("ValidFragment")
    public AddProjectDialogFragment(ProjectRecyclerViewAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanseState) {
        View view = inflater.inflate(R.layout.add_project_dialog_fragment, null);

        projectName = (EditText) view.findViewById(R.id.add_project_name);
        cancel = (Button) view.findViewById(R.id.add_project_cancel);
        add_new = (Button) view.findViewById(R.id.add_project_add_new);

        add_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sProject = projectName.getText().toString();
                Preferences data = new Preferences(getContext());
                data.addProject(new Project(sProject));
                adapter.dataSetChanged(data.getProjectData());
                dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }

}
