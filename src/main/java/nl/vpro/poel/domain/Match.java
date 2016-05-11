package nl.vpro.poel.domain;

import javax.persistence.*;
import java.time.Instant;

/**
 * A match for users to predict the outcome of. Matches can have limited periods in which they can be predicted.
 */
@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @OneToOne
    private Team homeTeam;

    @OneToOne
    private Team awayTeam;

    @Column
    private Instant invalidAfter = null;

    @Column
    private Instant invalidBefore = null;

    private Match() {} // For Hibernate

    public Match(Team homeTeam, Team awayTeam) {
        this(homeTeam, awayTeam, null, null);
    }

    public Match(Team homeTeam, Team awayTeam, Instant invalidAfter, Instant invalidBefore) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.invalidAfter = invalidAfter;
        this.invalidBefore = invalidBefore;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public boolean canBePredicatedAt(Instant instant) {
        return instant != null && checkAfter(instant) && checkBefore(instant);
    }

    private boolean checkAfter(Instant instant) {
        return invalidAfter == null || instant.isBefore(invalidAfter);
    }

    private boolean checkBefore(Instant instant) {
        return invalidBefore == null || instant.isAfter(invalidBefore);
    }
}
