package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

import com.mycompany.myapp.domain.enumeration.Language;

/**
 * A LanguageCode.
 */
@Entity
@Table(name = "language_code")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LanguageCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "language")
    private Language language;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @OneToMany(mappedBy = "code")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<LanguageValue> languageValues = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Language getLanguage() {
        return language;
    }

    public LanguageCode language(Language language) {
        this.language = language;
        return this;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getCode() {
        return code;
    }

    public LanguageCode code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<LanguageValue> getLanguageValues() {
        return languageValues;
    }

    public LanguageCode languageValues(Set<LanguageValue> languageValues) {
        this.languageValues = languageValues;
        return this;
    }

    public LanguageCode addLanguageValue(LanguageValue languageValue) {
        this.languageValues.add(languageValue);
        languageValue.setCode(this);
        return this;
    }

    public LanguageCode removeLanguageValue(LanguageValue languageValue) {
        this.languageValues.remove(languageValue);
        languageValue.setCode(null);
        return this;
    }

    public void setLanguageValues(Set<LanguageValue> languageValues) {
        this.languageValues = languageValues;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LanguageCode)) {
            return false;
        }
        return id != null && id.equals(((LanguageCode) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "LanguageCode{" +
            "id=" + getId() +
            ", language='" + getLanguage() + "'" +
            ", code='" + getCode() + "'" +
            "}";
    }
}
