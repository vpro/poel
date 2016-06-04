package nl.vpro.poel.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@EqualsAndHashCode(exclude = "id")
@NoArgsConstructor
public class Bonus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String question;

    // Freemarker 2.3.x is not ready for use with java.time.* yet, so let's use this old skool type
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date start = null;

    @Enumerated(EnumType.STRING)
    @Column
    private BonusCategory category;

    @ManyToOne
    private BonusChoice answer;

    @Column
    private int score;

    @ManyToOne
    private Round round;

    public Bonus(String question, BonusCategory category, Date start, Integer score) {
        this(question, category, start, score, null);
    }

    public Bonus(String question, BonusCategory category, Date start, Integer score, BonusChoice answer) {
        this(question, category, start, score, answer, null);
    }

    public Bonus(String question, BonusCategory category, Date start, Integer score, BonusChoice answer, Round round) {
        this.question = question;
        this.category = category;
        this.start = start;
        this.score = score;
        this.answer = answer;
        this.round = round;
    }
}
