package nl.vpro.poel.domain;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.Objects;

/**
 * A user's predicted match result.
 */
@Entity
public class Prediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Match match;

    @Embedded
    private MatchResult matchResult;

    private boolean multiplier = false;

    private Prediction() {} // For Hibernate

    public Prediction(User user, Match match) {
        this(user, match, null);
    }

    public Prediction(User user, Match match, MatchResult matchResult) {
        this(user, match, matchResult, false);
    }

    public Prediction(User user, Match match, MatchResult matchResult, boolean multiplier) {
        this.user = user;
        this.match = match;
        this.matchResult = matchResult;
        this.multiplier = multiplier;
    }

    public Long getId() {
        return id;
    }

    public Match getMatch() {
        return match;
    }

    public MatchResult getMatchResult() {
        return matchResult;
    }

    public void setMatchResult(MatchResult matchResult) {
        this.matchResult = matchResult;
    }

    public boolean getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(boolean multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public String toString() {
        return "Prediction{" +
                "id=" + id +
                ", user=" + user +
                ", match=" + match +
                ", matchResult=" + matchResult +
                ", multiplier=" + multiplier +
                '}';
    }
}
