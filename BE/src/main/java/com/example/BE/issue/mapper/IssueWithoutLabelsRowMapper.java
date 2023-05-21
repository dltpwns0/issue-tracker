package com.example.BE.issue.mapper;

import com.example.BE.issue.Issue;
import com.example.BE.milestone.Milestone;
import com.example.BE.user.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IssueWithoutLabelsRowMapper implements RowMapper<Issue> {

    @Override
    public Issue mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new BeanPropertyRowMapper<>(User.class).mapRow(rs, rowNum);
        Milestone milestone = new BeanPropertyRowMapper<>(Milestone.class).mapRow(rs, rowNum);
        Issue issue = new BeanPropertyRowMapper<>(Issue.class).mapRow(rs, rowNum);

        issue.setUser(user);
        if (!milestone.isEmpty()) {
            issue.setMilestone(milestone);
        }
        return issue;
    }
}
