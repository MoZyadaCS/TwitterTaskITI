package com.iti.twitter.twitter_project.repository;

import com.iti.twitter.twitter_project.model.TweetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepo extends JpaRepository<TweetEntity,Long> {

    public List<TweetEntity> findAllByUserId(Long id);

    public List<TweetEntity> findAllByUserIdIn(List<Long> ids);
}
