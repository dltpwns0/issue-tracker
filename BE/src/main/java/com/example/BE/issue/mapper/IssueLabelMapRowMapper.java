package com.example.BE.issue.mapper;

import com.example.BE.issue.dto.IssueNumberWithLabelDTO;
import com.example.BE.label.Label;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IssueLabelMapRowMapper implements RowMapper<IssueNumberWithLabelDTO> {

    @Override
    public IssueNumberWithLabelDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        Label label = new BeanPropertyRowMapper<>(Label.class).mapRow(rs, rowNum);
        IssueNumberWithLabelDTO issueNumberWithLabelDTO = new BeanPropertyRowMapper<>(IssueNumberWithLabelDTO.class).mapRow(rs, rowNum);
        issueNumberWithLabelDTO.setLabel(label);

        return issueNumberWithLabelDTO;
    }
}
