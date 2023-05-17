package com.example.BE.issue;

import com.example.BE.issue.dto.IosCreateIssueRequest;
import com.example.BE.issue.dto.IosIssueResponse;
import com.example.BE.issue.dto.IssueSearchCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-ios")
public class IosIssueController {

    private final IosIssueService issueService;

    @Autowired
    public IosIssueController(IosIssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping(value = "/issues")
    public IosIssueResponse createIosResponse(@ModelAttribute IssueSearchCondition issueSearchCondition) {
        return issueService.findIssuesBy(issueSearchCondition);
    }

    @PostMapping(value = "/issues")
    public void createIssue(@ModelAttribute IosCreateIssueRequest createIssueRequest) {
        issueService.createIssue(createIssueRequest);
    }

}
