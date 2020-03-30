package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.LanguageCodeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.LanguageCode}.
 */
public interface LanguageCodeService {

    /**
     * Save a languageCode.
     *
     * @param languageCodeDTO the entity to save.
     * @return the persisted entity.
     */
    LanguageCodeDTO save(LanguageCodeDTO languageCodeDTO);

    /**
     * Get all the languageCodes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LanguageCodeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" languageCode.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LanguageCodeDTO> findOne(Long id);

    /**
     * Delete the "id" languageCode.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
