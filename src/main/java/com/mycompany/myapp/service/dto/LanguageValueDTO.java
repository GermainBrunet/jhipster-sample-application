package com.mycompany.myapp.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.LanguageValue} entity.
 */
public class LanguageValueDTO implements Serializable {
    
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

    public void setCodeId(Long languageCodeId) {
        this.codeId = languageCodeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LanguageValueDTO languageValueDTO = (LanguageValueDTO) o;
        if (languageValueDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), languageValueDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LanguageValueDTO{" +
            "id=" + getId() +
            ", value='" + getValue() + "'" +
            ", codeId=" + getCodeId() +
            "}";
    }
}
