package com.example.BE.issue;

import com.example.BE.issue.dto.Count;
import com.example.BE.issue.dto.IosCreateIssueRequest;
import com.example.BE.issue.dto.IssueLabelMap;
import com.example.BE.issue.dto.IssueSearchCondition;
import com.example.BE.mapper.IssueMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class IssueRepository {

    private final IssueMapper issueMapper;

    public IssueRepository(IssueMapper issueMapper) {
        this.issueMapper = issueMapper;
    }

    public List<Issue> findIssueWithoutLabelsBy(IssueSearchCondition issueSearchCondition) {
        return issueMapper.findIssueWithoutLabelsBy(issueSearchCondition);
    }

    public List<IssueLabelMap> findIssueLabelMapBy(List<Issue> issues) {
        return issueMapper.findIssueLabelMapBy(issues);
    }

    public Count countEntities() {
        return issueMapper.countEntities();
    }

    @Transactional
    public void createIssue(IosCreateIssueRequest createIssueRequest) {
        issueMapper.insertIssue(createIssueRequest);
        if (createIssueRequest.getLabelNames() != null) {
            issueMapper.insertIssueLabelRelation(createIssueRequest);
        }
        if (createIssueRequest.getAssignees() != null) {
            issueMapper.insertAssigns(createIssueRequest);
        }
    }

}
