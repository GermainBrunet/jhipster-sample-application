package com.mycompany.myapp.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Exercise} entity.
 */
public class ExerciseDTO implements Serializable {
    
    private Long id;

    @NotNull
    private UUID guid;

    @NotNull
    private Integer sortOrder;

    @NotNull
    private String initialWord;

    @NotNull
    private String targetWord;

    @NotNull
    private String readInstructions;

    @NotNull
    private String writtenInstructions;


    private Long exerciseGroupId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getGuid() {
        return guid;
    }

    public void setGuid(UUID guid) {
        this.guid = guid;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getInitialWord() {
        return initialWord;
    }

    public void setInitialWord(String initialWord) {
        this.initialWord = initialWord;
    }

    public String getTargetWord() {
        return targetWord;
    }

    public void setTargetWord(String targetWord) {
        this.targetWord = targetWord;
    }

    public String getReadInstructions() {
        return readInstructions;
    }

    public void setReadInstructions(String readInstructions) {
        this.readInstructions = readInstructions;
    }

    public String getWrittenInstructions() {
        return writtenInstructions;
    }

    public void setWrittenInstructions(String writtenInstructions) {
        this.writtenInstructions = writtenInstructions;
    }

    public Long getExerciseGroupId() {
        return exerciseGroupId;
    }

    public void setExerciseGroupId(Long exerciseGroupId) {
        this.exerciseGroupId = exerciseGroupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ExerciseDTO exerciseDTO = (ExerciseDTO) o;
        if (exerciseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), exerciseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ExerciseDTO{" +
            "id=" + getId() +
            ", guid='" + getGuid() + "'" +
            ", sortOrder=" + getSortOrder() +
            ", initialWord='" + getInitialWord() + "'" +
            ", targetWord='" + getTargetWord() + "'" +
            ", readInstructions='" + getReadInstructions() + "'" +
            ", writtenInstructions='" + getWrittenInstructions() + "'" +
            ", exerciseGroupId=" + getExerciseGroupId() +
            "}";
    }
}
