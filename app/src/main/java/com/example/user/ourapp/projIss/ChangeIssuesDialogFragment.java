package com.example.user.ourapp.projIss;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.user.ourapp.R;

@SuppressLint("ValidFragment")
public class ChangeIssuesDialogFragment extends DialogFragment {

    TextView projectName;
    Spinner spinner1, spinner2, spinner3;
    EditText summary, description;
    Button cancel, change;
    private String sProject, sSummary, sSeverity, sPriority, sDescription, sStatus;
    private int iStatus, iSeverity, iPriority, iId;
    IssuesRecyclerViewAdapter adapter;
    Context mCnt;

    @SuppressLint("ValidFragment")
    ChangeIssuesDialogFragment(IssuesRecyclerViewAdapter adapter, Context mCnt) {
        this.adapter = adapter;
        this.mCnt = mCnt;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanseState) {
        View view = inflater.inflate(R.layout.change_issues_dialog_fragment, null);

        projectName = (TextView) view.findViewById(R.id.change_issues_project_name);
        projectName.setText(getArguments().get("projectName").toString());

        spinner1 = view.findViewById(R.id.change_issues_spinner1);
        spinner2 = view.findViewById(R.id.change_issues_spinner2);
        spinner3 = view.findViewById(R.id.change_issues_spinner3);
        summary = view.findViewById(R.id.change_issues_summary);
        description = view.findViewById(R.id.change_issues_description);
        cancel = view.findViewById(R.id.change_issues_cancel);
        change = view.findViewById(R.id.change_issues_add_new);

        String[] data_status = {"Open", "InProgress", "Close"};
        String[] data_severity = {"Blocker", "Critical", "Major", "Minor", "Trivial"};
        String[] data_priority = {"High", "Middle", "Low"};
        ArrayAdapter<String> adapter_status = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, data_status);
        ArrayAdapter<String> adapter_severity = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, data_severity);
        ArrayAdapter<String> adapter_priority = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, data_priority);

        spinner1.setAdapter(adapter_status);
        spinner2.setAdapter(adapter_severity);
        spinner3.setAdapter(adapter_priority);

        spinner1.setPrompt("Status");
        spinner2.setPrompt("Severity");
        spinner3.setPrompt("Priority");

        switch (getArguments().get("Status").toString()) {
            case "Open":
                iStatus = 0;
                break;
            case "InProgress":
                iStatus = 1;
                break;
            case "Close":
                iStatus = 2;
                break;
        }

        switch (getArguments().get("Severity").toString()) {
            case "Blocker":
                iSeverity = 0;
                break;
            case "Critical":
                iSeverity = 1;
                break;
            case "Major":
                iSeverity = 2;
                break;
            case "Minor":
                iSeverity = 3;
                break;
            case "Trivial":
                iSeverity = 4;
                break;
        }

        switch (getArguments().get("Priority").toString()) {
            case "High":
                iPriority = 0;
                break;
            case "Middle":
                iPriority = 1;
                break;
            case "Low":
                iPriority = 2;
                break;
        }

        spinner1.setSelection(iStatus);
        spinner2.setSelection(iSeverity);
        spinner3.setSelection(iPriority);

        summary.setText(getArguments().get("Summary").toString());
        description.setText(getArguments().get("Description").toString());

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sStatus = spinner1.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sSeverity = spinner2.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sPriority = spinner3.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iId = (int) getArguments().get("Id");
                sProject = projectName.getText().toString();
                sSummary = summary.getText().toString();
                sDescription = description.getText().toString();

                Preferences data = new Preferences(getContext());
                data.changeIssues(iId, sProject, sSummary, sPriority, sSeverity, sDescription, sStatus);
                Log.d("issuesData", data.getIssuesData().toString());
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
