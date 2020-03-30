package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.LanguageValueDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link LanguageValue} and its DTO {@link LanguageValueDTO}.
 */
@Mapper(componentModel = "spring", uses = {LanguageCodeMapper.class})
public interface LanguageValueMapper extends EntityMapper<LanguageValueDTO, LanguageValue> {

    @Mapping(source = "code.id", target = "codeId")
    LanguageValueDTO toDto(LanguageValue languageValue);

    @Mapping(source = "codeId", target = "code")
    LanguageValue toEntity(LanguageValueDTO languageValueDTO);

    default LanguageValue fromId(Long id) {
        if (id == null) {
            return null;
        }
        LanguageValue languageValue = new LanguageValue();
        languageValue.setId(id);
        return languageValue;
    }
}
