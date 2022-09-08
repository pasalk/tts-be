package hr.fer.grad.proj.ttsapp.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class GenerateSpeechDto {
    private Long voiceId;
    private String text;
}
