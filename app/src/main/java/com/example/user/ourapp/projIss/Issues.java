package com.example.user.ourapp.projIss;

public class Issues {

    private String project;
    private int id;
    private String summary;
    private String priority;
    private String severity;
    private String description;
    private String status;

    public Issues(String project, int id, String summary, String priority, String severity, String description, String status) {
        this.project = project;
        this.id = id;
        this.summary = summary;
        this.priority = priority;
        this.severity = severity;
        this.description = description;
        this.status = status;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
