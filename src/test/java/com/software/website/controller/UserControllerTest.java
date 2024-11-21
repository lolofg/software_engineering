package com.software.website.Controller;

import com.software.website.Entity.Users;
import com.software.website.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testGetAllUsers() throws Exception {
        System.out.println("Starter testen for /allUsers");

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

        given(userService.getAllUsers()).willReturn(mockUsers);

        mockMvc.perform(get("/allUsers"))
               .andExpect(status().isOk())
               .andExpect(content().json(
                   "[{'userID':1,'firstName':'John','lastName':'Doe','email':'john.doe@example.com'}," +
                   " {'userID':2,'firstName':'Jane','lastName':'Doe','email':'jane.doe@example.com'}]"
               ));

    
        System.out.println("Test for /allUsers ferdig: SUKSESS");
    }

    @Test
    void testGetOneUserByID() throws Exception {
        System.out.println("Starter testen for /user/{id}");

        Users mockUser = new Users();
        mockUser.setUserID(1);
        mockUser.setFirstName("John");
        mockUser.setLastName("Doe");
        mockUser.setEmail("john.doe@example.com");

        given(userService.getOneUserByID(1)).willReturn(mockUser);

        mockMvc.perform(get("/user/1"))
               .andExpect(status().isOk())
               .andExpect(content().json(
                   "{'userID':1,'firstName':'John','lastName':'Doe','email':'john.doe@example.com'}"
               ));

        System.out.println("Test for /user/{id} ferdig: SUKSESS");
    }
}
