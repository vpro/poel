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

    @Column(nullable = false)
    private BonusCategory category;

    // Both Hibernate 4.x and Freemarker 2.3.x are not ready for use with java.time.* yet, so let's use this old skool type
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date start = null;

    @ManyToOne
    private BonusChoice answer;

    @Column
    private int score;

    public Bonus(String question, BonusCategory category, Date start, Integer score) {
        this(question, category, start, score, null);
    }

    public Bonus(String question, BonusCategory category, Date start, Integer score, BonusChoice answer) {
        this.question = question;
        this.category = category;
        this.start = start;
        this.score = score;
        this.answer = answer;
    }
}
