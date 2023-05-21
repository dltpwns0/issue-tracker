package com.example.BE.issue;

import com.example.BE.issue.dto.IssueSearchCondition;
import com.example.BE.issue.mapper.IssueMapper;

import java.util.List;

public class IssueRepositoryImpl implements IssueRepositoryCustom{

    private final IssueMapper issueMapper;

    public IssueRepositoryImpl(IssueMapper issueMapper) {
        this.issueMapper = issueMapper;
    }

    @Override
    public List<Issue> findIssuesWithoutLabelsBy(IssueSearchCondition issueSearchCondition) {
        return issueMapper.findIssuesWithoutLabelsBy(issueSearchCondition);
    }
}
