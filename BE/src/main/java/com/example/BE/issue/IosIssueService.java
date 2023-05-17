package com.example.BE.issue;

import com.example.BE.issue.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class IosIssueService {
    private final IssueRepository issueRepository;

    @Autowired
    public IosIssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public IosIssueResponse findIssuesBy(IssueSearchCondition issueSearchCondition) {
        List<Issue> issues = issueRepository.findIssueWithoutLabelsBy(issueSearchCondition);

        if (issues.isEmpty()) {
            return new IosIssueResponse(issues);
        }
        
        List<IssueLabelMap> issueLabelMaps = issueRepository.findIssueLabelMapBy(issues);

        HashMap<Integer, Issue> issueHashMap = new HashMap<>();
        for (Issue issue : issues) {
            issueHashMap.put(issue.getNumber(), issue);
        }
        for (IssueLabelMap issueLabelMap : issueLabelMaps) {
            Issue issue = issueHashMap.get(issueLabelMap.getIssueNumber());
            if (issue != null) {
                issue.addLabel(issueLabelMap.getLabel());
            }
        }

        return new IosIssueResponse(new ArrayList<>(issueHashMap.values()));
    }
    public void createIssue(IosCreateIssueRequest createIssueRequest) {
        Issue issue = new Issue();
        issue.setTitle(createIssueRequest.getTitle());
        issue.setContents(createIssueRequest.getContents());

        issueRepository.createIssue(createIssueRequest);
    }

}
