package com.d4u.Decision4You.domain.project;

import com.d4u.Decision4You.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.mongodb.core.index.Indexed;
import static com.d4u.Decision4You.foundation.AssertUtil.isNotNull;
import static com.d4u.Decision4You.foundation.EntityUtil.generateUUIDv4;

@Getter
@ToString
@Document(collection = "vote")
public class Vote extends BaseEntity<String> {

    // The project this vote is for.
    @Indexed
    private String projectId;

    // The voter who cast this vote.
    @Indexed private String voterId;

    // The pairwise comparison vote results.
    private int[][] voteResults;

    // The weighted assessments in percentages for each option.
    private double[] weights;


    // ctor --------------------------------------------

    // Constructor for Spring Data to use when creating a new user from DB into memory.
    // Spring Data uses reflection to create an instance of this class.
    @PersistenceCreator
    @JsonCreator
    protected Vote(String id) {
        super(id);
    }

    // Constructor for us developers to use when creating a new user in memory.
    public Vote(String projectId, String voterId, int[][] voteResults, double[] weights) {
        super(generateUUIDv4());

        this.projectId = isNotNull(projectId, "projectId");
        this.voterId = isNotNull(voterId, "voterId");
        this.voteResults = voteResults;
        this.weights = weights;
    }
}