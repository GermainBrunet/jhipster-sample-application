package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.LanguageValueDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.LanguageValue}.
 */
public interface LanguageValueService {

    /**
     * Save a languageValue.
     *
     * @param languageValueDTO the entity to save.
     * @return the persisted entity.
     */
    LanguageValueDTO save(LanguageValueDTO languageValueDTO);

    /**
     * Get all the languageValues.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LanguageValueDTO> findAll(Pageable pageable);

    /**
     * Get the "id" languageValue.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LanguageValueDTO> findOne(Long id);

    /**
     * Delete the "id" languageValue.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
