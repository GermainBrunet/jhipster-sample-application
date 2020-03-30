package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class LanguageCodeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LanguageCode.class);
        LanguageCode languageCode1 = new LanguageCode();
        languageCode1.setId(1L);
        LanguageCode languageCode2 = new LanguageCode();
        languageCode2.setId(languageCode1.getId());
        assertThat(languageCode1).isEqualTo(languageCode2);
        languageCode2.setId(2L);
        assertThat(languageCode1).isNotEqualTo(languageCode2);
        languageCode1.setId(null);
        assertThat(languageCode1).isNotEqualTo(languageCode2);
    }
}
