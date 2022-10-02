
package com.iti.twitter.twitter_project.controller;

import com.iti.twitter.twitter_project.dto.UserRequestDto;
import com.iti.twitter.twitter_project.dto.UserResponseDto;
import com.iti.twitter.twitter_project.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {
    private UserService userService;


    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public String main(){
        return "hello world";
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.FOUND);
    }
    @PostMapping("/users/add")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto user){
        user.setCreatedAt(new Timestamp(new Date().getTime()));
        user.setUpdatedAt(new Timestamp(new Date().getTime()));
        return new ResponseEntity<>(userService.save(user),HttpStatus.CREATED);
    }
    @PostMapping("/users/update")
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody Long id, @RequestBody String password){
        UserResponseDto response = userService.updateUserPassword(id,password);
        if(response != null) {
            return new ResponseEntity<>(response, HttpStatus.FOUND);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
