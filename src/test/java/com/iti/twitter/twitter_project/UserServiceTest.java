package com.iti.twitter.twitter_project;

import com.iti.twitter.twitter_project.dto.UserRequestDto;
import com.iti.twitter.twitter_project.dto.UserResponseDto;
import com.iti.twitter.twitter_project.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Date;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;



    @Test
    void shouldAddUser(){
        UserRequestDto user = UserRequestDto.builder().name("test").email("test@test.com").rememberToken("1234").slug("test").passwordDigest("test").createdAt(new Timestamp(new Date().getTime())).updatedAt(new Timestamp(new Date().getTime())).build();
        UserResponseDto responseDto = userService.save(user);
        Assertions.assertEquals(user.getName(),responseDto.getName());
    }

}
