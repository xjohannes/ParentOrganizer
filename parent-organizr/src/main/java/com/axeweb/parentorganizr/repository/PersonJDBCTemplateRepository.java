package com.axeweb.parentorganizr.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PersonJDBCTemplateRepository {

private final JdbcTemplate jdbcTemplate;

    public PersonJDBCTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
