package hr.fer.grad.proj.ttsapp.repository;

import hr.fer.grad.proj.ttsapp.model.db.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {

    @Query(""" 
            SELECT language FROM Language language
            WHERE :query is NULL OR lower(language.name) LIKE lower(CONCAT('%', CAST(:query as string), '%'))
            """)
    List<Language> getQueriedLanguages(@Param("query") String query);
}
