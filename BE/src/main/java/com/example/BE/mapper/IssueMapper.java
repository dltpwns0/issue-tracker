package com.example.BE.mapper;

import com.example.BE.issue.Issue;
import com.example.BE.issue.dto.Count;
import com.example.BE.issue.dto.IosCreateIssueRequest;
import com.example.BE.issue.dto.IssueLabelMap;
import com.example.BE.issue.dto.IssueSearchCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IssueMapper {
    List<Issue> findIssueWithoutLabelsBy(IssueSearchCondition issueSearchCondition);

    List<IssueLabelMap> findIssueLabelMapBy(List<Issue> issues);

    Count countEntities();

    public void insertIssue(IosCreateIssueRequest createIssueRequest);

    public void insertAssigns(IosCreateIssueRequest createIssueRequest);

    public void insertIssueLabelRelation(IosCreateIssueRequest createIssueRequest);

}
