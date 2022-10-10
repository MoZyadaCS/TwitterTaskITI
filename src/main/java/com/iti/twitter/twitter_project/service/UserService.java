package com.iti.twitter.twitter_project.service;

import com.iti.twitter.twitter_project.dto.UserRequestDto;
import com.iti.twitter.twitter_project.dto.UserResponseDto;
import com.iti.twitter.twitter_project.mapper.UserMapper;
import com.iti.twitter.twitter_project.model.UserEntity;
import com.iti.twitter.twitter_project.repository.UsersRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UsersRepo usersRepo;

    public UserService(UsersRepo usersRepo){
        this.usersRepo = usersRepo;
    }

    public UserResponseDto save(UserRequestDto user){

        return UserMapper.userEntityToUserResponse(usersRepo.save(UserMapper.userRequestToUserEntity(user)));
    }

    public List<UserResponseDto> getAllUsers(){
        List<UserResponseDto> response = new ArrayList<>();
        for (UserEntity user : usersRepo.findAll()){
            response.add(UserMapper.userEntityToUserResponse(user));
        }
        return response;
    }
    public UserResponseDto getUserById(Long id){
        Optional<UserEntity> user = usersRepo.findById(id);
        if(!user.isEmpty()){
            return UserMapper.userEntityToUserResponse(user.get());
        }
        else {
            return null;
        }
    }

    public UserResponseDto updateUserPassword(Long id,String pass){
        Optional<UserEntity> user = usersRepo.findById(id);
        if(user.isEmpty()) return null;
        else{
            user.get().setPasswordDigest(pass);
            usersRepo.save(user.get());
            return UserMapper.userEntityToUserResponse(user.get());
        }
    }
}
