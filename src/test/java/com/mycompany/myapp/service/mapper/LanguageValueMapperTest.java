package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LanguageValueMapperTest {

    private LanguageValueMapper languageValueMapper;

    @BeforeEach
    public void setUp() {
        languageValueMapper = new LanguageValueMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(languageValueMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(languageValueMapper.fromId(null)).isNull();
    }
}
