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

    public void addDevice(Device device){
        String sql = "INSERT INTO Device (Device_ID, Product_ID, Name) VALUES (?, ?, ?)"; 
        jdbcTemplate.update((sql), device.getDeviceID(), device.getProductID(), device.getName()); 

    }

    public void removeDevice(int id){
    String sql = "DELETE FROM Device WHERE Device_ID = ?";
    jdbcTemplate.update(sql, id); 
    }
    
}
