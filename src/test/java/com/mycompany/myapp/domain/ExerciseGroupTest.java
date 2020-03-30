package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ExerciseGroupTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExerciseGroup.class);
        ExerciseGroup exerciseGroup1 = new ExerciseGroup();
        exerciseGroup1.setId(1L);
        ExerciseGroup exerciseGroup2 = new ExerciseGroup();
        exerciseGroup2.setId(exerciseGroup1.getId());
        assertThat(exerciseGroup1).isEqualTo(exerciseGroup2);
        exerciseGroup2.setId(2L);
        assertThat(exerciseGroup1).isNotEqualTo(exerciseGroup2);
        exerciseGroup1.setId(null);
        assertThat(exerciseGroup1).isNotEqualTo(exerciseGroup2);
    }
}
