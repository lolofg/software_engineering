package com.software.website.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.software.website.Entity.Device;
import com.software.website.service.DeviceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/allDevices")
    public List<Device> getAllDevices() {
        return deviceService.getAllDevices();
    }

    @GetMapping("/OneDevice/{id}")
    public Device getOneDeviceById(@RequestParam String id) {
        return deviceService.getOneDeviceByID(id);
    }

    @PostMapping
    public ResponseEntity<String> addDevice(@RequestBody Device device) {
        deviceService.addDevice(device);
        return ResponseEntity.ok("Device added successfully.");
    }

    @DeleteMapping("Iventory/{id}")
    public ResponseEntity<String> removeDevice(@PathVariable int id) {
        deviceService.removeDevice(id);
        return ResponseEntity.ok("Device removed successfully.");
    }
}
