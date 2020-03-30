package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.KeywordDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Keyword} and its DTO {@link KeywordDTO}.
 */
@Mapper(componentModel = "spring", uses = {LabelMapper.class})
public interface KeywordMapper extends EntityMapper<KeywordDTO, Keyword> {

    @Mapping(source = "name.id", target = "nameId")
    @Mapping(source = "description.id", target = "descriptionId")
    KeywordDTO toDto(Keyword keyword);

    @Mapping(source = "nameId", target = "name")
    @Mapping(source = "descriptionId", target = "description")
    @Mapping(target = "exerciseGroups", ignore = true)
    @Mapping(target = "removeExerciseGroup", ignore = true)
    Keyword toEntity(KeywordDTO keywordDTO);

    default Keyword fromId(Long id) {
        if (id == null) {
            return null;
        }
        Keyword keyword = new Keyword();
        keyword.setId(id);
        return keyword;
    }
}
