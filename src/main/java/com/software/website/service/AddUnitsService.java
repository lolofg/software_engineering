package com.software.website.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List; 


@Service
public class AddUnitsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
}
