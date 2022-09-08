package hr.fer.grad.proj.ttsapp.mapper;

import hr.fer.grad.proj.ttsapp.model.db.Voice;
import hr.fer.grad.proj.ttsapp.model.dto.KeyValueDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VoiceMapper {

    @Mapping(source = "id", target = "key")
    @Mapping(source = "code", target = "value", qualifiedByName = "nameMapper")
    KeyValueDto toKeyValueDto(Voice voice);

    @Named("nameMapper")
    default String nameMapper(String code){
        return code.substring(6).replace("Neural", "");
    }
}
