package hr.fer.grad.proj.ttsapp.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class KeyValueDto {
    private Long key;
    private String value;
}
