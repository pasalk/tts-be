package hr.fer.grad.proj.ttsapp.repository;

import hr.fer.grad.proj.ttsapp.enums.Gender;
import hr.fer.grad.proj.ttsapp.model.db.Voice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface VoiceRepository extends JpaRepository<Voice, Long> {

    @Query("""
            SELECT voice FROM Voice voice
            WHERE (:languageId IS NULL OR voice.language.id = :languageId)
            AND (:gender IS NULL OR voice.gender = :gender)
""")
    List<Voice> getQueriedVoices(@Param("languageId") Long languageId, @Param("gender") Gender gender);
}
