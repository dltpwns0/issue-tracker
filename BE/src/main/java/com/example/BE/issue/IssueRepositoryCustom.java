package com.example.BE.issue;

import com.example.BE.issue.dto.IssueSearchCondition;

import java.util.List;

public interface IssueRepositoryCustom {

    List<Issue> findIssuesWithoutLabelsBy(IssueSearchCondition issueSearchCondition);
}
