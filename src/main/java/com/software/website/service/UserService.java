package com.software.website.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.software.website.Entity.Users;
import com.software.website.RowMapper.UserRowMapper;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate; 

    public List<Users> getAllUsers() {
        return jdbcTemplate.query("select * from Users", new UserRowMapper());
    }
    
    public Users getOneUserByID(int UserID){
        return jdbcTemplate.queryForObject("select * from Users where UserID = ?", new UserRowMapper(), UserID);
        
    }
}
