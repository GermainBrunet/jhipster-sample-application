package com.mycompany.myapp.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.LabelValue} entity.
 */
public class LabelValueDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String value;


    private Long codeId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getCodeId() {
        return codeId;
    }

    public void setCodeId(Long labelId) {
        this.codeId = labelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LabelValueDTO labelValueDTO = (LabelValueDTO) o;
        if (labelValueDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), labelValueDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LabelValueDTO{" +
            "id=" + getId() +
            ", value='" + getValue() + "'" +
            ", codeId=" + getCodeId() +
            "}";
    }
}
