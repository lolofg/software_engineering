package com.software.website.RowMapper;

import org.springframework.jdbc.core.RowMapper;

import com.software.website.Entity.Device;

import java.sql.ResultSet;
import java.sql.SQLException;


public class DeviceRowMapper implements RowMapper<Device> {

    @Override
    public Device mapRow(ResultSet rs, int rowNum) throws SQLException {

        Device obj = new Device();
        
        obj.setDeviceID(rs.getString("Device_ID"));
        obj.setUsersID(rs.getInt("Users_ID"));
        obj.setProductID(rs.getInt("Product_ID"));
        obj.setName(rs.getString("Name"));
        return obj;

    }
    
}
