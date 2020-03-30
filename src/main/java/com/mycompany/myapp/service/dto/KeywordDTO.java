package com.mycompany.myapp.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Keyword} entity.
 */
public class KeywordDTO implements Serializable {
    
    private Long id;

    @NotNull
    private UUID guid;


    private Long nameId;

    private Long descriptionId;
    
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        KeywordDTO keywordDTO = (KeywordDTO) o;
        if (keywordDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), keywordDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "KeywordDTO{" +
            "id=" + getId() +
            ", guid='" + getGuid() + "'" +
            ", nameId=" + getNameId() +
            ", descriptionId=" + getDescriptionId() +
            "}";
    }
}
