package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.LanguageCodeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link LanguageCode} and its DTO {@link LanguageCodeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LanguageCodeMapper extends EntityMapper<LanguageCodeDTO, LanguageCode> {


    @Mapping(target = "languageValues", ignore = true)
    @Mapping(target = "removeLanguageValue", ignore = true)
    LanguageCode toEntity(LanguageCodeDTO languageCodeDTO);

    default LanguageCode fromId(Long id) {
        if (id == null) {
            return null;
        }
        LanguageCode languageCode = new LanguageCode();
        languageCode.setId(id);
        return languageCode;
    }
}
