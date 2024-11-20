package com.software.website.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.software.website.Entity.Users_Device;
import com.software.website.service.Users_DeviceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class Users_DeviceController {

    @Autowired
    private Users_DeviceService deviceService;

    @GetMapping("/allDevices")
    public List<Users_Device> getAllDevices() {
        return deviceService.getAllDevices();
    }

    @GetMapping("/OneDevice/{id}")
    public Users_Device getOneDeviceById(@PathVariable int id) {
        return deviceService.getOneDeviceByID(id);
    }
    
    
    
    
}
