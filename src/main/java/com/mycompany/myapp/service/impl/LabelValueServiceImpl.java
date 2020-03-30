package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.LabelValueService;
import com.mycompany.myapp.domain.LabelValue;
import com.mycompany.myapp.repository.LabelValueRepository;
import com.mycompany.myapp.service.dto.LabelValueDTO;
import com.mycompany.myapp.service.mapper.LabelValueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link LabelValue}.
 */
@Service
@Transactional
public class LabelValueServiceImpl implements LabelValueService {

    private final Logger log = LoggerFactory.getLogger(LabelValueServiceImpl.class);

    private final LabelValueRepository labelValueRepository;

    private final LabelValueMapper labelValueMapper;

    public LabelValueServiceImpl(LabelValueRepository labelValueRepository, LabelValueMapper labelValueMapper) {
        this.labelValueRepository = labelValueRepository;
        this.labelValueMapper = labelValueMapper;
    }

    /**
     * Save a labelValue.
     *
     * @param labelValueDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public LabelValueDTO save(LabelValueDTO labelValueDTO) {
        log.debug("Request to save LabelValue : {}", labelValueDTO);
        LabelValue labelValue = labelValueMapper.toEntity(labelValueDTO);
        labelValue = labelValueRepository.save(labelValue);
        return labelValueMapper.toDto(labelValue);
    }

    /**
     * Get all the labelValues.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LabelValueDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LabelValues");
        return labelValueRepository.findAll(pageable)
            .map(labelValueMapper::toDto);
    }

    /**
     * Get one labelValue by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LabelValueDTO> findOne(Long id) {
        log.debug("Request to get LabelValue : {}", id);
        return labelValueRepository.findById(id)
            .map(labelValueMapper::toDto);
    }

    /**
     * Delete the labelValue by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LabelValue : {}", id);
        labelValueRepository.deleteById(id);
    }
}
