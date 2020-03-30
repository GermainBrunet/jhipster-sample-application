package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.LevelDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Level} and its DTO {@link LevelDTO}.
 */
@Mapper(componentModel = "spring", uses = {LabelMapper.class})
public interface LevelMapper extends EntityMapper<LevelDTO, Level> {

    @Mapping(source = "label.id", target = "labelId")
    LevelDTO toDto(Level level);

    @Mapping(source = "labelId", target = "label")
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
