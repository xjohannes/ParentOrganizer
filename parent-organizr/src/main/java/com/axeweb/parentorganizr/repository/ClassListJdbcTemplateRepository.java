package com.axeweb.parentorganizr.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.axeweb.parentorganizr.model.ClassList;

@Repository
public class ClassListJdbcTemplateRepository {
    private final JdbcTemplate jdbcTemplate;

    public ClassListJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }  

    private static Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ClassList(rs.getString("classNumber"), rs.getString("classLetter"), rs.getString("className"), rs.getString("startYear"), rs.getString("filename"));
    }

    
}
