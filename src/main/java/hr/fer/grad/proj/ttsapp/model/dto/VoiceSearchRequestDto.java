package hr.fer.grad.proj.ttsapp.model.dto;

import hr.fer.grad.proj.ttsapp.enums.Gender;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class VoiceSearchRequestDto {
    private Long languageId;
    private Gender gender;
}
