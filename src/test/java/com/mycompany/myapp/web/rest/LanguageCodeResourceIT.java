package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.LanguageCode;
import com.mycompany.myapp.repository.LanguageCodeRepository;
import com.mycompany.myapp.service.LanguageCodeService;
import com.mycompany.myapp.service.dto.LanguageCodeDTO;
import com.mycompany.myapp.service.mapper.LanguageCodeMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.domain.enumeration.Language;
/**
 * Integration tests for the {@link LanguageCodeResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class LanguageCodeResourceIT {

    private static final Language DEFAULT_LANGUAGE = Language.FRENCH;
    private static final Language UPDATED_LANGUAGE = Language.ENGLISH;

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    @Autowired
    private LanguageCodeRepository languageCodeRepository;

    @Autowired
    private LanguageCodeMapper languageCodeMapper;

    @Autowired
    private LanguageCodeService languageCodeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLanguageCodeMockMvc;

    private LanguageCode languageCode;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LanguageCode createEntity(EntityManager em) {
        LanguageCode languageCode = new LanguageCode()
            .language(DEFAULT_LANGUAGE)
            .code(DEFAULT_CODE);
        return languageCode;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LanguageCode createUpdatedEntity(EntityManager em) {
        LanguageCode languageCode = new LanguageCode()
            .language(UPDATED_LANGUAGE)
            .code(UPDATED_CODE);
        return languageCode;
    }

    @BeforeEach
    public void initTest() {
        languageCode = createEntity(em);
    }

    @Test
    @Transactional
    public void createLanguageCode() throws Exception {
        int databaseSizeBeforeCreate = languageCodeRepository.findAll().size();

        // Create the LanguageCode
        LanguageCodeDTO languageCodeDTO = languageCodeMapper.toDto(languageCode);
        restLanguageCodeMockMvc.perform(post("/api/language-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(languageCodeDTO)))
            .andExpect(status().isCreated());

        // Validate the LanguageCode in the database
        List<LanguageCode> languageCodeList = languageCodeRepository.findAll();
        assertThat(languageCodeList).hasSize(databaseSizeBeforeCreate + 1);
        LanguageCode testLanguageCode = languageCodeList.get(languageCodeList.size() - 1);
        assertThat(testLanguageCode.getLanguage()).isEqualTo(DEFAULT_LANGUAGE);
        assertThat(testLanguageCode.getCode()).isEqualTo(DEFAULT_CODE);
    }

    @Test
    @Transactional
    public void createLanguageCodeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = languageCodeRepository.findAll().size();

        // Create the LanguageCode with an existing ID
        languageCode.setId(1L);
        LanguageCodeDTO languageCodeDTO = languageCodeMapper.toDto(languageCode);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLanguageCodeMockMvc.perform(post("/api/language-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(languageCodeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LanguageCode in the database
        List<LanguageCode> languageCodeList = languageCodeRepository.findAll();
        assertThat(languageCodeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = languageCodeRepository.findAll().size();
        // set the field null
        languageCode.setCode(null);

        // Create the LanguageCode, which fails.
        LanguageCodeDTO languageCodeDTO = languageCodeMapper.toDto(languageCode);

        restLanguageCodeMockMvc.perform(post("/api/language-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(languageCodeDTO)))
            .andExpect(status().isBadRequest());

        List<LanguageCode> languageCodeList = languageCodeRepository.findAll();
        assertThat(languageCodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLanguageCodes() throws Exception {
        // Initialize the database
        languageCodeRepository.saveAndFlush(languageCode);

        // Get all the languageCodeList
        restLanguageCodeMockMvc.perform(get("/api/language-codes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(languageCode.getId().intValue())))
            .andExpect(jsonPath("$.[*].language").value(hasItem(DEFAULT_LANGUAGE.toString())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)));
    }
    
    @Test
    @Transactional
    public void getLanguageCode() throws Exception {
        // Initialize the database
        languageCodeRepository.saveAndFlush(languageCode);

        // Get the languageCode
        restLanguageCodeMockMvc.perform(get("/api/language-codes/{id}", languageCode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(languageCode.getId().intValue()))
            .andExpect(jsonPath("$.language").value(DEFAULT_LANGUAGE.toString()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE));
    }

    @Test
    @Transactional
    public void getNonExistingLanguageCode() throws Exception {
        // Get the languageCode
        restLanguageCodeMockMvc.perform(get("/api/language-codes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLanguageCode() throws Exception {
        // Initialize the database
        languageCodeRepository.saveAndFlush(languageCode);

        int databaseSizeBeforeUpdate = languageCodeRepository.findAll().size();

        // Update the languageCode
        LanguageCode updatedLanguageCode = languageCodeRepository.findById(languageCode.getId()).get();
        // Disconnect from session so that the updates on updatedLanguageCode are not directly saved in db
        em.detach(updatedLanguageCode);
        updatedLanguageCode
            .language(UPDATED_LANGUAGE)
            .code(UPDATED_CODE);
        LanguageCodeDTO languageCodeDTO = languageCodeMapper.toDto(updatedLanguageCode);

        restLanguageCodeMockMvc.perform(put("/api/language-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(languageCodeDTO)))
            .andExpect(status().isOk());

        // Validate the LanguageCode in the database
        List<LanguageCode> languageCodeList = languageCodeRepository.findAll();
        assertThat(languageCodeList).hasSize(databaseSizeBeforeUpdate);
        LanguageCode testLanguageCode = languageCodeList.get(languageCodeList.size() - 1);
        assertThat(testLanguageCode.getLanguage()).isEqualTo(UPDATED_LANGUAGE);
        assertThat(testLanguageCode.getCode()).isEqualTo(UPDATED_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingLanguageCode() throws Exception {
        int databaseSizeBeforeUpdate = languageCodeRepository.findAll().size();

        // Create the LanguageCode
        LanguageCodeDTO languageCodeDTO = languageCodeMapper.toDto(languageCode);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLanguageCodeMockMvc.perform(put("/api/language-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(languageCodeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LanguageCode in the database
        List<LanguageCode> languageCodeList = languageCodeRepository.findAll();
        assertThat(languageCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLanguageCode() throws Exception {
        // Initialize the database
        languageCodeRepository.saveAndFlush(languageCode);

        int databaseSizeBeforeDelete = languageCodeRepository.findAll().size();

        // Delete the languageCode
        restLanguageCodeMockMvc.perform(delete("/api/language-codes/{id}", languageCode.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LanguageCode> languageCodeList = languageCodeRepository.findAll();
        assertThat(languageCodeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
