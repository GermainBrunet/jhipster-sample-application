package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ExerciseGroupMapperTest {

    private ExerciseGroupMapper exerciseGroupMapper;

    @BeforeEach
    public void setUp() {
        exerciseGroupMapper = new ExerciseGroupMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(exerciseGroupMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(exerciseGroupMapper.fromId(null)).isNull();
    }
}
