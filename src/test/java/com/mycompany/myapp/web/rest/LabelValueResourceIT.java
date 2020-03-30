package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.LabelValue;
import com.mycompany.myapp.repository.LabelValueRepository;
import com.mycompany.myapp.service.LabelValueService;
import com.mycompany.myapp.service.dto.LabelValueDTO;
import com.mycompany.myapp.service.mapper.LabelValueMapper;

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
 * Integration tests for the {@link LabelValueResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class LabelValueResourceIT {

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    @Autowired
    private LabelValueRepository labelValueRepository;

    @Autowired
    private LabelValueMapper labelValueMapper;

    @Autowired
    private LabelValueService labelValueService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLabelValueMockMvc;

    private LabelValue labelValue;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LabelValue createEntity(EntityManager em) {
        LabelValue labelValue = new LabelValue()
            .value(DEFAULT_VALUE);
        return labelValue;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LabelValue createUpdatedEntity(EntityManager em) {
        LabelValue labelValue = new LabelValue()
            .value(UPDATED_VALUE);
        return labelValue;
    }

    @BeforeEach
    public void initTest() {
        labelValue = createEntity(em);
    }

    @Test
    @Transactional
    public void createLabelValue() throws Exception {
        int databaseSizeBeforeCreate = labelValueRepository.findAll().size();

        // Create the LabelValue
        LabelValueDTO labelValueDTO = labelValueMapper.toDto(labelValue);
        restLabelValueMockMvc.perform(post("/api/label-values")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(labelValueDTO)))
            .andExpect(status().isCreated());

        // Validate the LabelValue in the database
        List<LabelValue> labelValueList = labelValueRepository.findAll();
        assertThat(labelValueList).hasSize(databaseSizeBeforeCreate + 1);
        LabelValue testLabelValue = labelValueList.get(labelValueList.size() - 1);
        assertThat(testLabelValue.getValue()).isEqualTo(DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void createLabelValueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = labelValueRepository.findAll().size();

        // Create the LabelValue with an existing ID
        labelValue.setId(1L);
        LabelValueDTO labelValueDTO = labelValueMapper.toDto(labelValue);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLabelValueMockMvc.perform(post("/api/label-values")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(labelValueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LabelValue in the database
        List<LabelValue> labelValueList = labelValueRepository.findAll();
        assertThat(labelValueList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = labelValueRepository.findAll().size();
        // set the field null
        labelValue.setValue(null);

        // Create the LabelValue, which fails.
        LabelValueDTO labelValueDTO = labelValueMapper.toDto(labelValue);

        restLabelValueMockMvc.perform(post("/api/label-values")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(labelValueDTO)))
            .andExpect(status().isBadRequest());

        List<LabelValue> labelValueList = labelValueRepository.findAll();
        assertThat(labelValueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLabelValues() throws Exception {
        // Initialize the database
        labelValueRepository.saveAndFlush(labelValue);

        // Get all the labelValueList
        restLabelValueMockMvc.perform(get("/api/label-values?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(labelValue.getId().intValue())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)));
    }
    
    @Test
    @Transactional
    public void getLabelValue() throws Exception {
        // Initialize the database
        labelValueRepository.saveAndFlush(labelValue);

        // Get the labelValue
        restLabelValueMockMvc.perform(get("/api/label-values/{id}", labelValue.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(labelValue.getId().intValue()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE));
    }

    @Test
    @Transactional
    public void getNonExistingLabelValue() throws Exception {
        // Get the labelValue
        restLabelValueMockMvc.perform(get("/api/label-values/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLabelValue() throws Exception {
        // Initialize the database
        labelValueRepository.saveAndFlush(labelValue);

        int databaseSizeBeforeUpdate = labelValueRepository.findAll().size();

        // Update the labelValue
        LabelValue updatedLabelValue = labelValueRepository.findById(labelValue.getId()).get();
        // Disconnect from session so that the updates on updatedLabelValue are not directly saved in db
        em.detach(updatedLabelValue);
        updatedLabelValue
            .value(UPDATED_VALUE);
        LabelValueDTO labelValueDTO = labelValueMapper.toDto(updatedLabelValue);

        restLabelValueMockMvc.perform(put("/api/label-values")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(labelValueDTO)))
            .andExpect(status().isOk());

        // Validate the LabelValue in the database
        List<LabelValue> labelValueList = labelValueRepository.findAll();
        assertThat(labelValueList).hasSize(databaseSizeBeforeUpdate);
        LabelValue testLabelValue = labelValueList.get(labelValueList.size() - 1);
        assertThat(testLabelValue.getValue()).isEqualTo(UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingLabelValue() throws Exception {
        int databaseSizeBeforeUpdate = labelValueRepository.findAll().size();

        // Create the LabelValue
        LabelValueDTO labelValueDTO = labelValueMapper.toDto(labelValue);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLabelValueMockMvc.perform(put("/api/label-values")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(labelValueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LabelValue in the database
        List<LabelValue> labelValueList = labelValueRepository.findAll();
        assertThat(labelValueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLabelValue() throws Exception {
        // Initialize the database
        labelValueRepository.saveAndFlush(labelValue);

        int databaseSizeBeforeDelete = labelValueRepository.findAll().size();

        // Delete the labelValue
        restLabelValueMockMvc.perform(delete("/api/label-values/{id}", labelValue.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LabelValue> labelValueList = labelValueRepository.findAll();
        assertThat(labelValueList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
