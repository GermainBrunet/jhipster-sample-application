package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class LanguageValueTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LanguageValue.class);
        LanguageValue languageValue1 = new LanguageValue();
        languageValue1.setId(1L);
        LanguageValue languageValue2 = new LanguageValue();
        languageValue2.setId(languageValue1.getId());
        assertThat(languageValue1).isEqualTo(languageValue2);
        languageValue2.setId(2L);
        assertThat(languageValue1).isNotEqualTo(languageValue2);
        languageValue1.setId(null);
        assertThat(languageValue1).isNotEqualTo(languageValue2);
    }
}
