package com.example.BE.issue.dto;

import com.example.BE.issue.Issue;

import java.util.Collection;
import java.util.List;

public class IosIssueResponseDTO {

    private Collection<Issue> issues;

    public IosIssueResponseDTO(Collection<Issue> issues) {
        this.issues = issues;
    }

    public Collection<Issue> getIssues() {
        return issues;
    }

}
