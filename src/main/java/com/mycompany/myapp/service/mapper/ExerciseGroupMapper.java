package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ExerciseGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ExerciseGroup} and its DTO {@link ExerciseGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {KeywordMapper.class, LevelMapper.class})
public interface ExerciseGroupMapper extends EntityMapper<ExerciseGroupDTO, ExerciseGroup> {

    @Mapping(source = "level.id", target = "levelId")
    ExerciseGroupDTO toDto(ExerciseGroup exerciseGroup);

    @Mapping(target = "exercises", ignore = true)
    @Mapping(target = "removeExercise", ignore = true)
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
