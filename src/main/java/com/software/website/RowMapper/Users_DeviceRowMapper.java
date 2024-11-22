package com.software.website.RowMapper;

import org.springframework.jdbc.core.RowMapper;

import com.software.website.Entity.Users_Device;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Users_DeviceRowMapper implements RowMapper<Users_Device> {

    @Override
    public Users_Device mapRow(ResultSet rs, int rowNum) throws SQLException {

        Users_Device obj = new Users_Device();
        
        obj.setDeviceID(rs.getInt("DeviceID"));
        obj.setUsersID(rs.getInt("UsersID"));
        obj.setProductID(rs.getInt("ProductID"));
        obj.setName(rs.getString("Name"));
        return obj;

    }
    
}
