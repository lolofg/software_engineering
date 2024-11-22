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

public class UsersDeviceServiceTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private Users_DeviceService deviceService;

    public UsersDeviceServiceTest() {
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

        verify(jdbcTemplate, times(1)).query(eq("select * from Users_device"), any(Users_DeviceRowMapper.class));
    }

    @Test
    void shouldReturnDeviceByID() {
        Users_Device mockDevice = new Users_Device(1, 1, 1, "Device 1");

        when(jdbcTemplate.queryForObject(
                eq("select * from Users_device WHERE DeviceID = ?"),
                any(Users_DeviceRowMapper.class),
                eq(1))
        ).thenReturn(mockDevice);

        Users_Device device = deviceService.getOneDeviceByID(1);

        assertNotNull(device);
        assertEquals(1, device.getDeviceID());
        assertEquals("Device 1", device.getName());

        verify(jdbcTemplate, times(1)).queryForObject(
                eq("select * from Users_device WHERE DeviceID = ?"),
                any(Users_DeviceRowMapper.class),
                eq(1)
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

        when(jdbcTemplate.update(
                eq("INSERT INTO Users_Device(UsersID, ProductID, Name) VALUES (?, ?, ?)"),
                eq(1), eq(1), eq("Device 1")
        )).thenReturn(1);

        deviceService.addDevice(mockDevice);

        verify(jdbcTemplate, times(1)).update(
                eq("INSERT INTO Users_Device(UsersID, ProductID, Name) VALUES (?, ?, ?)"),
                eq(1), eq(1), eq("Device 1")
        );
    }

    @Test
    void shouldRemoveDeviceSuccessfully() {
        when(jdbcTemplate.update(
                eq("DELETE FROM Users_device WHERE DeviceID = ?"),
                eq(1)
        )).thenReturn(1);

        deviceService.removeDevice(1);

        verify(jdbcTemplate, times(1)).update(
                eq("DELETE FROM Users_device WHERE DeviceID = ?"),
                eq(1)
        );
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

        when(jdbcTemplate.query(
                eq("SELECT * FROM Users_device WHERE UsersID = ?"),
                any(Users_DeviceRowMapper.class),
                eq(1)
        )).thenReturn(mockDevices);

        List<Users_Device> devices = deviceService.getOneUsersDevices(1);

        assertEquals(2, devices.size());
        assertEquals("Device 1", devices.get(0).getName());
        assertEquals("Device 2", devices.get(1).getName());

        verify(jdbcTemplate, times(1)).query(
                eq("SELECT * FROM Users_device WHERE UsersID = ?"),
                any(Users_DeviceRowMapper.class),
                eq(1)
        );
    }
}
