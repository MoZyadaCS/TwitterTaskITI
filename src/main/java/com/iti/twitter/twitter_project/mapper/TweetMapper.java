package com.iti.twitter.twitter_project.mapper;

import com.iti.twitter.twitter_project.dto.TweetRequestDto;
import com.iti.twitter.twitter_project.dto.TweetResponseDto;
import com.iti.twitter.twitter_project.model.TweetEntity;

public class TweetMapper {

    public static TweetEntity tweetRequestToTweetEntity(TweetRequestDto tweetRequest){
        return TweetEntity.builder().content(tweetRequest.getContent()).createdAt(tweetRequest.getCreatedAt()).updatedAt(tweetRequest.getUpdatedAt()).userId(tweetRequest.getUserId()).build();
    }

    public static TweetResponseDto tweetEntityToTweetResponse(TweetEntity tweetEntity){
        return TweetResponseDto.builder().id(tweetEntity.getId()).content(tweetEntity.getContent()).createdAt(tweetEntity.getCreatedAt()).updatedAt(tweetEntity.getUpdatedAt()).userId(tweetEntity.getUserId()).build();
    }
}
