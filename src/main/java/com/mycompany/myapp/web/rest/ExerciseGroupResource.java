package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.ExerciseGroupService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.ExerciseGroupDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.ExerciseGroup}.
 */
@RestController
@RequestMapping("/api")
public class ExerciseGroupResource {

    private final Logger log = LoggerFactory.getLogger(ExerciseGroupResource.class);

    private static final String ENTITY_NAME = "exerciseGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ExerciseGroupService exerciseGroupService;

    public ExerciseGroupResource(ExerciseGroupService exerciseGroupService) {
        this.exerciseGroupService = exerciseGroupService;
    }

    /**
     * {@code POST  /exercise-groups} : Create a new exerciseGroup.
     *
     * @param exerciseGroupDTO the exerciseGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new exerciseGroupDTO, or with status {@code 400 (Bad Request)} if the exerciseGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/exercise-groups")
    public ResponseEntity<ExerciseGroupDTO> createExerciseGroup(@Valid @RequestBody ExerciseGroupDTO exerciseGroupDTO) throws URISyntaxException {
        log.debug("REST request to save ExerciseGroup : {}", exerciseGroupDTO);
        if (exerciseGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new exerciseGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ExerciseGroupDTO result = exerciseGroupService.save(exerciseGroupDTO);
        return ResponseEntity.created(new URI("/api/exercise-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /exercise-groups} : Updates an existing exerciseGroup.
     *
     * @param exerciseGroupDTO the exerciseGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated exerciseGroupDTO,
     * or with status {@code 400 (Bad Request)} if the exerciseGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the exerciseGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/exercise-groups")
    public ResponseEntity<ExerciseGroupDTO> updateExerciseGroup(@Valid @RequestBody ExerciseGroupDTO exerciseGroupDTO) throws URISyntaxException {
        log.debug("REST request to update ExerciseGroup : {}", exerciseGroupDTO);
        if (exerciseGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ExerciseGroupDTO result = exerciseGroupService.save(exerciseGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, exerciseGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /exercise-groups} : get all the exerciseGroups.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of exerciseGroups in body.
     */
    @GetMapping("/exercise-groups")
    public ResponseEntity<List<ExerciseGroupDTO>> getAllExerciseGroups(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of ExerciseGroups");
        Page<ExerciseGroupDTO> page;
        if (eagerload) {
            page = exerciseGroupService.findAllWithEagerRelationships(pageable);
        } else {
            page = exerciseGroupService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /exercise-groups/:id} : get the "id" exerciseGroup.
     *
     * @param id the id of the exerciseGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the exerciseGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/exercise-groups/{id}")
    public ResponseEntity<ExerciseGroupDTO> getExerciseGroup(@PathVariable Long id) {
        log.debug("REST request to get ExerciseGroup : {}", id);
        Optional<ExerciseGroupDTO> exerciseGroupDTO = exerciseGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(exerciseGroupDTO);
    }

    /**
     * {@code DELETE  /exercise-groups/:id} : delete the "id" exerciseGroup.
     *
     * @param id the id of the exerciseGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/exercise-groups/{id}")
    public ResponseEntity<Void> deleteExerciseGroup(@PathVariable Long id) {
        log.debug("REST request to delete ExerciseGroup : {}", id);
        exerciseGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
