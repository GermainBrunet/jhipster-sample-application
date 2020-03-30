package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

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
    @Column(name = "guid", nullable = false)
    private String guid;

    @OneToOne
    @JoinColumn(unique = true)
    private Label name;

    @OneToOne
    @JoinColumn(unique = true)
    private Label description;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public Keyword guid(String guid) {
        this.guid = guid;
        return this;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Label getName() {
        return name;
    }

    public Keyword name(Label label) {
        this.name = label;
        return this;
    }

    public void setName(Label label) {
        this.name = label;
    }

    public Label getDescription() {
        return description;
    }

    public Keyword description(Label label) {
        this.description = label;
        return this;
    }

    public void setDescription(Label label) {
        this.description = label;
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
