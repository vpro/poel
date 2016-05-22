package nl.vpro.poel.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MatchResult {

    @Column
    private int homeTeamGoals;

    @Column
    private int awayTeamGoals;

    public int getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public int getAwayTeamGoals() {
        return awayTeamGoals;
    }

    private MatchResult() {} // For Hibernate

    public MatchResult(int homeTeamGoals, int awayTeamGoals) {
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
    }

    public Winner getWinner() {
        if (homeTeamGoals > awayTeamGoals) {
            return Winner.HOME;
        }
        if (homeTeamGoals < awayTeamGoals) {
            return Winner.AWAY;
        }
        return Winner.NEITHER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MatchResult matchResult = (MatchResult) o;

        if (homeTeamGoals != matchResult.homeTeamGoals) return false;
        return awayTeamGoals == matchResult.awayTeamGoals;
    }

    @Override
    public int hashCode() {
        int result = homeTeamGoals;
        result = 31 * result + awayTeamGoals;
        return result;
    }

    @Override
    public String toString() {
        return homeTeamGoals + "-" + awayTeamGoals;
    }
}
