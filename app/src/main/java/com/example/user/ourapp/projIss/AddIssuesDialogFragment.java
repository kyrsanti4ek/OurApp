package com.example.user.ourapp.projIss;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.Preference;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.user.ourapp.R;

@SuppressLint("ValidFragment")
public class AddIssuesDialogFragment extends DialogFragment {
    TextView projectName;
    Spinner spinner1, spinner2;
    EditText summary, description;
    Button cancel, add_new;
    private String sProject, sSummary, sSeverity, sPriority, sDescription, sStatus;
    IssuesRecyclerViewAdapter adapter;

    @SuppressLint("ValidFragment")
    public AddIssuesDialogFragment(IssuesRecyclerViewAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanseState) {

        View view = inflater.inflate(R.layout.add_issues_dialog_fragment, null);

        projectName = (TextView) view.findViewById(R.id.add_issues_project_name);
        projectName.setText(getArguments().get("projectName").toString());

        spinner1 = view.findViewById(R.id.add_issues_spinner1);
        spinner2 = view.findViewById(R.id.add_issues_spinner2);
        summary = view.findViewById(R.id.add_issues_summary);
        description = view.findViewById(R.id.add_issues_description);
        cancel = view.findViewById(R.id.add_issues_cancel);
        add_new = view.findViewById(R.id.add_issues_add_new);

        String[] data_severity = {"Blocker", "Critical", "Major", "Minor", "Trivial"};
        String[] data_priority = {"High", "Middle", "Low"};
        ArrayAdapter<String> adapter_severity = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, data_severity);
        ArrayAdapter<String> adapter_priority = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, data_priority);

        spinner1.setAdapter(adapter_severity);
        spinner2.setAdapter(adapter_priority);

        spinner1.setPrompt("Severity");
        spinner2.setPrompt("Priority");

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sSeverity = spinner1.getSelectedItem().toString();
                Log.d("blablabla", sSeverity);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sPriority = spinner2.getSelectedItem().toString();
                Log.d("blablabla", sPriority);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        add_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sProject = projectName.getText().toString();
                Log.d("sProject", sProject);

                sSummary = summary.getText().toString();

                sDescription = description.getText().toString();

                sStatus = "Open";
                Preferences data = new Preferences(getContext());
                data.addIssues(new Issues(sProject, sSummary, sPriority, sSeverity, sDescription, sStatus));
                adapter.dataSetChanged(data.getIssuesData());
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


    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }

}
