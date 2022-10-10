package com.iti.twitter.twitter_project.controller;

import com.iti.twitter.twitter_project.dto.TweetRequestDto;
import com.iti.twitter.twitter_project.dto.TweetResponseDto;
import com.iti.twitter.twitter_project.service.RelationshipService;
import com.iti.twitter.twitter_project.service.TweetService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TweetsControllerTest {

    @Mock
    TweetService tweetService;

    @Mock
    RelationshipService relationshipService;

    TweetsController underTest;



    @BeforeEach
    void setUp() {
        underTest = new TweetsController(tweetService,relationshipService);
    }

    @Test
    void shouldAddTweetByUser() {
        // given
        TweetRequestDto requestDto = TweetRequestDto.builder().userId(1).content("test").build();
        TweetResponseDto responseDto = TweetResponseDto.builder().userId(1).content("test").build();
        when(tweetService.addTweet(requestDto)).thenReturn(responseDto);
        // when
        ResponseEntity<TweetResponseDto> response = underTest.addTweetByUser(requestDto);
        // then
        assertAll(
                () -> assertEquals(response.getBody().getUserId(),responseDto.getUserId()),
                () -> assertEquals(response.getBody().getContent(),responseDto.getContent()),
                () -> assertEquals(response.getStatusCode(),HttpStatus.CREATED)
                );
    }

    @Test
    void shouldGetTweetsByUserId() {
        // given
        Long id = 1l;
        List<TweetResponseDto> responseDtos = new ArrayList<>();
        for (int i=0;i<5;i++){
            responseDtos.add(TweetResponseDto.builder().build());
        }
        when(tweetService.getAllTweetsByUserId(id)).thenReturn(responseDtos);
        // when
       ResponseEntity<List<TweetResponseDto>> response = underTest.getTweetsByUserId(id);
        // then
        assertAll(
                () -> assertEquals(response.getBody().size(),responseDtos.size()),
                () -> assertEquals(response.getStatusCode(),HttpStatus.FOUND)
        );
    }

    @Test
    void shouldGetTweetsOfFollowers() {
        // given
        Long id = 1l;
        List<Long> ids = new ArrayList<>();
        for (Long i=0l;i<5;i++){
            ids.add(i);
        }
        when(relationshipService.getAllRelationShipsByFollowerID(id)).thenReturn(ids);
        List<TweetResponseDto> tweets = new ArrayList<>();
        for (int i=0;i<10;i++){
            tweets.add(TweetResponseDto.builder().build());
        }
        when(tweetService.getAllTweetsIn(ids)).thenReturn(tweets);
        // when
        ResponseEntity<List<TweetResponseDto>> response = underTest.getTweetsOfFollowers(id);
        // then
        assertAll(
                () -> assertEquals(response.getBody().size(),tweets.size()),
                () -> assertEquals(response.getStatusCode(),HttpStatus.FOUND)
        );

    }
}