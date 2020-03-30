package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class LabelValueDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LabelValueDTO.class);
        LabelValueDTO labelValueDTO1 = new LabelValueDTO();
        labelValueDTO1.setId(1L);
        LabelValueDTO labelValueDTO2 = new LabelValueDTO();
        assertThat(labelValueDTO1).isNotEqualTo(labelValueDTO2);
        labelValueDTO2.setId(labelValueDTO1.getId());
        assertThat(labelValueDTO1).isEqualTo(labelValueDTO2);
        labelValueDTO2.setId(2L);
        assertThat(labelValueDTO1).isNotEqualTo(labelValueDTO2);
        labelValueDTO1.setId(null);
        assertThat(labelValueDTO1).isNotEqualTo(labelValueDTO2);
    }
}
