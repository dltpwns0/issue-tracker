package com.example.BE.issue.dto;

import java.util.List;

public class IssueSearchCondition {

    private String author;
    private String assignee;
    private List<String> labelNames;
    private String milestoneName;
    private Boolean state;

    public IssueSearchCondition() {
    }

    public IssueSearchCondition(String author, String assignee, List<String> labelNames, String milestoneName, Boolean state) {
        this.author = author;
        this.assignee = assignee;
        this.labelNames = labelNames;
        this.milestoneName = milestoneName;
        this.state = state;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public List<String> getLabelNames() {
        return labelNames;
    }

    public void setLabelNames(List<String> labelNames) {
        this.labelNames = labelNames;
    }

    public Integer getLabelNamesSize() {
        return labelNames.size();
    }

    public String getMilestoneName() {
        return milestoneName;
    }

    public void setMilestoneName(String milestoneName) {
        this.milestoneName = milestoneName;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
