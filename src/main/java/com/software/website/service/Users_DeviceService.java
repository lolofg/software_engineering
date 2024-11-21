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
        String sql = "INSERT INTO Users_Device(UsersID, ProductID, Name) VALUES (?, ?, ?)";
        jdbcTemplate.update((sql), device.getUsersID(), device.getProductID(), device.getName());
    }

    public void removeDevice(int id) {
        String sql = "DELETE FROM Users_device WHERE DeviceID = ?";
        jdbcTemplate.update(sql, id);
    }


    public List<Users_Device> getOneUsersDevices(int UserID) {
        String sql = "SELECT * FROM Users_device WHERE UsersID = ?";
        return jdbcTemplate.query(sql, new Users_DeviceRowMapper(), UserID);
    }
}