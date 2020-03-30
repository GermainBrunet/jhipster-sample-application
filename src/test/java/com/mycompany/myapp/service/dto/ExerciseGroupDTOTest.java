package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ExerciseGroupDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExerciseGroupDTO.class);
        ExerciseGroupDTO exerciseGroupDTO1 = new ExerciseGroupDTO();
        exerciseGroupDTO1.setId(1L);
        ExerciseGroupDTO exerciseGroupDTO2 = new ExerciseGroupDTO();
        assertThat(exerciseGroupDTO1).isNotEqualTo(exerciseGroupDTO2);
        exerciseGroupDTO2.setId(exerciseGroupDTO1.getId());
        assertThat(exerciseGroupDTO1).isEqualTo(exerciseGroupDTO2);
        exerciseGroupDTO2.setId(2L);
        assertThat(exerciseGroupDTO1).isNotEqualTo(exerciseGroupDTO2);
        exerciseGroupDTO1.setId(null);
        assertThat(exerciseGroupDTO1).isNotEqualTo(exerciseGroupDTO2);
    }
}
