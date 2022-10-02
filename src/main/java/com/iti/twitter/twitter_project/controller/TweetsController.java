package com.iti.twitter.twitter_project.controller;

import com.iti.twitter.twitter_project.dto.TweetRequestDto;
import com.iti.twitter.twitter_project.dto.TweetResponseDto;
import com.iti.twitter.twitter_project.model.TweetEntity;
import com.iti.twitter.twitter_project.service.RelashionshipService;
import com.iti.twitter.twitter_project.service.TweetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TweetsController {
    private TweetService tweetService;
    private RelashionshipService relashionshipService;


    public TweetsController (TweetService tweetService,RelashionshipService relashionshipService){
        this.tweetService = tweetService;
        this.relashionshipService = relashionshipService;
    }


    @PostMapping("/tweets/add")
    public ResponseEntity<TweetResponseDto> addTweetByUser(@RequestBody TweetRequestDto tweet){
        return new ResponseEntity<>(tweetService.addTweet(tweet), HttpStatus.CREATED);
    }
    @GetMapping("/tweets/{id}")
    public ResponseEntity<List<TweetResponseDto>> getTweetsByUserId(@PathVariable Long id){
        return new ResponseEntity<>(tweetService.getAllTweetsByUserId(id),HttpStatus.FOUND);
    }
    @GetMapping("/tweets/follows/{id}")
    public ResponseEntity<List<TweetResponseDto>> getTweetsOfFollowers(@PathVariable Long id){
        // get all the ids for the follows of this id
        List<Long> ids = relashionshipService.getAllRelationShipsByFollowerID(id);
        // get all the tweets for each user in the follows
        List<TweetResponseDto> tweets = tweetService.getAllTweetsIn(ids);
        return new ResponseEntity<>(tweets,HttpStatus.FOUND);

    }
}
