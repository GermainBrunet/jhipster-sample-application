package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.LabelDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Label}.
 */
public interface LabelService {

    /**
     * Save a label.
     *
     * @param labelDTO the entity to save.
     * @return the persisted entity.
     */
    LabelDTO save(LabelDTO labelDTO);

    /**
     * Get all the labels.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LabelDTO> findAll(Pageable pageable);
    /**
     * Get all the LabelDTO where Guid is {@code null}.
     *
     * @return the list of entities.
     */
    List<LabelDTO> findAllWhereGuidIsNull();
    /**
     * Get all the LabelDTO where Guid is {@code null}.
     *
     * @return the list of entities.
     */
    List<LabelDTO> findAllWhereGuidIsNull();
    /**
     * Get all the LabelDTO where Guid is {@code null}.
     *
     * @return the list of entities.
     */
    List<LabelDTO> findAllWhereGuidIsNull();
    /**
     * Get all the LabelDTO where Guid is {@code null}.
     *
     * @return the list of entities.
     */
    List<LabelDTO> findAllWhereGuidIsNull();

    /**
     * Get the "id" label.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LabelDTO> findOne(Long id);

    /**
     * Delete the "id" label.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
