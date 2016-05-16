package nl.vpro.poel.domain;

import javax.persistence.*;
import java.util.Optional;

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

    private Prediction() {} // For Hibernate

    public Prediction(User user, Match match, MatchResult matchResult) {
        this.user = user;
        this.match = match;
        this.matchResult = matchResult;
    }

    public Match getMatch() {
        return match;
    }

    public MatchResult getMatchResult() {
        return matchResult;
    }

    public Integer getScore() {
        if (match.getMatchResult().isPresent() && matchResult != null) {
            MatchResult actualMatchResult = match.getMatchResult().get();
            Integer scoreForWinner = getScoreForWinner(actualMatchResult);
            Integer scoreForResult = getScoreForResult(actualMatchResult);
            return scoreForWinner + scoreForResult;
        }
        return 0;
    }

    private Integer getScoreForWinner(MatchResult actualResult) {
        Winner actualWinner = actualResult.getWinner();
        Winner predictedWinner = matchResult.getWinner();
        return actualWinner.equals(predictedWinner) ? 2 : 0;
    }

    private Integer getScoreForResult(MatchResult actualResult) {
        return actualResult.equals(matchResult) ? 1 : 0;
    }
}
