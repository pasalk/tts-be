package hr.fer.grad.proj.ttsapp.controller;

import hr.fer.grad.proj.ttsapp.model.dto.GenerateSpeechDto;
import hr.fer.grad.proj.ttsapp.model.dto.KeyValueDto;
import hr.fer.grad.proj.ttsapp.model.dto.LanguageSearchRequestDto;
import hr.fer.grad.proj.ttsapp.model.dto.VoiceSearchRequestDto;
import hr.fer.grad.proj.ttsapp.service.TTSService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final TTSService ttsService;

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello");
    }

    @PostMapping("/generateSpeech")
    public ResponseEntity<String> generateSpeech(@RequestBody final GenerateSpeechDto generateSpeechDto)
            throws ExecutionException, InterruptedException {
        ttsService.generateSpeech(generateSpeechDto);
        return ResponseEntity.ok("Your text has been generated.");
    }

    @PostMapping("/generateAndDownloadSpeech")
    public ResponseEntity<Resource> generateAndDownloadSpeech(@RequestBody final GenerateSpeechDto generateSpeechDto) throws ExecutionException, InterruptedException {
        byte[] audio = ttsService.generateAndDownloadSpeech(generateSpeechDto);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(
                ContentDisposition.builder("inline")
                        .filename("audiofile.wav").build()
        );

        ByteArrayResource byteArrayResource = new ByteArrayResource(audio);
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(audio.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(byteArrayResource);

    }

    @PostMapping("/language/list")
    public ResponseEntity<List<KeyValueDto>> getLanguagesList(@RequestBody final LanguageSearchRequestDto languageSearchRequestDto) {
        return ResponseEntity.ok(ttsService.getLanguagesList(languageSearchRequestDto));
    }

    @PostMapping("/voice/list")
    public ResponseEntity<List<KeyValueDto>> getVoicesList(@RequestBody final VoiceSearchRequestDto voiceSearchRequestDto) {
        return ResponseEntity.ok(ttsService.getVoicesList(voiceSearchRequestDto));
    }
}
