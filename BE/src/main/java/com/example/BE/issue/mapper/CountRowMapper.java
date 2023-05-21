package com.example.BE.issue.mapper;

import com.example.BE.issue.dto.CountDTO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountRowMapper implements RowMapper<CountDTO> {

    @Override
    public CountDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new BeanPropertyRowMapper<>(CountDTO.class).mapRow(rs, rowNum);
    }
}
