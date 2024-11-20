package com.software.website.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.software.website.Entity.Users_Device;
import com.software.website.RowMapper.Users_DeviceRowMapper;

import java.util.List; 


@Service
public class Users_DeviceService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Users_Device> getAllDevices() {
        return jdbcTemplate.query("select * from Users_device", new Users_DeviceRowMapper());
    
    }

    public Users_Device getOneDeviceByID(int DeviceID) {
        return jdbcTemplate.queryForObject("select * from Users_device WHERE DeviceID = ?", new Users_DeviceRowMapper(), DeviceID);

    }
    
}
