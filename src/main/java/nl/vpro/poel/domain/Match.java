package nl.vpro.poel.domain;

import javax.persistence.*;
import java.time.Instant;
import java.util.Optional;

/**
 * A match for users to predict the outcome of. Matches can have limited periods in which they can be predicted.
 */
@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String homeTeam;

    @Column(nullable = false)
    private String awayTeam;

    @Column(nullable = false)
    private Instant start = null;

    @Embedded
    private MatchResult matchResult = null;

    private Match() {} // For Hibernate

    public Match(String homeTeam, String awayTeam, Instant start) {
        this(homeTeam, awayTeam, start, null);
    }

    public Match(String homeTeam, String awayTeam, Instant start, MatchResult matchResult) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.start = start;
        this.matchResult = matchResult;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public Optional<MatchResult> getMatchResult() {
        return Optional.ofNullable(matchResult);
    }

    public MatchResult getMatchResultOrNull() {
        return matchResult;
    }

    public Instant getStart() {
        return start;
    }
}
