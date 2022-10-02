package com.iti.twitter.twitter_project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "relationships", schema = "twitter_project", catalog = "")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RelationshipEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "follower_id")
    private Long followerId;
    @Basic
    @Column(name = "followed_id")
    private Long followedId;
    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Basic
    @Column(name = "updated_at")
    private Timestamp updatedAt;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RelationshipEntity entity = (RelationshipEntity) o;

        if (id != entity.id) return false;
        if (followerId != null ? !followerId.equals(entity.followerId) : entity.followerId != null) return false;
        if (followedId != null ? !followedId.equals(entity.followedId) : entity.followedId != null) return false;
        if (createdAt != null ? !createdAt.equals(entity.createdAt) : entity.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(entity.updatedAt) : entity.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (followerId != null ? followerId.hashCode() : 0);
        result = 31 * result + (followedId != null ? followedId.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }
}