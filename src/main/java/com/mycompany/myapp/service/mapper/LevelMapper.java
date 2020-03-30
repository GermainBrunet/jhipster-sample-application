package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.LevelDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Level} and its DTO {@link LevelDTO}.
 */
@Mapper(componentModel = "spring", uses = {LabelMapper.class})
public interface LevelMapper extends EntityMapper<LevelDTO, Level> {

    @Mapping(source = "name.id", target = "nameId")
    @Mapping(source = "description.id", target = "descriptionId")
    LevelDTO toDto(Level level);

    @Mapping(source = "nameId", target = "name")
    @Mapping(source = "descriptionId", target = "description")
    @Mapping(target = "exerciseGroups", ignore = true)
    @Mapping(target = "removeExerciseGroup", ignore = true)
    Level toEntity(LevelDTO levelDTO);

    default Level fromId(Long id) {
        if (id == null) {
            return null;
        }
        Level level = new Level();
        level.setId(id);
        return level;
    }
}
