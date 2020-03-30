package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.LabelValueDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link LabelValue} and its DTO {@link LabelValueDTO}.
 */
@Mapper(componentModel = "spring", uses = {LabelMapper.class})
public interface LabelValueMapper extends EntityMapper<LabelValueDTO, LabelValue> {

    @Mapping(source = "code.id", target = "codeId")
    LabelValueDTO toDto(LabelValue labelValue);

    @Mapping(source = "codeId", target = "code")
    LabelValue toEntity(LabelValueDTO labelValueDTO);

    default LabelValue fromId(Long id) {
        if (id == null) {
            return null;
        }
        LabelValue labelValue = new LabelValue();
        labelValue.setId(id);
        return labelValue;
    }
}
