package nl.vpro.poel.domain;

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

    private Prediction() {} // For Hibernate

    public Prediction(User user, Match match, MatchResult matchResult) {
        this.user = user;
        this.match = match;
        this.matchResult = matchResult;
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

    public int getScore() {
        MatchResult predictedResult = getMatchResult();

        if (predictedResult == null) {
            return 0;
        }

        Match match = getMatch();

        if (match == null) {
            return 0;
        }

        MatchResult actualResult = match.getMatchResult();

        if (actualResult == null) {
            return 0;
        }

        int scoreForWinner = getScoreForWinner(predictedResult, actualResult);
        int scoreForResult = getScoreForResult(predictedResult, actualResult);
        return scoreForWinner + scoreForResult;
    }

    private int getScoreForWinner(MatchResult predictedResult, MatchResult actualResult) {
        Winner actualWinner = actualResult.getWinner();
        Winner predictedWinner = predictedResult.getWinner();
        return Objects.equals(actualWinner, predictedWinner) ? 2 : 0;
    }

    private int getScoreForResult(MatchResult predictedResult, MatchResult actualResult) {
        return Objects.equals(actualResult, predictedResult) ? 1 : 0;
    }
}
