package com.example.BE.issue.mapper;

import com.example.BE.assignee.Assignee;
import com.example.BE.user.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AssigneeRowMapper implements RowMapper<Assignee> {

    @Override
    public Assignee mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new BeanPropertyRowMapper<>(User.class).mapRow(rs, rowNum);
        Assignee assignee = new BeanPropertyRowMapper<>(Assignee.class).mapRow(rs, rowNum);
        assignee.setUser(user);

        return assignee;
    }
}
