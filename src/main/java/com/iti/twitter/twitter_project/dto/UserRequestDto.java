package com.iti.twitter.twitter_project.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;


@Data
@Builder
public class UserRequestDto {
    private String name;
    private String email;
    private String passwordDigest;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String rememberToken;
    private String slug;
}
