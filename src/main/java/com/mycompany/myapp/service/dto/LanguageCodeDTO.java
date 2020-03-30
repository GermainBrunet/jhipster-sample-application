package com.mycompany.myapp.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.mycompany.myapp.domain.enumeration.Language;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.LanguageCode} entity.
 */
public class LanguageCodeDTO implements Serializable {
    
    private Long id;

    private Language language;

    @NotNull
    private String code;

    
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LanguageCodeDTO languageCodeDTO = (LanguageCodeDTO) o;
        if (languageCodeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), languageCodeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LanguageCodeDTO{" +
            "id=" + getId() +
            ", language='" + getLanguage() + "'" +
            ", code='" + getCode() + "'" +
            "}";
    }
}
