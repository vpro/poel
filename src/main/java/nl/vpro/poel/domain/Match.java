package nl.vpro.poel.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

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

    // Both Hibernate 4.x and Freemarker 2.3.x are not ready for use with java.time.* yet, so let's use this old skool type
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date start = null;

    @Embedded
    private MatchResult matchResult;

    public Match(String homeTeam, String awayTeam, Date start) {
        this(homeTeam, awayTeam, start, null);
    }

    public Match(String homeTeam, String awayTeam, Date start, MatchResult matchResult) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.start = start;
        this.matchResult = matchResult;
    }
}
