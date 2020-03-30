package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.ExerciseGroupService;
import com.mycompany.myapp.domain.ExerciseGroup;
import com.mycompany.myapp.repository.ExerciseGroupRepository;
import com.mycompany.myapp.service.dto.ExerciseGroupDTO;
import com.mycompany.myapp.service.mapper.ExerciseGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ExerciseGroup}.
 */
@Service
@Transactional
public class ExerciseGroupServiceImpl implements ExerciseGroupService {

    private final Logger log = LoggerFactory.getLogger(ExerciseGroupServiceImpl.class);

    private final ExerciseGroupRepository exerciseGroupRepository;

    private final ExerciseGroupMapper exerciseGroupMapper;

    public ExerciseGroupServiceImpl(ExerciseGroupRepository exerciseGroupRepository, ExerciseGroupMapper exerciseGroupMapper) {
        this.exerciseGroupRepository = exerciseGroupRepository;
        this.exerciseGroupMapper = exerciseGroupMapper;
    }

    /**
     * Save a exerciseGroup.
     *
     * @param exerciseGroupDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ExerciseGroupDTO save(ExerciseGroupDTO exerciseGroupDTO) {
        log.debug("Request to save ExerciseGroup : {}", exerciseGroupDTO);
        ExerciseGroup exerciseGroup = exerciseGroupMapper.toEntity(exerciseGroupDTO);
        exerciseGroup = exerciseGroupRepository.save(exerciseGroup);
        return exerciseGroupMapper.toDto(exerciseGroup);
    }

    /**
     * Get all the exerciseGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ExerciseGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ExerciseGroups");
        return exerciseGroupRepository.findAll(pageable)
            .map(exerciseGroupMapper::toDto);
    }

    /**
     * Get all the exerciseGroups with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<ExerciseGroupDTO> findAllWithEagerRelationships(Pageable pageable) {
        return exerciseGroupRepository.findAllWithEagerRelationships(pageable).map(exerciseGroupMapper::toDto);
    }

    /**
     * Get one exerciseGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ExerciseGroupDTO> findOne(Long id) {
        log.debug("Request to get ExerciseGroup : {}", id);
        return exerciseGroupRepository.findOneWithEagerRelationships(id)
            .map(exerciseGroupMapper::toDto);
    }

    /**
     * Delete the exerciseGroup by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ExerciseGroup : {}", id);
        exerciseGroupRepository.deleteById(id);
    }
}
