package nl.vpro.poel.domain;

import javax.persistence.*;

/**
 * The actual result of a played match.
 */
@Entity
public class Outcome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @OneToOne(optional = false)
    private Match match;

    @Embedded
    private MatchResult matchResult;

    private Outcome() {} // For Hibernate

    public MatchResult getMatchResult() {
        return matchResult;
    }
}
