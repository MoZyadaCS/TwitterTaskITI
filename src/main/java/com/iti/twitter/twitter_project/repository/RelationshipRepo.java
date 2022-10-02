package com.iti.twitter.twitter_project.repository;

import com.iti.twitter.twitter_project.model.RelationshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;
import java.util.List;

@Repository
public interface RelationshipRepo extends JpaRepository<RelationshipEntity,Long> {
@Query("select r.followedId from RelationshipEntity r where r.followerId = ?1")
    public List<Long> findAllIdsByFollowerId(Long id);

}
