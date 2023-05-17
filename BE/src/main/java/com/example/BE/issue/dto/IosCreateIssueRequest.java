package com.example.BE.issue.dto;

import java.util.List;

public class IosCreateIssueRequest {
    private int issueNumber;
    private int userId;
    private String title;
    private String contents;
    private List<String> assignees;
    private String milestoneName;
    private List<String> labelNames;

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    public void setAssignees(List<String> assignees) {
        this.assignees = assignees;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public List<String> getAssignees() {
        return assignees;
    }

    public void setAssignee(List<String> assignees) {
        this.assignees = assignees;
    }

    public String getMilestoneName() {
        return milestoneName;
    }

    public void setMilestoneName(String milestoneName) {
        this.milestoneName = milestoneName;
    }

    public List<String> getLabelNames() {
        return labelNames;
    }

    public void setLabelNames(List<String> labelNames) {
        this.labelNames = labelNames;
    }
}
