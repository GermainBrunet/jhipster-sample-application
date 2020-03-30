package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class LanguageValueDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LanguageValueDTO.class);
        LanguageValueDTO languageValueDTO1 = new LanguageValueDTO();
        languageValueDTO1.setId(1L);
        LanguageValueDTO languageValueDTO2 = new LanguageValueDTO();
        assertThat(languageValueDTO1).isNotEqualTo(languageValueDTO2);
        languageValueDTO2.setId(languageValueDTO1.getId());
        assertThat(languageValueDTO1).isEqualTo(languageValueDTO2);
        languageValueDTO2.setId(2L);
        assertThat(languageValueDTO1).isNotEqualTo(languageValueDTO2);
        languageValueDTO1.setId(null);
        assertThat(languageValueDTO1).isNotEqualTo(languageValueDTO2);
    }
}
