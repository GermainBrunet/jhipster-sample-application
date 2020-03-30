package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LabelValueMapperTest {

    private LabelValueMapper labelValueMapper;

    @BeforeEach
    public void setUp() {
        labelValueMapper = new LabelValueMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(labelValueMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(labelValueMapper.fromId(null)).isNull();
    }
}
