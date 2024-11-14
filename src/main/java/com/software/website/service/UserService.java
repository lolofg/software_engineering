package com.software.website.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.software.website.Entity.User;
import com.software.website.RowMapper.UserRowMapper;

@Service
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate; 

    public List<User> getAllUsers() {
        return jdbcTemplate.query("select * from user", new UserRowMapper()); 
    }
    
    public User getOneUserByID(int userID){
        return jdbcTemplate.queryForObject("select * from user where userID = ?", new UserRowMapper(), userID);
        
    }
}
