package hr.fer.grad.proj.ttsapp.mapper;

import hr.fer.grad.proj.ttsapp.model.db.Language;
import hr.fer.grad.proj.ttsapp.model.dto.KeyValueDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface LanguageMapper {

    @Mapping(source = "id", target = "key")
    @Mapping(source = "name", target = "value")
    KeyValueDto toKeyValue(Language language);
}
