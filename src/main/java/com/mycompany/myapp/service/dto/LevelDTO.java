package com.mycompany.myapp.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Level} entity.
 */
public class LevelDTO implements Serializable {
    
    private Long id;

    @NotNull
    private UUID guid;


    private Long labelId;
    
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

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LevelDTO levelDTO = (LevelDTO) o;
        if (levelDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), levelDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LevelDTO{" +
            "id=" + getId() +
            ", guid='" + getGuid() + "'" +
            ", labelId=" + getLabelId() +
            "}";
    }
}
