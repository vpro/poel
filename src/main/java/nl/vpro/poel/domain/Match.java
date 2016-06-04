package nl.vpro.poel.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * A match for users to predict the outcome of. Matches can have limited periods in which they can be predicted.
 */
@Entity
@Data
@EqualsAndHashCode(exclude = "id")
@NoArgsConstructor
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String homeTeam;

    @Column(nullable = false)
    private String awayTeam;

    // Freemarker 2.3.x is not ready for use with java.time.* yet, so let's use this old skool type
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date start = null;

    @Embedded
    private MatchResult matchResult;

    @ManyToOne
    private Round round;

    public Match(String homeTeam, String awayTeam, Date start) {
        this(homeTeam, awayTeam, start, null, null);
    }

    public Match(String homeTeam, String awayTeam, Date start, MatchResult matchResult) {
        this(homeTeam, awayTeam, start, matchResult, null);
    }

    public Match(String homeTeam, String awayTeam, Date start, MatchResult matchResult, Round round) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.start = start;
        this.matchResult = matchResult;
        this.round = round;
    }
}
