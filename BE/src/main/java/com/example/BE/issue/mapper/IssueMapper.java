package com.example.BE.issue.mapper;

import com.example.BE.issue.Issue;
import com.example.BE.issue.dto.IssueSearchCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IssueMapper {
    List<Issue> findIssuesWithoutLabelsBy(IssueSearchCondition issueSearchCondition);
}
