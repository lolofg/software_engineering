package com.software.website.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.software.website.Entity.Users_Device;
import com.software.website.service.Users_DeviceService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class Users_DeviceController {

    @Autowired
    private Users_DeviceService deviceService;

    @GetMapping("/GetallDevices")
    public List<Users_Device> getAllDevices() {
        return deviceService.getAllDevices();
    }

    @GetMapping("/GetOneDevice/{id}")
    public Users_Device getOneDeviceById(@PathVariable int id) {
        return deviceService.getOneDeviceByID(id);
    }

    @PostMapping("AddDeviceToUser/{id}")
    public ResponseEntity<String> addDevice(@RequestBody Users_Device device) {
        deviceService.addDevice(device);
        return ResponseEntity.ok("Device added successfully.");
    }

    @DeleteMapping("DeleteDevice/{id}")
    public ResponseEntity<String> removeDevice(@PathVariable int id) {
        deviceService.removeDevice(id);
        return ResponseEntity.ok("Device removed successfully.");
    }

    @GetMapping("/GetOneUserDevices/{id}")
    public List<Users_Device> GetOneUsersDevices(@PathVariable int id) {
        return deviceService.getOneUsersDevices(id);
    }
}
