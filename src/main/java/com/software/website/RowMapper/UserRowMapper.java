package com.software.website.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import com.software.website.Entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException{
        User obj = new User();

        obj.setUserID(rs.getInt("touristID"));
        obj.setFirstName(rs.getString("FirstName"));
        obj.setLastName(rs.getString("lastName"));
        obj.setEmail(rs.getString("email"));

        return obj;


    }
    
}
