package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.LabelValueService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.LabelValueDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.LabelValue}.
 */
@RestController
@RequestMapping("/api")
public class LabelValueResource {

    private final Logger log = LoggerFactory.getLogger(LabelValueResource.class);

    private static final String ENTITY_NAME = "labelValue";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LabelValueService labelValueService;

    public LabelValueResource(LabelValueService labelValueService) {
        this.labelValueService = labelValueService;
    }

    /**
     * {@code POST  /label-values} : Create a new labelValue.
     *
     * @param labelValueDTO the labelValueDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new labelValueDTO, or with status {@code 400 (Bad Request)} if the labelValue has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/label-values")
    public ResponseEntity<LabelValueDTO> createLabelValue(@Valid @RequestBody LabelValueDTO labelValueDTO) throws URISyntaxException {
        log.debug("REST request to save LabelValue : {}", labelValueDTO);
        if (labelValueDTO.getId() != null) {
            throw new BadRequestAlertException("A new labelValue cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LabelValueDTO result = labelValueService.save(labelValueDTO);
        return ResponseEntity.created(new URI("/api/label-values/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /label-values} : Updates an existing labelValue.
     *
     * @param labelValueDTO the labelValueDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated labelValueDTO,
     * or with status {@code 400 (Bad Request)} if the labelValueDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the labelValueDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/label-values")
    public ResponseEntity<LabelValueDTO> updateLabelValue(@Valid @RequestBody LabelValueDTO labelValueDTO) throws URISyntaxException {
        log.debug("REST request to update LabelValue : {}", labelValueDTO);
        if (labelValueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LabelValueDTO result = labelValueService.save(labelValueDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, labelValueDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /label-values} : get all the labelValues.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of labelValues in body.
     */
    @GetMapping("/label-values")
    public ResponseEntity<List<LabelValueDTO>> getAllLabelValues(Pageable pageable) {
        log.debug("REST request to get a page of LabelValues");
        Page<LabelValueDTO> page = labelValueService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /label-values/:id} : get the "id" labelValue.
     *
     * @param id the id of the labelValueDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the labelValueDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/label-values/{id}")
    public ResponseEntity<LabelValueDTO> getLabelValue(@PathVariable Long id) {
        log.debug("REST request to get LabelValue : {}", id);
        Optional<LabelValueDTO> labelValueDTO = labelValueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(labelValueDTO);
    }

    /**
     * {@code DELETE  /label-values/:id} : delete the "id" labelValue.
     *
     * @param id the id of the labelValueDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/label-values/{id}")
    public ResponseEntity<Void> deleteLabelValue(@PathVariable Long id) {
        log.debug("REST request to delete LabelValue : {}", id);
        labelValueService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
