package com.iti.twitter.twitter_project.service;

import com.iti.twitter.twitter_project.dto.TweetRequestDto;
import com.iti.twitter.twitter_project.dto.TweetResponseDto;
import com.iti.twitter.twitter_project.mapper.TweetMapper;
import com.iti.twitter.twitter_project.model.TweetEntity;
import com.iti.twitter.twitter_project.repository.TweetRepo;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TweetServiceTest {

    private TweetService underTest;

    @Mock
    private TweetRepo tweetRepo;

    @BeforeEach
    void setUp(){
        underTest = new TweetService(tweetRepo);
    }


    @Test
    void shouldAddTweet(){
        // given
        Long id = 1l;
        TweetEntity entity = new TweetEntity();
        entity.setUserId(id);
        entity.setContent("test");
        when(tweetRepo.save(entity)).thenReturn(entity);
        // when
        TweetResponseDto response = underTest.addTweet(TweetRequestDto.builder().userId(id).content("test").build());
        // then
        assertAll(
                () -> assertEquals(response.getUserId(),id),
                () -> assertEquals(response.getContent(),"test")
        );
    }

    @Test
    void shouldGetAllTweetsOfUserById(){
        // given
        Long id = 1l;
        List<TweetEntity> entities = new ArrayList<>();
        for(int i=0;i<5;i++){
            entities.add(new TweetEntity());
        }
        when(tweetRepo.findAllByUserId(id)).thenReturn(entities);
        // when
        List<TweetResponseDto> response = underTest.getAllTweetsByUserId(id);

        // then
        assertEquals(response.size(),5);
    }

    @Test
    void shouldGetTweetsFollowedById(){
        // given
        List<Long> ids = new ArrayList<>();
        ids.add(1l);
        List<TweetEntity> entities = new ArrayList<>();
        for (int i=0;i<5;i++){
            entities.add(new TweetEntity());
        }
        when(tweetRepo.findAllByUserIdIn(ids)).thenReturn(entities);

        // when
        List<TweetResponseDto> response = underTest.getAllTweetsIn(ids);
        // then
        assertEquals(response.size(),5);
    }


}