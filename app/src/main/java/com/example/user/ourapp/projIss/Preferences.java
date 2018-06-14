package com.example.user.ourapp.projIss;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Preferences {
    private static final String ISSUES_DATA = "issues_data";
    private static final String PROJECT_DATA = "project_data";
    private SharedPreferences mSettings;

    public Preferences(Context context) {
        mSettings = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setIssuesData(List<Issues> i) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(i);
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(ISSUES_DATA, jsonStr);
        editor.apply();
    }

    public List<Issues> getIssuesData() {
        Gson gson = new Gson();
        String jsonStr = mSettings.getString(ISSUES_DATA, null);
        Type listType = new TypeToken<List<Issues>>(){}.getType();
        if (gson.fromJson(jsonStr, listType) == null)
            return new ArrayList<>();
        else
            return (List<Issues>) gson.fromJson(jsonStr, listType);
    }

    public void addIssues(Issues issues) {
        int maxId = 0;
        List<Issues> issueses = getIssuesData();
        if (issueses == null) {
            issueses = new ArrayList<>();
        }
        for (Issues i : issueses) {
            if (i.getId() > maxId) {
                maxId = i.getId();
            }
        }
        issues.setId(maxId + 1);
        issueses.add(issues);
        setIssuesData(issueses);
    }

    public void changeIssues(int id, String project,  String summary, String priority, String severity, String description, String status) {
        List<Issues> issueses = getIssuesData();
        issueses.remove(id - 1);
        issueses.add(new Issues(id, project, summary, priority, severity, description, status));
        setIssuesData(issueses);
    }

    public void deleteIssues(int position) {
        List<Issues> issueses = getIssuesData();
        issueses.remove(position);
        setIssuesData(issueses);
    }

    public void setProjectData(List<Project> p) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(p);
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(PROJECT_DATA, jsonStr);
        editor.apply();
    }

    public List<Project> getProjectData() {
        Gson gson = new Gson();
        String jsonStr = mSettings.getString(PROJECT_DATA, null);
        Type listType = new TypeToken<List<Project>>(){}.getType();
        if (gson.fromJson(jsonStr, listType) == null)
            return new ArrayList<>();
        else
            return (List<Project>) gson.fromJson(jsonStr, listType);
    }

    public void addProject(Project issues) {
        int maxId = 0;
        List<Project> projects = getProjectData();
        if (projects == null) {
            projects = new ArrayList<>();
        }
        for (Project p : projects) {
            if (p.getId() > maxId) {
                maxId = p.getId();
            }
        }
        issues.setId(maxId + 1);
        projects.add(issues);
        setProjectData(projects);
    }

    public void deleteProject(int position) {

        List<Project> projects = getProjectData();

        Project project = null;
        for (Project p : projects){
            if (p.getId() == (position)){
                project = p;
                deleteProjectAndIssues(project);
                Log.d("ProjectProject", String.valueOf(project));
            }
        }
        projects.remove(project);
        setProjectData(projects);
    }

    public void deleteProjectAndIssues(Project project) {
        List<Issues> issueses = getIssuesData();

        for (Iterator<Issues> it = issueses.iterator(); it.hasNext(); ) {
            Issues issues = it.next();
            if (issues.getProject().equals(project.getTitle())){
                it.remove();
            }
        }
        setIssuesData(issueses);
    }

    public float[] getCountIssues(String projectName) {
        float iBlocker = 0,
            iCritical = 0,
            iMajor = 0,
            iMinor = 0,
            iTrivial = 0;
        List<Issues> issueses = getIssuesData();

        for (Issues i : issueses){
            if (i.getProject().equals(projectName)){
                switch (i.getSeverity()){
                    case "Blocker":
                        iBlocker+=1;
                        break;
                    case "Critical":
                        iCritical+=1;
                        break;
                    case "Major":
                        iMajor+=1;
                        break;
                    case "Minor":
                        iMinor+=1;
                        break;
                    case "Trivial":
                        iTrivial+=1;
                        break;
                }
            }
        }
        float[] severity = {iBlocker, iCritical, iMajor, iMinor, iTrivial};
        return severity;
    }

}
