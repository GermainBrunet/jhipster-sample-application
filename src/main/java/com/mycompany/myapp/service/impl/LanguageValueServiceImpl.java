package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.LanguageValueService;
import com.mycompany.myapp.domain.LanguageValue;
import com.mycompany.myapp.repository.LanguageValueRepository;
import com.mycompany.myapp.service.dto.LanguageValueDTO;
import com.mycompany.myapp.service.mapper.LanguageValueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link LanguageValue}.
 */
@Service
@Transactional
public class LanguageValueServiceImpl implements LanguageValueService {

    private final Logger log = LoggerFactory.getLogger(LanguageValueServiceImpl.class);

    private final LanguageValueRepository languageValueRepository;

    private final LanguageValueMapper languageValueMapper;

    public LanguageValueServiceImpl(LanguageValueRepository languageValueRepository, LanguageValueMapper languageValueMapper) {
        this.languageValueRepository = languageValueRepository;
        this.languageValueMapper = languageValueMapper;
    }

    /**
     * Save a languageValue.
     *
     * @param languageValueDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public LanguageValueDTO save(LanguageValueDTO languageValueDTO) {
        log.debug("Request to save LanguageValue : {}", languageValueDTO);
        LanguageValue languageValue = languageValueMapper.toEntity(languageValueDTO);
        languageValue = languageValueRepository.save(languageValue);
        return languageValueMapper.toDto(languageValue);
    }

    /**
     * Get all the languageValues.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LanguageValueDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LanguageValues");
        return languageValueRepository.findAll(pageable)
            .map(languageValueMapper::toDto);
    }

    /**
     * Get one languageValue by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LanguageValueDTO> findOne(Long id) {
        log.debug("Request to get LanguageValue : {}", id);
        return languageValueRepository.findById(id)
            .map(languageValueMapper::toDto);
    }

    /**
     * Delete the languageValue by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LanguageValue : {}", id);
        languageValueRepository.deleteById(id);
    }
}
