package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Exercise.
 */
@Entity
@Table(name = "exercise")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Exercise implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Type(type = "uuid-char")
    @Column(name = "guid", length = 36, nullable = false)
    private UUID guid;

    @NotNull
    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder;

    @NotNull
    @Column(name = "initial_word", nullable = false)
    private String initialWord;

    @NotNull
    @Column(name = "target_word", nullable = false)
    private String targetWord;

    @NotNull
    @Column(name = "read_instructions", nullable = false)
    private String readInstructions;

    @NotNull
    @Column(name = "written_instructions", nullable = false)
    private String writtenInstructions;

    @ManyToOne
    @JsonIgnoreProperties("exercises")
    private ExerciseGroup exerciseGroup;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getGuid() {
        return guid;
    }

    public Exercise guid(UUID guid) {
        this.guid = guid;
        return this;
    }

    public void setGuid(UUID guid) {
        this.guid = guid;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public Exercise sortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getInitialWord() {
        return initialWord;
    }

    public Exercise initialWord(String initialWord) {
        this.initialWord = initialWord;
        return this;
    }

    public void setInitialWord(String initialWord) {
        this.initialWord = initialWord;
    }

    public String getTargetWord() {
        return targetWord;
    }

    public Exercise targetWord(String targetWord) {
        this.targetWord = targetWord;
        return this;
    }

    public void setTargetWord(String targetWord) {
        this.targetWord = targetWord;
    }

    public String getReadInstructions() {
        return readInstructions;
    }

    public Exercise readInstructions(String readInstructions) {
        this.readInstructions = readInstructions;
        return this;
    }

    public void setReadInstructions(String readInstructions) {
        this.readInstructions = readInstructions;
    }

    public String getWrittenInstructions() {
        return writtenInstructions;
    }

    public Exercise writtenInstructions(String writtenInstructions) {
        this.writtenInstructions = writtenInstructions;
        return this;
    }

    public void setWrittenInstructions(String writtenInstructions) {
        this.writtenInstructions = writtenInstructions;
    }

    public ExerciseGroup getExerciseGroup() {
        return exerciseGroup;
    }

    public Exercise exerciseGroup(ExerciseGroup exerciseGroup) {
        this.exerciseGroup = exerciseGroup;
        return this;
    }

    public void setExerciseGroup(ExerciseGroup exerciseGroup) {
        this.exerciseGroup = exerciseGroup;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Exercise)) {
            return false;
        }
        return id != null && id.equals(((Exercise) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Exercise{" +
            "id=" + getId() +
            ", guid='" + getGuid() + "'" +
            ", sortOrder=" + getSortOrder() +
            ", initialWord='" + getInitialWord() + "'" +
            ", targetWord='" + getTargetWord() + "'" +
            ", readInstructions='" + getReadInstructions() + "'" +
            ", writtenInstructions='" + getWrittenInstructions() + "'" +
            "}";
    }
}
