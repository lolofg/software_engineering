package com.software.website.Controller;

import com.software.website.Service.Users_DeviceService;
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

    @Test
    void shouldReturnAllDevices() throws Exception {
        Users_Device device1 = new Users_Device(1, 1, 1, "Type A");
        Users_Device device2 = new Users_Device(2, 2, 2, "Type B");
        List<Users_Device> devices = Arrays.asList(device1, device2);

        when(deviceService.getAllDevices()).thenReturn(devices);

        mockMvc.perform(get("/allDevices")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].deviceType").value("Type A"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].deviceType").value("Type B"));
    }

    @Test
    void shouldReturnDeviceById() throws Exception {

        Users_Device device = new Users_Device(1, 2, 2, "Type A");

        when(deviceService.getOneDeviceByID(1)).thenReturn(device);

        mockMvc.perform(get("/OneDevice/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.deviceType").value("Type A"));
    }

    @Test
    void shouldReturnNotFoundForInvalidId() throws Exception {
        when(deviceService.getOneDeviceByID(999)).thenThrow(new RuntimeException("Device not found"));

        mockMvc.perform(get("/OneDevice/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Device not found"));
    }

    @Test
    void shouldAddDeviceSuccessfully() throws Exception {
        String deviceJson = """
                {
                    "id": 1,
                    "deviceType": "Type A"
                }
                """;

        doNothing().when(deviceService).addDevice(Mockito.any(Users_Device.class));

        mockMvc.perform(post("/AddIventory/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(deviceJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Device added successfully."));
    }

    @Test
    void shouldReturnBadRequestForInvalidData() throws Exception {
        String invalidDeviceJson = """
                {
                    "deviceType": ""
                }
                """;

        mockMvc.perform(post("/AddIventory/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidDeviceJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldRemoveDeviceSuccessfully() throws Exception {
        doNothing().when(deviceService).removeDevice(1);

        mockMvc.perform(delete("/DeleteIventory/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Device removed successfully."));
    }

    @Test
    void shouldReturnNotFoundForNonExistentDevice() throws Exception {
        doThrow(new IllegalArgumentException("Device not found"))
                .when(deviceService).removeDevice(99);

        mockMvc.perform(delete("/DeleteIventory/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Device not found"));
    }

    @Test
    void shouldHandleBadRequestForInvalidId() throws Exception {
        mockMvc.perform(delete("/DeleteIventory/abc")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
