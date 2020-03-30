package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LanguageCodeMapperTest {

    private LanguageCodeMapper languageCodeMapper;

    @BeforeEach
    public void setUp() {
        languageCodeMapper = new LanguageCodeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(languageCodeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(languageCodeMapper.fromId(null)).isNull();
    }
}
