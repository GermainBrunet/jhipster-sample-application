package com.mycompany.myapp.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import java.util.UUID;
import com.mycompany.myapp.domain.enumeration.Language;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.ExerciseGroup} entity.
 */
public class ExerciseGroupDTO implements Serializable {
    
    private Long id;

    private Language language;

    @NotNull
    private UUID guid;

    private Integer sortOrder;

    private String author;


    private Long nameId;

    private Long descriptionId;
    private Set<KeywordDTO> keywords = new HashSet<>();

    private Long levelId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getNameId() {
        return nameId;
    }

    public void setNameId(Long labelId) {
        this.nameId = labelId;
    }

    public Long getDescriptionId() {
        return descriptionId;
    }

    public void setDescriptionId(Long labelId) {
        this.descriptionId = labelId;
    }

    public Set<KeywordDTO> getKeywords() {
        return keywords;
    }

    public void setKeywords(Set<KeywordDTO> keywords) {
        this.keywords = keywords;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ExerciseGroupDTO exerciseGroupDTO = (ExerciseGroupDTO) o;
        if (exerciseGroupDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), exerciseGroupDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ExerciseGroupDTO{" +
            "id=" + getId() +
            ", language='" + getLanguage() + "'" +
            ", guid='" + getGuid() + "'" +
            ", sortOrder=" + getSortOrder() +
            ", author='" + getAuthor() + "'" +
            ", nameId=" + getNameId() +
            ", descriptionId=" + getDescriptionId() +
            ", keywords='" + getKeywords() + "'" +
            ", levelId=" + getLevelId() +
            "}";
    }
}
