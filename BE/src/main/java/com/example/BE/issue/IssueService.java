package com.example.BE.issue;

import com.example.BE.assignee.Assignee;
import com.example.BE.issue.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class IssueService {

    private final IssueRepository issueRepository;

    @Autowired
    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public FeIssueResponseDTO makeFeIssueResponse(IssueSearchCondition issueSearchCondition) {
        Collection<Issue> issues = findIssues(issueSearchCondition);
        CountDTO countDTO = calculateCount();
        return new FeIssueResponseDTO(issues, countDTO);
    }

    public IosIssueResponseDTO makeIosIssueResponse(IssueSearchCondition issueSearchCondition) {
        Collection<Issue> issues = findIssues(issueSearchCondition);
        return new IosIssueResponseDTO(issues);
    }

    private Collection<Issue> findIssues(IssueSearchCondition issueSearchCondition) {
        List<Issue> issues = issueRepository.findIssuesWithoutLabelsBy(issueSearchCondition);

        if (issues.isEmpty()) {
            return Collections.emptyList();
        }

        Map<Integer, Issue> issueMap = issues.stream()
                .collect(Collectors.toMap(
                        Issue::getNumber,
                        Function.identity()
                ));

        List<IssueNumberWithLabelDTO> issueNumberWithLabelDTOs = issueRepository.findIssueLabelMapsBy(issueMap.keySet());
        issueNumberWithLabelDTOs.forEach(issueLabel -> issueMap.get(issueLabel.getIssueNumber()).add(issueLabel));

        List<Assignee> assignees = issueRepository.findAssigneesBy(issueMap.keySet());
        assignees.forEach(assignee -> issueMap.get(assignee.getIssueNumber()).add(assignee));

        return issueMap.values();
    }

    private CountDTO calculateCount() {
        return issueRepository.countEntities();
    }
}
