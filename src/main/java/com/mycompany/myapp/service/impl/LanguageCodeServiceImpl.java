package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.LanguageCodeService;
import com.mycompany.myapp.domain.LanguageCode;
import com.mycompany.myapp.repository.LanguageCodeRepository;
import com.mycompany.myapp.service.dto.LanguageCodeDTO;
import com.mycompany.myapp.service.mapper.LanguageCodeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link LanguageCode}.
 */
@Service
@Transactional
public class LanguageCodeServiceImpl implements LanguageCodeService {

    private final Logger log = LoggerFactory.getLogger(LanguageCodeServiceImpl.class);

    private final LanguageCodeRepository languageCodeRepository;

    private final LanguageCodeMapper languageCodeMapper;

    public LanguageCodeServiceImpl(LanguageCodeRepository languageCodeRepository, LanguageCodeMapper languageCodeMapper) {
        this.languageCodeRepository = languageCodeRepository;
        this.languageCodeMapper = languageCodeMapper;
    }

    /**
     * Save a languageCode.
     *
     * @param languageCodeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public LanguageCodeDTO save(LanguageCodeDTO languageCodeDTO) {
        log.debug("Request to save LanguageCode : {}", languageCodeDTO);
        LanguageCode languageCode = languageCodeMapper.toEntity(languageCodeDTO);
        languageCode = languageCodeRepository.save(languageCode);
        return languageCodeMapper.toDto(languageCode);
    }

    /**
     * Get all the languageCodes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LanguageCodeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LanguageCodes");
        return languageCodeRepository.findAll(pageable)
            .map(languageCodeMapper::toDto);
    }

    /**
     * Get one languageCode by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LanguageCodeDTO> findOne(Long id) {
        log.debug("Request to get LanguageCode : {}", id);
        return languageCodeRepository.findById(id)
            .map(languageCodeMapper::toDto);
    }

    /**
     * Delete the languageCode by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LanguageCode : {}", id);
        languageCodeRepository.deleteById(id);
    }
}
