package com.iti.twitter.twitter_project.service;

import com.iti.twitter.twitter_project.dto.UserRequestDto;
import com.iti.twitter.twitter_project.dto.UserResponseDto;
import com.iti.twitter.twitter_project.model.UserEntity;
import com.iti.twitter.twitter_project.repository.UsersRepo;
import com.iti.twitter.twitter_project.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    private UserService underTest;
    @Mock
    private UsersRepo usersRepo;

    @BeforeEach
    void setUp(){
        underTest = new UserService(usersRepo);
    }


    @Test
    void shouldAddUser(){
        // given
        UserEntity entity = new UserEntity();
        entity.setName("ahmed");
        entity.setPasswordDigest("1234");
        entity.setEmail("test@test.com");
        when(usersRepo.save(entity)).thenReturn(entity);

        // when
        UserResponseDto responseDto = underTest.save(UserRequestDto.builder().name(entity.getName()).passwordDigest(entity.getPasswordDigest()).email(entity.getEmail()).build());
        // then
        assertAll(
                () -> assertEquals(responseDto.getName(),entity.getName()),
                () -> assertEquals(responseDto.getPasswordDigest(),entity.getPasswordDigest()),
                () -> assertEquals(responseDto.getEmail(),entity.getEmail())
        );
    }

    @Test
    void shouldReturnAllUsers(){
        // given
        List<UserEntity> entities = new ArrayList<>();
        for(int i=0;i<5;i++){
            entities.add(new UserEntity());
        }
        when(usersRepo.findAll()).thenReturn(entities);
        // when
        List<UserResponseDto> response = underTest.getAllUsers();
        // then
        assertEquals(response.size(),5);
    }

    @Test
    void shouldReturnNullWhenUserNotFound(){
        // given
        Long id = 1l;
        when(usersRepo.findById(id)).thenReturn(Optional.empty());
        // when
        UserResponseDto response = underTest.getUserById(id);
        // then
        assertEquals(response,null);
    }


    @Test
    void shouldReturnUserResponseWhenUserIsFound(){
        // given
        Long id = 1l;
        Optional<UserEntity> entity = Optional.of(new UserEntity());
        entity.get().setId(id);
        when(usersRepo.findById(id)).thenReturn(entity);

        // when
        UserResponseDto response = underTest.getUserById(id);

        // then
        assertEquals(response.getId(),id);
    }

    @Test
    void shouldReturnNullWhenTryingToChangePasswordForNonExistingUser(){
        // given
        Long id = 1l;
        when(usersRepo.findById(id)).thenReturn(Optional.empty());
        // when
        UserResponseDto response =  underTest.updateUserPassword(id,"testPass");
        // then
        assertEquals(response,null);
    }


    @Test
    void ShouldChangeThePasswordSuccessfully(){
        // given
        Long id = 1l;
        Optional<UserEntity> entity = Optional.of(new UserEntity());
        entity.get().setId(id);
        entity.get().setPasswordDigest("1234");
        when(usersRepo.findById(id)).thenReturn(entity);
        // when
        UserResponseDto response = underTest.updateUserPassword(id,"newPass");
        // then
        assertAll(
                () -> assertEquals(response.getId(),entity.get().getId()),
                () -> assertEquals(response.getPasswordDigest(),"newPass")
        );

    }
}

