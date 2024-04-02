package com.d4u.Decision4You.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * Base class for all domain entities (that are persisted to the as an own collection)
 */
@Getter
public abstract class BaseEntity<PK extends Serializable> {

    @Id
    protected final PK id;

    // Spring Data automatically sets the createdAt field when the entity is created in DB.
    // Needs @EnableMongoAuditing in MongoConfig
    @CreatedDate
    private Instant createdAt;

    // Spring Data automatically updates the lastModifiedAt field when the entity is updated in DB.
    // Needs @EnableMongoAuditing in MongoConfig
    @LastModifiedDate
    private Instant lastModifiedAt;

    // Spring Data automatically set the version field when the entity is updated in DB.
    @Version
    private Long version;

    // ctor -----------------------------------------------------------------
    protected BaseEntity(PK id) {
        this.id = id;
    }

    // hash/equals ----------------------------------------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity<?> that = (BaseEntity<?>) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
