package nl.vpro.poel.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * A user's predicted result.
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

    @ManyToOne(optional = true)
    private Match match;

    @ManyToOne(optional = true)
    private Bonus bonus;

    @Embedded
    private MatchResult matchResult;

    @ManyToOne(optional = true)
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
        if ( match != null ) {
            startDate = match.getStart();
        } else if ( bonus != null ) {
            startDate = bonus.getStart();
        }
        return startDate;
    }
}
