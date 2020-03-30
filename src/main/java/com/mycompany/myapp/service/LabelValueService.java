package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.LabelValueDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.LabelValue}.
 */
public interface LabelValueService {

    /**
     * Save a labelValue.
     *
     * @param labelValueDTO the entity to save.
     * @return the persisted entity.
     */
    LabelValueDTO save(LabelValueDTO labelValueDTO);

    /**
     * Get all the labelValues.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LabelValueDTO> findAll(Pageable pageable);

    /**
     * Get the "id" labelValue.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LabelValueDTO> findOne(Long id);

    /**
     * Delete the "id" labelValue.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
