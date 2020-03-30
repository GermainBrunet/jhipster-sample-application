package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class LanguageCodeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LanguageCodeDTO.class);
        LanguageCodeDTO languageCodeDTO1 = new LanguageCodeDTO();
        languageCodeDTO1.setId(1L);
        LanguageCodeDTO languageCodeDTO2 = new LanguageCodeDTO();
        assertThat(languageCodeDTO1).isNotEqualTo(languageCodeDTO2);
        languageCodeDTO2.setId(languageCodeDTO1.getId());
        assertThat(languageCodeDTO1).isEqualTo(languageCodeDTO2);
        languageCodeDTO2.setId(2L);
        assertThat(languageCodeDTO1).isNotEqualTo(languageCodeDTO2);
        languageCodeDTO1.setId(null);
        assertThat(languageCodeDTO1).isNotEqualTo(languageCodeDTO2);
    }
}
