package com.example.BE.issue.dto;

import com.example.BE.label.Label;

public class IssueLabelMap {

    private Integer issueNumber;

    private Label label;

    public IssueLabelMap() {
    }

    public Integer getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(Integer issueNumber) {
        this.issueNumber = issueNumber;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }
}
