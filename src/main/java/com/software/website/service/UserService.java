package com.software.website.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.software.website.Entity.Users;
import com.software.website.RowMapper.UserRowMapper;

@Service
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate; 

    public List<Users> getAllUsers() {
        return jdbcTemplate.query("select * from user", new UserRowMapper()); 
    }
    
    public Users getOneUserByID(int User_ID){
        return jdbcTemplate.queryForObject("select * from user where userID = ?", new UserRowMapper(), User_ID);
        
    }
}
