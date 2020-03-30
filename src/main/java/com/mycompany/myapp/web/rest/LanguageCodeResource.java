package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.LanguageCodeService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.LanguageCodeDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.LanguageCode}.
 */
@RestController
@RequestMapping("/api")
public class LanguageCodeResource {

    private final Logger log = LoggerFactory.getLogger(LanguageCodeResource.class);

    private static final String ENTITY_NAME = "languageCode";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LanguageCodeService languageCodeService;

    public LanguageCodeResource(LanguageCodeService languageCodeService) {
        this.languageCodeService = languageCodeService;
    }

    /**
     * {@code POST  /language-codes} : Create a new languageCode.
     *
     * @param languageCodeDTO the languageCodeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new languageCodeDTO, or with status {@code 400 (Bad Request)} if the languageCode has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/language-codes")
    public ResponseEntity<LanguageCodeDTO> createLanguageCode(@Valid @RequestBody LanguageCodeDTO languageCodeDTO) throws URISyntaxException {
        log.debug("REST request to save LanguageCode : {}", languageCodeDTO);
        if (languageCodeDTO.getId() != null) {
            throw new BadRequestAlertException("A new languageCode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LanguageCodeDTO result = languageCodeService.save(languageCodeDTO);
        return ResponseEntity.created(new URI("/api/language-codes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /language-codes} : Updates an existing languageCode.
     *
     * @param languageCodeDTO the languageCodeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated languageCodeDTO,
     * or with status {@code 400 (Bad Request)} if the languageCodeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the languageCodeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/language-codes")
    public ResponseEntity<LanguageCodeDTO> updateLanguageCode(@Valid @RequestBody LanguageCodeDTO languageCodeDTO) throws URISyntaxException {
        log.debug("REST request to update LanguageCode : {}", languageCodeDTO);
        if (languageCodeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LanguageCodeDTO result = languageCodeService.save(languageCodeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, languageCodeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /language-codes} : get all the languageCodes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of languageCodes in body.
     */
    @GetMapping("/language-codes")
    public ResponseEntity<List<LanguageCodeDTO>> getAllLanguageCodes(Pageable pageable) {
        log.debug("REST request to get a page of LanguageCodes");
        Page<LanguageCodeDTO> page = languageCodeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /language-codes/:id} : get the "id" languageCode.
     *
     * @param id the id of the languageCodeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the languageCodeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/language-codes/{id}")
    public ResponseEntity<LanguageCodeDTO> getLanguageCode(@PathVariable Long id) {
        log.debug("REST request to get LanguageCode : {}", id);
        Optional<LanguageCodeDTO> languageCodeDTO = languageCodeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(languageCodeDTO);
    }

    /**
     * {@code DELETE  /language-codes/:id} : delete the "id" languageCode.
     *
     * @param id the id of the languageCodeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/language-codes/{id}")
    public ResponseEntity<Void> deleteLanguageCode(@PathVariable Long id) {
        log.debug("REST request to delete LanguageCode : {}", id);
        languageCodeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
