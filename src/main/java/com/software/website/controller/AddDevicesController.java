package com.software.website.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class AddDevicesController {

    @Autowired
    AddDevicesController addDevicesController; 
    @PostMapping("/addUnitToListOfUnits")
    public void addDevicesToListOfUnits(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    
    
}
