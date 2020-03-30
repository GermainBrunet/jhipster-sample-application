package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.ExerciseGroup;
import com.mycompany.myapp.repository.ExerciseGroupRepository;
import com.mycompany.myapp.service.ExerciseGroupService;
import com.mycompany.myapp.service.dto.ExerciseGroupDTO;
import com.mycompany.myapp.service.mapper.ExerciseGroupMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.domain.enumeration.Language;
/**
 * Integration tests for the {@link ExerciseGroupResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class ExerciseGroupResourceIT {

    private static final Language DEFAULT_LANGUAGE = Language.FRENCH;
    private static final Language UPDATED_LANGUAGE = Language.ENGLISH;

    private static final UUID DEFAULT_GUID = UUID.randomUUID();
    private static final UUID UPDATED_GUID = UUID.randomUUID();

    private static final Integer DEFAULT_SORT_ORDER = 1;
    private static final Integer UPDATED_SORT_ORDER = 2;

    private static final String DEFAULT_AUTHOR = "AAAAAAAAAA";
    private static final String UPDATED_AUTHOR = "BBBBBBBBBB";

    @Autowired
    private ExerciseGroupRepository exerciseGroupRepository;

    @Mock
    private ExerciseGroupRepository exerciseGroupRepositoryMock;

    @Autowired
    private ExerciseGroupMapper exerciseGroupMapper;

    @Mock
    private ExerciseGroupService exerciseGroupServiceMock;

    @Autowired
    private ExerciseGroupService exerciseGroupService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restExerciseGroupMockMvc;

    private ExerciseGroup exerciseGroup;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExerciseGroup createEntity(EntityManager em) {
        ExerciseGroup exerciseGroup = new ExerciseGroup()
            .language(DEFAULT_LANGUAGE)
            .guid(DEFAULT_GUID)
            .sortOrder(DEFAULT_SORT_ORDER)
            .author(DEFAULT_AUTHOR);
        return exerciseGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExerciseGroup createUpdatedEntity(EntityManager em) {
        ExerciseGroup exerciseGroup = new ExerciseGroup()
            .language(UPDATED_LANGUAGE)
            .guid(UPDATED_GUID)
            .sortOrder(UPDATED_SORT_ORDER)
            .author(UPDATED_AUTHOR);
        return exerciseGroup;
    }

    @BeforeEach
    public void initTest() {
        exerciseGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createExerciseGroup() throws Exception {
        int databaseSizeBeforeCreate = exerciseGroupRepository.findAll().size();

        // Create the ExerciseGroup
        ExerciseGroupDTO exerciseGroupDTO = exerciseGroupMapper.toDto(exerciseGroup);
        restExerciseGroupMockMvc.perform(post("/api/exercise-groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(exerciseGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the ExerciseGroup in the database
        List<ExerciseGroup> exerciseGroupList = exerciseGroupRepository.findAll();
        assertThat(exerciseGroupList).hasSize(databaseSizeBeforeCreate + 1);
        ExerciseGroup testExerciseGroup = exerciseGroupList.get(exerciseGroupList.size() - 1);
        assertThat(testExerciseGroup.getLanguage()).isEqualTo(DEFAULT_LANGUAGE);
        assertThat(testExerciseGroup.getGuid()).isEqualTo(DEFAULT_GUID);
        assertThat(testExerciseGroup.getSortOrder()).isEqualTo(DEFAULT_SORT_ORDER);
        assertThat(testExerciseGroup.getAuthor()).isEqualTo(DEFAULT_AUTHOR);
    }

    @Test
    @Transactional
    public void createExerciseGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = exerciseGroupRepository.findAll().size();

        // Create the ExerciseGroup with an existing ID
        exerciseGroup.setId(1L);
        ExerciseGroupDTO exerciseGroupDTO = exerciseGroupMapper.toDto(exerciseGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restExerciseGroupMockMvc.perform(post("/api/exercise-groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(exerciseGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ExerciseGroup in the database
        List<ExerciseGroup> exerciseGroupList = exerciseGroupRepository.findAll();
        assertThat(exerciseGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkGuidIsRequired() throws Exception {
        int databaseSizeBeforeTest = exerciseGroupRepository.findAll().size();
        // set the field null
        exerciseGroup.setGuid(null);

        // Create the ExerciseGroup, which fails.
        ExerciseGroupDTO exerciseGroupDTO = exerciseGroupMapper.toDto(exerciseGroup);

        restExerciseGroupMockMvc.perform(post("/api/exercise-groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(exerciseGroupDTO)))
            .andExpect(status().isBadRequest());

        List<ExerciseGroup> exerciseGroupList = exerciseGroupRepository.findAll();
        assertThat(exerciseGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllExerciseGroups() throws Exception {
        // Initialize the database
        exerciseGroupRepository.saveAndFlush(exerciseGroup);

        // Get all the exerciseGroupList
        restExerciseGroupMockMvc.perform(get("/api/exercise-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(exerciseGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].language").value(hasItem(DEFAULT_LANGUAGE.toString())))
            .andExpect(jsonPath("$.[*].guid").value(hasItem(DEFAULT_GUID.toString())))
            .andExpect(jsonPath("$.[*].sortOrder").value(hasItem(DEFAULT_SORT_ORDER)))
            .andExpect(jsonPath("$.[*].author").value(hasItem(DEFAULT_AUTHOR)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllExerciseGroupsWithEagerRelationshipsIsEnabled() throws Exception {
        when(exerciseGroupServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restExerciseGroupMockMvc.perform(get("/api/exercise-groups?eagerload=true"))
            .andExpect(status().isOk());

        verify(exerciseGroupServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllExerciseGroupsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(exerciseGroupServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restExerciseGroupMockMvc.perform(get("/api/exercise-groups?eagerload=true"))
            .andExpect(status().isOk());

        verify(exerciseGroupServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getExerciseGroup() throws Exception {
        // Initialize the database
        exerciseGroupRepository.saveAndFlush(exerciseGroup);

        // Get the exerciseGroup
        restExerciseGroupMockMvc.perform(get("/api/exercise-groups/{id}", exerciseGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(exerciseGroup.getId().intValue()))
            .andExpect(jsonPath("$.language").value(DEFAULT_LANGUAGE.toString()))
            .andExpect(jsonPath("$.guid").value(DEFAULT_GUID.toString()))
            .andExpect(jsonPath("$.sortOrder").value(DEFAULT_SORT_ORDER))
            .andExpect(jsonPath("$.author").value(DEFAULT_AUTHOR));
    }

    @Test
    @Transactional
    public void getNonExistingExerciseGroup() throws Exception {
        // Get the exerciseGroup
        restExerciseGroupMockMvc.perform(get("/api/exercise-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateExerciseGroup() throws Exception {
        // Initialize the database
        exerciseGroupRepository.saveAndFlush(exerciseGroup);

        int databaseSizeBeforeUpdate = exerciseGroupRepository.findAll().size();

        // Update the exerciseGroup
        ExerciseGroup updatedExerciseGroup = exerciseGroupRepository.findById(exerciseGroup.getId()).get();
        // Disconnect from session so that the updates on updatedExerciseGroup are not directly saved in db
        em.detach(updatedExerciseGroup);
        updatedExerciseGroup
            .language(UPDATED_LANGUAGE)
            .guid(UPDATED_GUID)
            .sortOrder(UPDATED_SORT_ORDER)
            .author(UPDATED_AUTHOR);
        ExerciseGroupDTO exerciseGroupDTO = exerciseGroupMapper.toDto(updatedExerciseGroup);

        restExerciseGroupMockMvc.perform(put("/api/exercise-groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(exerciseGroupDTO)))
            .andExpect(status().isOk());

        // Validate the ExerciseGroup in the database
        List<ExerciseGroup> exerciseGroupList = exerciseGroupRepository.findAll();
        assertThat(exerciseGroupList).hasSize(databaseSizeBeforeUpdate);
        ExerciseGroup testExerciseGroup = exerciseGroupList.get(exerciseGroupList.size() - 1);
        assertThat(testExerciseGroup.getLanguage()).isEqualTo(UPDATED_LANGUAGE);
        assertThat(testExerciseGroup.getGuid()).isEqualTo(UPDATED_GUID);
        assertThat(testExerciseGroup.getSortOrder()).isEqualTo(UPDATED_SORT_ORDER);
        assertThat(testExerciseGroup.getAuthor()).isEqualTo(UPDATED_AUTHOR);
    }

    @Test
    @Transactional
    public void updateNonExistingExerciseGroup() throws Exception {
        int databaseSizeBeforeUpdate = exerciseGroupRepository.findAll().size();

        // Create the ExerciseGroup
        ExerciseGroupDTO exerciseGroupDTO = exerciseGroupMapper.toDto(exerciseGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExerciseGroupMockMvc.perform(put("/api/exercise-groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(exerciseGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ExerciseGroup in the database
        List<ExerciseGroup> exerciseGroupList = exerciseGroupRepository.findAll();
        assertThat(exerciseGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteExerciseGroup() throws Exception {
        // Initialize the database
        exerciseGroupRepository.saveAndFlush(exerciseGroup);

        int databaseSizeBeforeDelete = exerciseGroupRepository.findAll().size();

        // Delete the exerciseGroup
        restExerciseGroupMockMvc.perform(delete("/api/exercise-groups/{id}", exerciseGroup.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ExerciseGroup> exerciseGroupList = exerciseGroupRepository.findAll();
        assertThat(exerciseGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
