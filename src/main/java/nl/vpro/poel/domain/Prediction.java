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

    public Match getMatch() {
        return match;
    }

    public MatchResult getMatchResult() {
        return matchResult;
    }

    public Integer getScore() {
        // TODO: Implement desired scoring algorithm
        Optional<MatchResult> actualMatchResult = match.getMatchResult();
        if (actualMatchResult.isPresent() && matchResult != null) {
            return actualMatchResult.get().equals(matchResult) ? 3 : 0;
        }
        return 0;
    }
}
