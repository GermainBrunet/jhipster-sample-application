package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * A Label.
 */
@Entity
@Table(name = "label")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Label implements Serializable {

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
    private Set<LabelValue> languageValues = new HashSet<>();

    @OneToOne(mappedBy = "name")
    @JsonIgnore
    private Keyword guid;

    @OneToOne(mappedBy = "description")
    @JsonIgnore
    private Keyword guid;

    @OneToOne(mappedBy = "name")
    @JsonIgnore
    private Level guid;

    @OneToOne(mappedBy = "description")
    @JsonIgnore
    private Level guid;

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

    public Label language(Language language) {
        this.language = language;
        return this;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getCode() {
        return code;
    }

    public Label code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<LabelValue> getLanguageValues() {
        return languageValues;
    }

    public Label languageValues(Set<LabelValue> labelValues) {
        this.languageValues = labelValues;
        return this;
    }

    public Label addLanguageValue(LabelValue labelValue) {
        this.languageValues.add(labelValue);
        labelValue.setCode(this);
        return this;
    }

    public Label removeLanguageValue(LabelValue labelValue) {
        this.languageValues.remove(labelValue);
        labelValue.setCode(null);
        return this;
    }

    public void setLanguageValues(Set<LabelValue> labelValues) {
        this.languageValues = labelValues;
    }

    public Keyword getGuid() {
        return guid;
    }

    public Label guid(Keyword keyword) {
        this.guid = keyword;
        return this;
    }

    public void setGuid(Keyword keyword) {
        this.guid = keyword;
    }

    public Keyword getGuid() {
        return guid;
    }

    public Label guid(Keyword keyword) {
        this.guid = keyword;
        return this;
    }

    public void setGuid(Keyword keyword) {
        this.guid = keyword;
    }

    public Level getGuid() {
        return guid;
    }

    public Label guid(Level level) {
        this.guid = level;
        return this;
    }

    public void setGuid(Level level) {
        this.guid = level;
    }

    public Level getGuid() {
        return guid;
    }

    public Label guid(Level level) {
        this.guid = level;
        return this;
    }

    public void setGuid(Level level) {
        this.guid = level;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Label)) {
            return false;
        }
        return id != null && id.equals(((Label) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Label{" +
            "id=" + getId() +
            ", language='" + getLanguage() + "'" +
            ", code='" + getCode() + "'" +
            "}";
    }
}
