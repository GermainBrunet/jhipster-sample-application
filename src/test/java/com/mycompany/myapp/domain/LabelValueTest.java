package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class LabelValueTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LabelValue.class);
        LabelValue labelValue1 = new LabelValue();
        labelValue1.setId(1L);
        LabelValue labelValue2 = new LabelValue();
        labelValue2.setId(labelValue1.getId());
        assertThat(labelValue1).isEqualTo(labelValue2);
        labelValue2.setId(2L);
        assertThat(labelValue1).isNotEqualTo(labelValue2);
        labelValue1.setId(null);
        assertThat(labelValue1).isNotEqualTo(labelValue2);
    }
}
