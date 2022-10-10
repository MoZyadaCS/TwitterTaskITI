package com.iti.twitter.twitter_project.repository;

import com.iti.twitter.twitter_project.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepo extends JpaRepository<UserEntity,Long> {

}

