package com.software.website.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.software.website.service.Users_DeviceService;
import com.software.website.Entity.Users_Device;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(Users_DeviceController.class) 
public class UserDeviceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Users_DeviceService deviceService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnAllDevices() throws Exception {
        Users_Device device1 = new Users_Device(2, 3, 100, "Type A");
        Users_Device device2 = new Users_Device(5, 1, 101, "Type B");
        List<Users_Device> devices = Arrays.asList(device1, device2);

        when(deviceService.getAllDevices()).thenReturn(devices);

        mockMvc.perform(get("/GetallDevices")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].deviceID").value(2))
                .andExpect(jsonPath("$[0].usersID").value(3))
                .andExpect(jsonPath("$[0].productID").value(100))
                .andExpect(jsonPath("$[0].name").value("Type A"))
                .andExpect(jsonPath("$[1].deviceID").value(5))
                .andExpect(jsonPath("$[1].usersID").value(1))
                .andExpect(jsonPath("$[1].productID").value(101))
                .andExpect(jsonPath("$[1].name").value("Type B"));
    }

    @Test
    void shouldReturnDeviceById() throws Exception {

        Users_Device device = new Users_Device(1, 2, 2, "Type A");

        when(deviceService.getOneDeviceByID(1)).thenReturn(device);

        mockMvc.perform(get("/GetOneDevice/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deviceID").value(1))
                .andExpect(jsonPath("$.usersID").value(2))
                .andExpect(jsonPath("$.productID").value(2))
                .andExpect(jsonPath("$.name").value("Type A"));
    }

    @Test
    void shouldReturnNotFoundForInvalidId() throws Exception {
        when(deviceService.getOneDeviceByID(999)).thenThrow(new RuntimeException("Device not found"));

        mockMvc.perform(get("/GetOneDevice/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Device not found"));
    }


    @Test
    public void testAddDevice() throws Exception {
        // Create a sample Users_Device object
        Users_Device device = new Users_Device();
        device.setDeviceID(101);
        device.setUsersID(1001);
        device.setProductID(202);
        device.setName("Smart Thermostat");

        doNothing().when(deviceService).addDevice(device);

        mockMvc.perform(post("/AddDeviceToUser/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(device)))
                .andExpect(status().isOk())
                .andExpect(content().string("Device added successfully."));
    }

    @Test
    void shouldRemoveDeviceSuccessfully() throws Exception {
        doNothing().when(deviceService).removeDevice(1);

        mockMvc.perform(delete("/DeleteOneDevice/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Device removed successfully."));
    }

}
