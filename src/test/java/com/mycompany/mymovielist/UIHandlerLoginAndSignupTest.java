/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist;
import com.mycompany.mymovielist.model.*;
import com.mycompany.mymovielist.Database.*;
import com.mycompany.mymovielist.controller.*;
import com.mycompany.mymovielist.view.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;
import com.mycompany.mymovielist.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
/**
 *
 * @author kiran
 */
public class UIHandlerLoginAndSignupTest {
    
    @Mock
    private DBUserRepository userRepository; 

    @Mock
    private PasswordService passwordService; 

    @InjectMocks
    private UIHandler uiHandler; 

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSuccessfulLogin() {
        String username = "kiran";
        String password = "password123";
        User user = new User(username, "kiran@example.com", password); // This constructor will be bypassed since we re-hash later if needed.

        // Set the hashed password (simulate the user stored password)
        String hashedPassword = "hashedPassword"; 
        user.setPassword(hashedPassword);
        
        // Mock repository behavior
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        
        // Stub the password verification
        when(passwordService.verifyPassword(password, hashedPassword)).thenReturn(true);

        // Perform login
        Optional<User> loggedInUser = uiHandler.login(username, password);

        assertTrue(loggedInUser.isPresent(), "User should be able to login");
        assertEquals(username, loggedInUser.get().getUsername());
    }
}

