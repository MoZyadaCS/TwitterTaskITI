package com.iti.twitter.twitter_project.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class TweetResponseDto {

    private Long id;
    private String content;
    private long userId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
