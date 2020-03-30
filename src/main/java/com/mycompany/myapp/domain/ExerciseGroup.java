package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.mycompany.myapp.domain.enumeration.Language;

/**
 * A ExerciseGroup.
 */
@Entity
@Table(name = "exercise_group")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ExerciseGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "language")
    private Language language;

    @NotNull
    @Type(type = "uuid-char")
    @Column(name = "guid", length = 36, nullable = false)
    private UUID guid;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "author")
    private String author;

    @OneToOne
    @JoinColumn(unique = true)
    private Label name;

    @OneToOne
    @JoinColumn(unique = true)
    private Label description;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "exercise_group_keyword",
               joinColumns = @JoinColumn(name = "exercise_group_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "keyword_id", referencedColumnName = "id"))
    private Set<Keyword> keywords = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("exerciseGroups")
    private Level level;

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

    public ExerciseGroup language(Language language) {
        this.language = language;
        return this;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public UUID getGuid() {
        return guid;
    }

    public ExerciseGroup guid(UUID guid) {
        this.guid = guid;
        return this;
    }

    public void setGuid(UUID guid) {
        this.guid = guid;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public ExerciseGroup sortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getAuthor() {
        return author;
    }

    public ExerciseGroup author(String author) {
        this.author = author;
        return this;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Label getName() {
        return name;
    }

    public ExerciseGroup name(Label label) {
        this.name = label;
        return this;
    }

    public void setName(Label label) {
        this.name = label;
    }

    public Label getDescription() {
        return description;
    }

    public ExerciseGroup description(Label label) {
        this.description = label;
        return this;
    }

    public void setDescription(Label label) {
        this.description = label;
    }

    public Set<Keyword> getKeywords() {
        return keywords;
    }

    public ExerciseGroup keywords(Set<Keyword> keywords) {
        this.keywords = keywords;
        return this;
    }

    public ExerciseGroup addKeyword(Keyword keyword) {
        this.keywords.add(keyword);
        keyword.getExerciseGroups().add(this);
        return this;
    }

    public ExerciseGroup removeKeyword(Keyword keyword) {
        this.keywords.remove(keyword);
        keyword.getExerciseGroups().remove(this);
        return this;
    }

    public void setKeywords(Set<Keyword> keywords) {
        this.keywords = keywords;
    }

    public Level getLevel() {
        return level;
    }

    public ExerciseGroup level(Level level) {
        this.level = level;
        return this;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExerciseGroup)) {
            return false;
        }
        return id != null && id.equals(((ExerciseGroup) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ExerciseGroup{" +
            "id=" + getId() +
            ", language='" + getLanguage() + "'" +
            ", guid='" + getGuid() + "'" +
            ", sortOrder=" + getSortOrder() +
            ", author='" + getAuthor() + "'" +
            "}";
    }
}
