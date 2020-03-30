package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.LabelDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Label} and its DTO {@link LabelDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LabelMapper extends EntityMapper<LabelDTO, Label> {


    @Mapping(target = "languageValues", ignore = true)
    @Mapping(target = "removeLanguageValue", ignore = true)
    @Mapping(target = "keyword", ignore = true)
    @Mapping(target = "level", ignore = true)
    Label toEntity(LabelDTO labelDTO);

    default Label fromId(Long id) {
        if (id == null) {
            return null;
        }
        Label label = new Label();
        label.setId(id);
        return label;
    }
}
