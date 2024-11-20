package com.software.website.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import com.software.website.Entity.Users;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<Users> {

    @Override
    public Users mapRow(ResultSet rs, int rowNum) throws SQLException{
        Users obj = new Users();

        obj.setUserID(rs.getInt("UserID"));
        obj.setFirstName(rs.getString("FirstName"));
        obj.setLastName(rs.getString("lastName"));
        obj.setEmail(rs.getString("Email"));

        return obj;


    }
    
}
