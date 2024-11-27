package hr.fer.grad.proj.ttsapp.service;

import com.microsoft.cognitiveservices.speech.*;
import hr.fer.grad.proj.ttsapp.exception.UnableToGenerateException;
import hr.fer.grad.proj.ttsapp.mapper.LanguageMapper;
import hr.fer.grad.proj.ttsapp.mapper.VoiceMapper;
import hr.fer.grad.proj.ttsapp.model.db.Voice;
import hr.fer.grad.proj.ttsapp.model.dto.GenerateSpeechDto;
import hr.fer.grad.proj.ttsapp.model.dto.KeyValueDto;
import hr.fer.grad.proj.ttsapp.model.dto.LanguageSearchRequestDto;
import hr.fer.grad.proj.ttsapp.model.dto.VoiceSearchRequestDto;
import hr.fer.grad.proj.ttsapp.repository.LanguageRepository;
import hr.fer.grad.proj.ttsapp.repository.VoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@Service
public class TTSService {

    private final LanguageRepository languageRepository;
    private final LanguageMapper languageMapper;

    private final VoiceRepository voiceRepository;
    private final VoiceMapper voiceMapper;

    private static final String SUBSCRIPTION_KEY = "add-your-subscription-key";
    private static final String SERVICE_REGION = "add-your-service-region";

    public List<KeyValueDto> getLanguagesList(final LanguageSearchRequestDto languageSearchRequestDto){
        return languageRepository.getQueriedLanguages(languageSearchRequestDto.getQuery())
                .stream()
                .map(languageMapper::toKeyValue)
                .toList();
    }

    public List<KeyValueDto> getVoicesList(final VoiceSearchRequestDto voiceSearchRequestDto) {
        return voiceRepository.getQueriedVoices(voiceSearchRequestDto.getLanguageId(), voiceSearchRequestDto.getGender())
                .stream()
                .map(voiceMapper::toKeyValueDto)
                .toList();
    }

    public void generateSpeech(final GenerateSpeechDto generateSpeechDto) throws ExecutionException, InterruptedException {
        Voice voice = voiceRepository.getById(generateSpeechDto.getVoiceId());

        SpeechConfig speechConfig = SpeechConfig.fromSubscription(SUBSCRIPTION_KEY, SERVICE_REGION);
        speechConfig.setSpeechSynthesisVoiceName(voice.getCode());

        SpeechSynthesizer speechSynthesizer = new SpeechSynthesizer(speechConfig);
        SpeechSynthesisResult speechRecognitionResult = speechSynthesizer.SpeakTextAsync(generateSpeechDto.getText()).get();

        if (speechRecognitionResult.getReason() == ResultReason.Canceled)
            throw new UnableToGenerateException("Speech wasn't able to be generated.");

    }

    public byte[] generateAndDownloadSpeech(final GenerateSpeechDto generateSpeechDto) throws ExecutionException, InterruptedException {
        Voice voice = voiceRepository.getById(generateSpeechDto.getVoiceId());

        SpeechConfig speechConfig = SpeechConfig.fromSubscription(SUBSCRIPTION_KEY, SERVICE_REGION);
        speechConfig.setSpeechSynthesisVoiceName(voice.getCode());

        SpeechSynthesizer speechSynthesizer = new SpeechSynthesizer(speechConfig);
        SpeechSynthesisResult speechRecognitionResult = speechSynthesizer.SpeakTextAsync(generateSpeechDto.getText()).get();

        if (speechRecognitionResult.getReason() == ResultReason.Canceled)
            throw new UnableToGenerateException("Speech wasn't able to be generated.");

        return speechRecognitionResult.getAudioData();
    }

}
