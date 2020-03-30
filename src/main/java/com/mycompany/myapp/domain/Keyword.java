package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

/**
 * A Keyword.
 */
@Entity
@Table(name = "keyword")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Keyword implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Type(type = "uuid-char")
    @Column(name = "guid", length = 36, nullable = false)
    private UUID guid;

    @OneToOne
    @JoinColumn(unique = true)
    private Label label;

    @ManyToMany(mappedBy = "keywords")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<ExerciseGroup> exerciseGroups = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getGuid() {
        return guid;
    }

    public Keyword guid(UUID guid) {
        this.guid = guid;
        return this;
    }

    public void setGuid(UUID guid) {
        this.guid = guid;
    }

    public Label getLabel() {
        return label;
    }

    public Keyword label(Label label) {
        this.label = label;
        return this;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Set<ExerciseGroup> getExerciseGroups() {
        return exerciseGroups;
    }

    public Keyword exerciseGroups(Set<ExerciseGroup> exerciseGroups) {
        this.exerciseGroups = exerciseGroups;
        return this;
    }

    public Keyword addExerciseGroup(ExerciseGroup exerciseGroup) {
        this.exerciseGroups.add(exerciseGroup);
        exerciseGroup.getKeywords().add(this);
        return this;
    }

    public Keyword removeExerciseGroup(ExerciseGroup exerciseGroup) {
        this.exerciseGroups.remove(exerciseGroup);
        exerciseGroup.getKeywords().remove(this);
        return this;
    }

    public void setExerciseGroups(Set<ExerciseGroup> exerciseGroups) {
        this.exerciseGroups = exerciseGroups;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Keyword)) {
            return false;
        }
        return id != null && id.equals(((Keyword) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Keyword{" +
            "id=" + getId() +
            ", guid='" + getGuid() + "'" +
            "}";
    }
}
