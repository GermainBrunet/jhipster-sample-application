package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.LanguageValueService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.LanguageValueDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.LanguageValue}.
 */
@RestController
@RequestMapping("/api")
public class LanguageValueResource {

    private final Logger log = LoggerFactory.getLogger(LanguageValueResource.class);

    private static final String ENTITY_NAME = "languageValue";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LanguageValueService languageValueService;

    public LanguageValueResource(LanguageValueService languageValueService) {
        this.languageValueService = languageValueService;
    }

    /**
     * {@code POST  /language-values} : Create a new languageValue.
     *
     * @param languageValueDTO the languageValueDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new languageValueDTO, or with status {@code 400 (Bad Request)} if the languageValue has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/language-values")
    public ResponseEntity<LanguageValueDTO> createLanguageValue(@Valid @RequestBody LanguageValueDTO languageValueDTO) throws URISyntaxException {
        log.debug("REST request to save LanguageValue : {}", languageValueDTO);
        if (languageValueDTO.getId() != null) {
            throw new BadRequestAlertException("A new languageValue cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LanguageValueDTO result = languageValueService.save(languageValueDTO);
        return ResponseEntity.created(new URI("/api/language-values/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /language-values} : Updates an existing languageValue.
     *
     * @param languageValueDTO the languageValueDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated languageValueDTO,
     * or with status {@code 400 (Bad Request)} if the languageValueDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the languageValueDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/language-values")
    public ResponseEntity<LanguageValueDTO> updateLanguageValue(@Valid @RequestBody LanguageValueDTO languageValueDTO) throws URISyntaxException {
        log.debug("REST request to update LanguageValue : {}", languageValueDTO);
        if (languageValueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LanguageValueDTO result = languageValueService.save(languageValueDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, languageValueDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /language-values} : get all the languageValues.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of languageValues in body.
     */
    @GetMapping("/language-values")
    public ResponseEntity<List<LanguageValueDTO>> getAllLanguageValues(Pageable pageable) {
        log.debug("REST request to get a page of LanguageValues");
        Page<LanguageValueDTO> page = languageValueService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /language-values/:id} : get the "id" languageValue.
     *
     * @param id the id of the languageValueDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the languageValueDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/language-values/{id}")
    public ResponseEntity<LanguageValueDTO> getLanguageValue(@PathVariable Long id) {
        log.debug("REST request to get LanguageValue : {}", id);
        Optional<LanguageValueDTO> languageValueDTO = languageValueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(languageValueDTO);
    }

    /**
     * {@code DELETE  /language-values/:id} : delete the "id" languageValue.
     *
     * @param id the id of the languageValueDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/language-values/{id}")
    public ResponseEntity<Void> deleteLanguageValue(@PathVariable Long id) {
        log.debug("REST request to delete LanguageValue : {}", id);
        languageValueService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
