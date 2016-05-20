package nl.vpro.poel.dto;

import javax.validation.constraints.NotNull;

public class PredictionDTO {

    @NotNull
    private Long matchId;

    private Integer homeTeamGoals;

    private Integer awayTeamGoals;

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public Integer getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public void setHomeTeamGoals(int homeTeamGoals) {
        this.homeTeamGoals = homeTeamGoals;
    }

    public Integer getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(int awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
    }

    @Override
    public String toString() {
        return "PredictionDTO{" +
                "matchId=" + matchId +
                ", homeTeamGoals=" + homeTeamGoals +
                ", awayTeamGoals=" + awayTeamGoals +
                '}';
    }
}
