package nl.vpro.poel.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * A user's predicted result.
 *
 * TODO: This class is currently (ab)used for predictions about either a Match or a Bonus question. There should be separate classes for these things.
 */
@Data
@EqualsAndHashCode(exclude = "id")
@NoArgsConstructor
@Entity
public class Prediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne
    private Match match;

    @ManyToOne
    private Bonus bonus;

    @Embedded
    private MatchResult matchResult;

    @ManyToOne
    private BonusChoice answer;

    private boolean multiplier = false;

    public Prediction(User user, Match match) {
        this(user, match, null);
    }

    public Prediction(User user, Match match, MatchResult matchResult) {
        this(user, match, matchResult, false);
    }

    public Prediction(User user, Bonus bonus) {
        this(user, bonus, null);
    }

    public Prediction(User user, Bonus bonus, BonusChoice answer) {
        this(user, bonus, answer, false);
    }

    public Prediction(User user, Match match, MatchResult matchResult, boolean multiplier) {
        this.user = user;
        this.match = match;
        this.matchResult = matchResult;
        this.multiplier = multiplier;
    }

    public Prediction(User user, Bonus bonus, BonusChoice answer, boolean multiplier) {
        this.user = user;
        this.bonus = bonus;
        this.answer = answer;
        this.multiplier = multiplier;
    }

    public Date getStart() {
        Date startDate = null;
        if (match != null) {
            startDate = match.getStart();
        } else if (bonus != null) {
            startDate = bonus.getStart();
        }
        return startDate;
    }
}
