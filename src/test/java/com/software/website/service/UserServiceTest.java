package com.software.website.service;

import com.software.website.Entity.Users;
import com.software.website.RowMapper.UserRowMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        Users user1 = new Users();
        user1.setUserID(1);
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setEmail("john.doe@example.com");

        Users user2 = new Users();
        user2.setUserID(2);
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setEmail("jane.doe@example.com");

        List<Users> mockUsers = Arrays.asList(user1, user2);

        when(jdbcTemplate.query(eq("select * from Users"), any(UserRowMapper.class))).thenReturn(mockUsers);

        List<Users> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Jane", result.get(1).getFirstName());

        verify(jdbcTemplate).query(eq("select * from Users"), any(UserRowMapper.class));
    }

    @Test
    void testGetOneUserByID() {
        Users mockUser = new Users();
        mockUser.setUserID(1);
        mockUser.setFirstName("John");
        mockUser.setLastName("Doe");
        mockUser.setEmail("john.doe@example.com");

        when(jdbcTemplate.queryForObject(
            eq("select * from Users where UserID = ?"),
            any(UserRowMapper.class),
            eq(1)
        )).thenReturn(mockUser);

        Users result = userService.getOneUserByID(1);

        assertNotNull(result);
        assertEquals(1, result.getUserID());
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());

        verify(jdbcTemplate).queryForObject(
            eq("select * from Users where UserID = ?"),
            any(UserRowMapper.class),
            eq(1)
        );
    }
}

