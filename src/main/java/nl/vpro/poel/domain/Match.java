package nl.vpro.poel.domain;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
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

    // Both Hibernate 4.x and Freemarker 2.3.x are not ready for use with java.time.* yet, so let's use this old skool type
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date start = null;

    @Embedded
    private MatchResult matchResult;

    Match() {} // For Hibernate

    public Match(String homeTeam, String awayTeam, Date start) {
        this(homeTeam, awayTeam, start, null);
    }

    public Match(String homeTeam, String awayTeam, Date start, MatchResult matchResult) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.start = start;
        this.matchResult = matchResult;
    }

    public Long getId() {
        return id;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public MatchResult getMatchResult() {
        return matchResult;
    }

    public Date getStart() {
        return start;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Match match = (Match) o;

        if (id != null ? !id.equals(match.id) : match.id != null) return false;
        if (homeTeam != null ? !homeTeam.equals(match.homeTeam) : match.homeTeam != null) return false;
        if (awayTeam != null ? !awayTeam.equals(match.awayTeam) : match.awayTeam != null) return false;
        if (start != null ? !start.equals(match.start) : match.start != null) return false;
        return matchResult != null ? matchResult.equals(match.matchResult) : match.matchResult == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (homeTeam != null ? homeTeam.hashCode() : 0);
        result = 31 * result + (awayTeam != null ? awayTeam.hashCode() : 0);
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (matchResult != null ? matchResult.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", start=" + start +
                ", matchResult=" + matchResult +
                '}';
    }
}
