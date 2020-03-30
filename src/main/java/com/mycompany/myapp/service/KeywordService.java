package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.KeywordDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Keyword}.
 */
public interface KeywordService {

    /**
     * Save a keyword.
     *
     * @param keywordDTO the entity to save.
     * @return the persisted entity.
     */
    KeywordDTO save(KeywordDTO keywordDTO);

    /**
     * Get all the keywords.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<KeywordDTO> findAll(Pageable pageable);

    /**
     * Get the "id" keyword.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<KeywordDTO> findOne(Long id);

    /**
     * Delete the "id" keyword.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
