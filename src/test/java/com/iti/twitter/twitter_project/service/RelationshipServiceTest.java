package com.iti.twitter.twitter_project.service;

import com.iti.twitter.twitter_project.repository.RelationshipRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RelationshipServiceTest {

    @Mock
    RelationshipRepo relationshipRepo;

    RelationshipService underTest;
    @BeforeEach
    void setUp(){
        underTest = new RelationshipService(relationshipRepo);
    }


    @Test
    void shouldReturnAllRelationIdsShipsByFlowerID(){
        // given
        Long id = 1l;
        List<Long> ids = new ArrayList<>();
        for(Long i=1l;i<6;i++){
            ids.add(i);
        }
        Mockito.when(relationshipRepo.findAllIdsByFollowerId(id)).thenReturn(ids);
        // when
        List<Long> response = underTest.getAllRelationShipsByFollowerID(id);
        // then

        Assertions.assertEquals(response.size(),ids.size());


    }
}
