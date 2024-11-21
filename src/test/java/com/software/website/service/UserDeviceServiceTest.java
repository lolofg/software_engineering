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

    @Test
    void shouldReturnAllDevices() {
        List<Users_Device> mockDevices = Arrays.asList(
                new Users_Device(1, 1, 1, "Device 1"),
                new Users_Device(2, 2, 2, "Device 2")
        );

        when(jdbcTemplate.query(anyString(), any(Users_DeviceRowMapper.class))).thenReturn(mockDevices);

        List<Users_Device> devices = deviceService.getAllDevices();

        assertEquals(2, devices.size());
        assertEquals("Device 1", devices.get(0).getName());
        assertEquals(2, devices.get(1).getDeviceID());
        verify(jdbcTemplate, times(1)).query("select * from Users_device", new Users_DeviceRowMapper());
    }

    @Test
    void shouldReturnDeviceByID() {
        Users_Device mockDevice = new Users_Device(1, 1, 1, "Device 1");

        when(jdbcTemplate.queryForObject(anyString(), any(Users_DeviceRowMapper.class), eq(1))).thenReturn(mockDevice);

        Users_Device device = deviceService.getOneDeviceByID(1);

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
        when(jdbcTemplate.queryForObject(anyString(), any(Users_DeviceRowMapper.class), eq(99)))
                .thenThrow(new RuntimeException("Device not found"));

        Exception exception = assertThrows(RuntimeException.class, () -> deviceService.getOneDeviceByID(99));
        assertEquals("Device not found", exception.getMessage());
    }

    @Test
    void shouldAddDeviceSuccessfully() {
        Users_Device mockDevice = new Users_Device(1, 1, 1, "Device 1");

        doNothing().when(jdbcTemplate).update(anyString(), anyInt(), anyInt(), anyString());

        deviceService.addDevice(mockDevice);

        ArgumentCaptor<String> queryCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Object> argsCaptor = ArgumentCaptor.forClass(Object.class);

        verify(jdbcTemplate, times(1)).update(queryCaptor.capture(), argsCaptor.capture(), argsCaptor.capture(), argsCaptor.capture());

        assertEquals("INSERT INTO Device (Device_ID, Product_ID, Name) VALUES (?, ?, ?)", queryCaptor.getValue());
        assertEquals(1, argsCaptor.getAllValues().get(0));
        assertEquals(1, argsCaptor.getAllValues().get(1));
        assertEquals("Device 1", argsCaptor.getAllValues().get(2));
    }

    @Test
    void shouldRemoveDeviceSuccessfully() {
        doNothing().when(jdbcTemplate).update(anyString(), eq(1));

        deviceService.removeDevice(1);

        verify(jdbcTemplate, times(1)).update("DELETE FROM Device WHERE Device_ID = ?", 1);
    }

    @Test
    void shouldThrowExceptionWhenRemovingNonexistentDevice() {
        doThrow(new RuntimeException("Device not found")).when(jdbcTemplate).update(anyString(), eq(99));

        Exception exception = assertThrows(RuntimeException.class, () -> deviceService.removeDevice(99));
        assertEquals("Device not found", exception.getMessage());
    }

    @Test
    void shouldReturnDevicesForUser() {
        List<Users_Device> mockDevices = Arrays.asList(
                new Users_Device(1, 1, 1, "Device 1"),
                new Users_Device(2, 1, 2, "Device 2")
        );

        when(jdbcTemplate.query(anyString(), any(Users_DeviceRowMapper.class), eq(1))).thenReturn(mockDevices);

        List<Users_Device> devices = deviceService.getOneUsersDevices(1);

        assertEquals(2, devices.size());
        assertEquals("Device 1", devices.get(0).getName());
        verify(jdbcTemplate, times(1)).query("SELECT * FROM Users_device WHERE UsersID = ?", new Users_DeviceRowMapper(), 1);
    }
}
