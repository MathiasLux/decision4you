package com.d4u.Decision4You.domain.project;

import com.d4u.Decision4You.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

import static com.d4u.Decision4You.foundation.AssertUtil.isNotNull;
import static com.d4u.Decision4You.foundation.EntityUtil.generateUUIDv4;




@Getter
@ToString
@Document(collection = "vote")
@TypeAlias("vote")
public class Vote extends BaseEntity<String> {

    // The project this vote is for.
    @Indexed
    private String projectId;

    // The voter who cast this vote.
    @Indexed
    private String voterId;

    // The voter's pairwise comparisons of the criteria.
    private double[][] criteriaAssessment;


    // ctor --------------------------------------------

    // Constructor for Spring Data to use when creating a new user from DB into memory.
    // Spring Data uses reflection to create an instance of this class.
    @PersistenceCreator
    @JsonCreator
    protected Vote(String id) {
        super(id);
    }

    // Constructor for us developers to use when creating a new user in memory.
    public Vote(String projectId, String voterId, double[][] criteriaAssessment) {
        super(generateUUIDv4());

        this.projectId = isNotNull(projectId, "projectId");
        this.voterId = isNotNull(voterId, "voterId");

        this.criteriaAssessment = criteriaAssessment;
    }


    // methods --------------------------------------------

    /**
     * Helper method to convert user votes into a pairwise comparison matrix creating a shallow copy.
     */
    public static double[][][] toCriteriaAssessment(List<Vote> votes) {
        return votes.stream().map(Vote::getCriteriaAssessment).toArray(double[][][]::new);
    }


    /**
     * Helper method to combine a new vote with existing votes.
     */
    public static List<Vote> combine(List<Vote> existingVotes, Vote newVote) {
        List<Vote> combinedVotes = new ArrayList<>(existingVotes);
        combinedVotes.add(newVote);
        return combinedVotes;
    }
}
