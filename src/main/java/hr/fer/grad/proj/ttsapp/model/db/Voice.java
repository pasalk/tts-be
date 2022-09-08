package hr.fer.grad.proj.ttsapp.model.db;

import hr.fer.grad.proj.ttsapp.enums.Gender;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "voice")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Voice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "code")
    private String code;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "gender")
    private Gender gender;
}
