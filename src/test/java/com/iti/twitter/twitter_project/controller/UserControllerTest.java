package com.iti.twitter.twitter_project.controller;

import com.iti.twitter.twitter_project.dto.UserRequestDto;
import com.iti.twitter.twitter_project.dto.UserResponseDto;
import com.iti.twitter.twitter_project.service.UserService;
import org.apache.catalina.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    UserService userService;

    UserController underTest;

    @BeforeEach
    void setUp() {
        underTest = new UserController(userService);
    }

    @Test
    void shouldGetAllUsers() {
        // given
        List<UserResponseDto> responseDtos = new ArrayList<>();
        for (int i=0;i<10;i++){
            responseDtos.add(UserResponseDto.builder().build());
        }
        when(userService.getAllUsers()).thenReturn(responseDtos);
        // when
        ResponseEntity<List<UserResponseDto>> response = underTest.getAllUsers();
        // then
        assertAll(
                () -> assertEquals(response.getBody().size(),responseDtos.size()),
                () -> assertEquals(response.getStatusCode(), HttpStatus.FOUND)
        );
    }

    @Test
    void shouldCreateUser() {
        // given
        UserRequestDto requestDto = UserRequestDto.builder().name("test").passwordDigest("1234").build();
        UserResponseDto responseDto = UserResponseDto.builder().name("test").passwordDigest("1234").build();
        when(userService.save(requestDto)).thenReturn(responseDto);
        // when
        ResponseEntity<UserResponseDto> response = underTest.createUser(requestDto);
        // then
        assertAll(
                () -> assertEquals(response.getBody().getName(),requestDto.getName()),
                () -> assertEquals(response.getBody().getPasswordDigest(),requestDto.getPasswordDigest()),
                () -> assertEquals(response.getStatusCode(),HttpStatus.CREATED)
        );

    }

    @Test
    void shouldFailToUpdatePasswordWhenUserNotFound() {
        // given
        Long id = 1l;
        String password = "1234";
        when(userService.updateUserPassword(id,password)).thenReturn(null);
        // when
        ResponseEntity<UserResponseDto> response = underTest.updateUser(id,password);
        // then
        assertAll(
                () -> assertEquals(response.getBody(),null),
                () -> assertEquals(response.getStatusCode(),HttpStatus.NOT_FOUND)
        );
    }

    @Test
    void shouldUpdateUserPassword(){
        // given
        Long id = 1l;
        String password = "1234";
        UserResponseDto responseDto = UserResponseDto.builder().id(id).passwordDigest(password).build();
        when(userService.updateUserPassword(id,password)).thenReturn(responseDto);
        // when
        ResponseEntity<UserResponseDto> response = underTest.updateUser(id,password);

        // then
        assertAll(
                () -> assertEquals(response.getBody().getId(),responseDto.getId()),
                () -> assertEquals(response.getBody().getPasswordDigest(), responseDto.getPasswordDigest()),
                () -> assertEquals(response.getStatusCode(),HttpStatus.FOUND)
        );
    }
}