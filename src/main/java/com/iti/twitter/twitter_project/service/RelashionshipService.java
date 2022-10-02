package com.iti.twitter.twitter_project.service;

import com.iti.twitter.twitter_project.repository.RelationshipRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelashionshipService {
    private RelationshipRepo relationshipRepo;

    public RelashionshipService (RelationshipRepo relationshipRepo){
        this.relationshipRepo = relationshipRepo;
    }

    public List<Long> getAllRelationShipsByFollowerID(Long id){
        return relationshipRepo.findAllIdsByFollowerId(id);
    }
}
