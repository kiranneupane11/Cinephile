///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.mycompany.mymovielist;
//import com.mycompany.mymovielist.model.*;
//import com.mycompany.mymovielist.Database.*;
//import com.mycompany.mymovielist.controller.*;
//import com.mycompany.mymovielist.view.*;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//import java.util.Optional;
//import com.mycompany.mymovielist.util.*;
//
//import org.junit.jupiter.api.Test;
//
///**
// *
// * @author kiran
// */
//public class UIHandlerLoginAndSignupTest {
//    @Test
//    public void testLoginWithInjectedPasswordService() {
//        EntityManager emtityManager = mock()
//        DBUserRepository userRepositoryMock = mock(DBUserRepository.class);
//        PasswordService passwordServiceMock = mock(PasswordService.class);
//
//        // Inject your mocks into UIHandler
//        UIHandler uiHandler = new UIHandler(userRepositoryMock, passwordServiceMock);
//
//        // Prepare a fake user and stub methods
//        String username = "testUser";
//        String password = "password123";
//        User fakeUser = new User(username, "test@example.com", password);
//        when(userRepositoryMock.findByUsername(username)).thenReturn(Optional.of(fakeUser));
//        when(passwordServiceMock.verifyPassword(password, fakeUser.getPassword())).thenReturn(true);
//
//        Optional<User> result = uiHandler.login(username, password);
//        assertTrue(result.isPresent(), "User should be present on successful login");
//    }
//        
//}
//
