package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ExerciseGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ExerciseGroup} and its DTO {@link ExerciseGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {LabelMapper.class, KeywordMapper.class, LevelMapper.class})
public interface ExerciseGroupMapper extends EntityMapper<ExerciseGroupDTO, ExerciseGroup> {

    @Mapping(source = "name.id", target = "nameId")
    @Mapping(source = "description.id", target = "descriptionId")
    @Mapping(source = "level.id", target = "levelId")
    ExerciseGroupDTO toDto(ExerciseGroup exerciseGroup);

    @Mapping(source = "nameId", target = "name")
    @Mapping(source = "descriptionId", target = "description")
    @Mapping(target = "removeKeyword", ignore = true)
    @Mapping(source = "levelId", target = "level")
    ExerciseGroup toEntity(ExerciseGroupDTO exerciseGroupDTO);

    default ExerciseGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        ExerciseGroup exerciseGroup = new ExerciseGroup();
        exerciseGroup.setId(id);
        return exerciseGroup;
    }
}
