package com.software.website.service;

import com.software.website.Entity.Users_Device;
import com.software.website.RowMapper.Users_DeviceRowMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Users_DeviceServiceTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private Users_DeviceService deviceService;

    public Users_DeviceServiceTest() {
        // Initialize mocks
        org.mockito.MockitoAnnotations.openMocks(this);
    }

    // Test: Get all devices
    @Test
    void shouldReturnAllDevices() {
        // Mock data
        List<Users_Device> mockDevices = Arrays.asList(
                new Users_Device(1, 1, 1, "Device 1"),
                new Users_Device(2, 2, 2, "Device 2")
        );

        // Mock behavior
        when(jdbcTemplate.query(anyString(), any(Users_DeviceRowMapper.class))).thenReturn(mockDevices);

        // Call method
        List<Users_Device> devices = deviceService.getAllDevices();

        // Verify and assert
        assertEquals(2, devices.size());
        assertEquals("Device 1", devices.get(0).getName());
        assertEquals(2, devices.get(1).getDeviceID());
        verify(jdbcTemplate, times(1)).query("select * from Users_device", new Users_DeviceRowMapper());
    }

    // Test: Get one device by ID
    @Test
    void shouldReturnDeviceByID() {
        // Mock data
        Users_Device mockDevice = new Users_Device(1, 1, 1, "Device 1");

        // Mock behavior
        when(jdbcTemplate.queryForObject(anyString(), any(Users_DeviceRowMapper.class), eq(1))).thenReturn(mockDevice);

        // Call method
        Users_Device device = deviceService.getOneDeviceByID(1);

        // Verify and assert
        assertNotNull(device);
        assertEquals(1, device.getDeviceID());
        assertEquals("Device 1", device.getName());
        verify(jdbcTemplate, times(1)).queryForObject(
                "select * from Users_device WHERE DeviceID = ?",
                new Users_DeviceRowMapper(),
                1
        );
    }

    @Test
    void shouldThrowExceptionForInvalidDeviceID() {
        // Mock behavior for an invalid ID
        when(jdbcTemplate.queryForObject(anyString(), any(Users_DeviceRowMapper.class), eq(99)))
                .thenThrow(new RuntimeException("Device not found"));

        // Call method and assert exception
        Exception exception = assertThrows(RuntimeException.class, () -> deviceService.getOneDeviceByID(99));
        assertEquals("Device not found", exception.getMessage());
    }

    // Test: Add a device
    @Test
    void shouldAddDeviceSuccessfully() {
        // Mock input
        Users_Device mockDevice = new Users_Device(1, 1, 1, "Device 1");

        // Mock behavior
        doNothing().when(jdbcTemplate).update(anyString(), anyInt(), anyInt(), anyString());

        // Call method
        deviceService.addDevice(mockDevice);

        // Capture arguments
        ArgumentCaptor<String> queryCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Object> argsCaptor = ArgumentCaptor.forClass(Object.class);

        // Verify interactions
        verify(jdbcTemplate, times(1)).update(queryCaptor.capture(), argsCaptor.capture(), argsCaptor.capture(), argsCaptor.capture());

        // Assert query and arguments
        assertEquals("INSERT INTO Device (Device_ID, Product_ID, Name) VALUES (?, ?, ?)", queryCaptor.getValue());
        assertEquals(1, argsCaptor.getAllValues().get(0));
        assertEquals(1, argsCaptor.getAllValues().get(1));
        assertEquals("Device 1", argsCaptor.getAllValues().get(2));
    }

    // Test: Remove a device
    @Test
    void shouldRemoveDeviceSuccessfully() {
        // Mock behavior
        doNothing().when(jdbcTemplate).update(anyString(), eq(1));

        // Call method
        deviceService.removeDevice(1);

        // Verify
        verify(jdbcTemplate, times(1)).update("DELETE FROM Device WHERE Device_ID = ?", 1);
    }

    @Test
    void shouldThrowExceptionWhenRemovingNonexistentDevice() {
        // Mock behavior
        doThrow(new RuntimeException("Device not found")).when(jdbcTemplate).update(anyString(), eq(99));

        // Call method and assert exception
        Exception exception = assertThrows(RuntimeException.class, () -> deviceService.removeDevice(99));
        assertEquals("Device not found", exception.getMessage());
    }

    // Test: Get all devices for a specific user
    @Test
    void shouldReturnDevicesForUser() {
        // Mock data
        List<Users_Device> mockDevices = Arrays.asList(
                new Users_Device(1, 1, 1, "Device 1"),
                new Users_Device(2, 1, 2, "Device 2")
        );

        // Mock behavior
        when(jdbcTemplate.query(anyString(), any(Users_DeviceRowMapper.class), eq(1))).thenReturn(mockDevices);

        // Call method
        List<Users_Device> devices = deviceService.getOneUsersDevices(1);

        // Verify and assert
        assertEquals(2, devices.size());
        assertEquals("Device 1", devices.get(0).getName());
        verify(jdbcTemplate, times(1)).query("SELECT * FROM Users_device WHERE UsersID = ?", new Users_DeviceRowMapper(), 1);
    }
}
