package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ExerciseGroupDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.ExerciseGroup}.
 */
public interface ExerciseGroupService {

    /**
     * Save a exerciseGroup.
     *
     * @param exerciseGroupDTO the entity to save.
     * @return the persisted entity.
     */
    ExerciseGroupDTO save(ExerciseGroupDTO exerciseGroupDTO);

    /**
     * Get all the exerciseGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ExerciseGroupDTO> findAll(Pageable pageable);

    /**
     * Get all the exerciseGroups with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<ExerciseGroupDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" exerciseGroup.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ExerciseGroupDTO> findOne(Long id);

    /**
     * Delete the "id" exerciseGroup.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
