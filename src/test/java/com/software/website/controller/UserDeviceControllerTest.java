package com.software.website.controller;

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

@WebMvcTest(Users_DeviceController.class) // Replace with your actual controller name
public class UserDeviceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Users_DeviceService deviceService; // Mock the service layer

    // Test: Get all devices
    @Test
    void shouldReturnAllDevices() throws Exception {
        // Mock data
        Users_Device device1 = new Users_Device(1, 1, 1, "Type A");
        Users_Device device2 = new Users_Device(2, 2, 2, "Type B");
        List<Users_Device> devices = Arrays.asList(device1, device2);

        // Mock service response
        when(deviceService.getAllDevices()).thenReturn(devices);

        // Perform GET request and validate response
        mockMvc.perform(get("/allDevices")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].deviceType").value("Type A"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].deviceType").value("Type B"));
    }

    // Test: Get device by ID
    @Test
    void shouldReturnDeviceById() throws Exception {
        // Mock data
        Users_Device device = new Users_Device(1, 2, 2, "Type A");

        // Mock service response
        when(deviceService.getOneDeviceByID(1)).thenReturn(device);

        // Perform GET request and validate response
        mockMvc.perform(get("/OneDevice/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.deviceType").value("Type A"));
    }

    @Test
    void shouldReturnNotFoundForInvalidId() throws Exception {
        // Mock service to throw an exception for invalid ID
        when(deviceService.getOneDeviceByID(999)).thenThrow(new RuntimeException("Device not found"));

        // Perform GET request with invalid ID
        mockMvc.perform(get("/OneDevice/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Device not found"));
    }

    // Test: Add a device
    @Test
    void shouldAddDeviceSuccessfully() throws Exception {
        // Mock JSON input
        String deviceJson = """
                {
                    "id": 1,
                    "deviceType": "Type A"
                }
                """;

        // Mock service call
        doNothing().when(deviceService).addDevice(Mockito.any(Users_Device.class));

        // Perform POST request
        mockMvc.perform(post("/AddIventory/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(deviceJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Device added successfully."));
    }

    @Test
    void shouldReturnBadRequestForInvalidData() throws Exception {
        // Invalid JSON
        String invalidDeviceJson = """
                {
                    "deviceType": ""
                }
                """;

        // Perform POST request
        mockMvc.perform(post("/AddIventory/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidDeviceJson))
                .andExpect(status().isBadRequest());
    }

    // Test: Remove a device
    @Test
    void shouldRemoveDeviceSuccessfully() throws Exception {
        // Mock service call
        doNothing().when(deviceService).removeDevice(1);

        // Perform DELETE request
        mockMvc.perform(delete("/DeleteIventory/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Device removed successfully."));
    }

    @Test
    void shouldReturnNotFoundForNonExistentDevice() throws Exception {
        // Mock service to throw an exception
        doThrow(new IllegalArgumentException("Device not found"))
                .when(deviceService).removeDevice(99);

        // Perform DELETE request
        mockMvc.perform(delete("/DeleteIventory/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Device not found"));
    }

    @Test
    void shouldHandleBadRequestForInvalidId() throws Exception {
        // Perform DELETE request with invalid ID
        mockMvc.perform(delete("/DeleteIventory/abc")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
