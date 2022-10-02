package com.iti.twitter.twitter_project.service;

import com.iti.twitter.twitter_project.dto.TweetRequestDto;
import com.iti.twitter.twitter_project.dto.TweetResponseDto;
import com.iti.twitter.twitter_project.mapper.TweetMapper;
import com.iti.twitter.twitter_project.model.TweetEntity;
import com.iti.twitter.twitter_project.repository.TweetRepo;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TweetService {
    private TweetRepo tweetRepo;

    public TweetService(TweetRepo tweetRepo){
        this.tweetRepo = tweetRepo;
    }

    public TweetResponseDto addTweet(TweetRequestDto tweet){
        tweet.setCreatedAt(new Timestamp(new Date().getTime()));
        tweet.setUpdatedAt(new Timestamp(new Date().getTime()));
        TweetEntity tweetEntity = tweetRepo.save(TweetMapper.tweetRequestToTweetEntity(tweet));
        return TweetMapper.tweetEntityToTweetResponse(tweetEntity);
    }
    public List<TweetResponseDto> getAllTweetsByUserId(Long id){
        List<TweetResponseDto> response = new ArrayList<>();
        for (TweetEntity tweet : tweetRepo.findAllByUserId(id)){
            response.add(TweetMapper.tweetEntityToTweetResponse(tweet));
        }
        return response;
    }

    public List<TweetResponseDto> getAllTweetsIn(List<Long> ids){
        List<TweetResponseDto> response = new ArrayList<>();
        for (TweetEntity tweet : tweetRepo.findAllByUserIdIn(ids)){
            response.add(TweetMapper.tweetEntityToTweetResponse(tweet));
        }
        return response;
    }
}
