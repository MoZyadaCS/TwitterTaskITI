package com.iti.twitter.twitter_project.mapper;


import com.iti.twitter.twitter_project.dto.UserRequestDto;
import com.iti.twitter.twitter_project.dto.UserResponseDto;
import com.iti.twitter.twitter_project.model.UserEntity;

public class UserMapper {

    public static UserResponseDto userEntityToUserResponse(UserEntity userEntity){
            return UserResponseDto.builder().id(userEntity.getId()).email(userEntity.getEmail()).passwordDigest(userEntity.getPasswordDigest()).name(userEntity.getName()).slug(userEntity.getSlug()).createdAt(userEntity.getCreatedAt()).updatedAt(userEntity.getUpdatedAt()).build();
        }

    public static UserEntity userRequestToUserEntity(UserRequestDto userRequest){
            return UserEntity.builder().name(userRequest.getName()).rememberToken(userRequest.getRememberToken()).email(userRequest.getEmail()).passwordDigest(userRequest.getPasswordDigest()).createdAt(userRequest.getCreatedAt()).updatedAt(userRequest.getUpdatedAt()).build();
        }





}
