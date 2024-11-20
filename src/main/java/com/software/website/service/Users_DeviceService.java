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
    
    public void addDevice(Users_Device device){
        String sql = "INSERT INTO Device (Device_ID, Product_ID, Name) VALUES (?, ?, ?)"; 
        jdbcTemplate.update((sql), device.getDeviceID(), device.getProductID(), device.getName()); 
    }

    public void removeDevice(int id) {
        String sql = "DELETE FROM Device WHERE Device_ID = ?";
        jdbcTemplate.update(sql, id);
    }


}