package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.LanguageValue;
import com.mycompany.myapp.repository.LanguageValueRepository;
import com.mycompany.myapp.service.LanguageValueService;
import com.mycompany.myapp.service.dto.LanguageValueDTO;
import com.mycompany.myapp.service.mapper.LanguageValueMapper;

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

/**
 * Integration tests for the {@link LanguageValueResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class LanguageValueResourceIT {

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    @Autowired
    private LanguageValueRepository languageValueRepository;

    @Autowired
    private LanguageValueMapper languageValueMapper;

    @Autowired
    private LanguageValueService languageValueService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLanguageValueMockMvc;

    private LanguageValue languageValue;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LanguageValue createEntity(EntityManager em) {
        LanguageValue languageValue = new LanguageValue()
            .value(DEFAULT_VALUE);
        return languageValue;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LanguageValue createUpdatedEntity(EntityManager em) {
        LanguageValue languageValue = new LanguageValue()
            .value(UPDATED_VALUE);
        return languageValue;
    }

    @BeforeEach
    public void initTest() {
        languageValue = createEntity(em);
    }

    @Test
    @Transactional
    public void createLanguageValue() throws Exception {
        int databaseSizeBeforeCreate = languageValueRepository.findAll().size();

        // Create the LanguageValue
        LanguageValueDTO languageValueDTO = languageValueMapper.toDto(languageValue);
        restLanguageValueMockMvc.perform(post("/api/language-values")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(languageValueDTO)))
            .andExpect(status().isCreated());

        // Validate the LanguageValue in the database
        List<LanguageValue> languageValueList = languageValueRepository.findAll();
        assertThat(languageValueList).hasSize(databaseSizeBeforeCreate + 1);
        LanguageValue testLanguageValue = languageValueList.get(languageValueList.size() - 1);
        assertThat(testLanguageValue.getValue()).isEqualTo(DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void createLanguageValueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = languageValueRepository.findAll().size();

        // Create the LanguageValue with an existing ID
        languageValue.setId(1L);
        LanguageValueDTO languageValueDTO = languageValueMapper.toDto(languageValue);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLanguageValueMockMvc.perform(post("/api/language-values")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(languageValueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LanguageValue in the database
        List<LanguageValue> languageValueList = languageValueRepository.findAll();
        assertThat(languageValueList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = languageValueRepository.findAll().size();
        // set the field null
        languageValue.setValue(null);

        // Create the LanguageValue, which fails.
        LanguageValueDTO languageValueDTO = languageValueMapper.toDto(languageValue);

        restLanguageValueMockMvc.perform(post("/api/language-values")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(languageValueDTO)))
            .andExpect(status().isBadRequest());

        List<LanguageValue> languageValueList = languageValueRepository.findAll();
        assertThat(languageValueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLanguageValues() throws Exception {
        // Initialize the database
        languageValueRepository.saveAndFlush(languageValue);

        // Get all the languageValueList
        restLanguageValueMockMvc.perform(get("/api/language-values?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(languageValue.getId().intValue())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)));
    }
    
    @Test
    @Transactional
    public void getLanguageValue() throws Exception {
        // Initialize the database
        languageValueRepository.saveAndFlush(languageValue);

        // Get the languageValue
        restLanguageValueMockMvc.perform(get("/api/language-values/{id}", languageValue.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(languageValue.getId().intValue()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE));
    }

    @Test
    @Transactional
    public void getNonExistingLanguageValue() throws Exception {
        // Get the languageValue
        restLanguageValueMockMvc.perform(get("/api/language-values/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLanguageValue() throws Exception {
        // Initialize the database
        languageValueRepository.saveAndFlush(languageValue);

        int databaseSizeBeforeUpdate = languageValueRepository.findAll().size();

        // Update the languageValue
        LanguageValue updatedLanguageValue = languageValueRepository.findById(languageValue.getId()).get();
        // Disconnect from session so that the updates on updatedLanguageValue are not directly saved in db
        em.detach(updatedLanguageValue);
        updatedLanguageValue
            .value(UPDATED_VALUE);
        LanguageValueDTO languageValueDTO = languageValueMapper.toDto(updatedLanguageValue);

        restLanguageValueMockMvc.perform(put("/api/language-values")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(languageValueDTO)))
            .andExpect(status().isOk());

        // Validate the LanguageValue in the database
        List<LanguageValue> languageValueList = languageValueRepository.findAll();
        assertThat(languageValueList).hasSize(databaseSizeBeforeUpdate);
        LanguageValue testLanguageValue = languageValueList.get(languageValueList.size() - 1);
        assertThat(testLanguageValue.getValue()).isEqualTo(UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingLanguageValue() throws Exception {
        int databaseSizeBeforeUpdate = languageValueRepository.findAll().size();

        // Create the LanguageValue
        LanguageValueDTO languageValueDTO = languageValueMapper.toDto(languageValue);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLanguageValueMockMvc.perform(put("/api/language-values")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(languageValueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LanguageValue in the database
        List<LanguageValue> languageValueList = languageValueRepository.findAll();
        assertThat(languageValueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLanguageValue() throws Exception {
        // Initialize the database
        languageValueRepository.saveAndFlush(languageValue);

        int databaseSizeBeforeDelete = languageValueRepository.findAll().size();

        // Delete the languageValue
        restLanguageValueMockMvc.perform(delete("/api/language-values/{id}", languageValue.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LanguageValue> languageValueList = languageValueRepository.findAll();
        assertThat(languageValueList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
