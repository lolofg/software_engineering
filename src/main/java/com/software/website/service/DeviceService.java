package com.software.website.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.software.website.Entity.Device;
import com.software.website.RowMapper.DeviceRowMapper;

import java.util.List; 


@Service
public class DeviceService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Device> getAllDevices() {
        return jdbcTemplate.query("select * from Users_devices", new DeviceRowMapper());
    
    }

    public Device getOneDeviceByID(String Device_ID) {
        return jdbcTemplate.queryForObject("select * from Users_devices WHERE Device_ID = ?", new DeviceRowMapper(), Device_ID); 

    }
    
}
