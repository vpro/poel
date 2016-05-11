package nl.vpro.poel.domain;

import javax.persistence.*;

/**
 * A user's predicted match result.
 */
@Entity
public class Prediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @OneToOne(optional = false)
    private User user;

    @OneToOne(optional = false)
    private Match match;

    @Embedded
    private MatchResult matchResult;

    public Match getMatch() {
        return match;
    }

    public MatchResult getMatchResult() {
        return matchResult;
    }
}
