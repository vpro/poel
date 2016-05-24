package nl.vpro.poel.dto;

public class PredictionDTO {

    private Long predictionId;

    private Long matchId;

    private Integer homeTeamGoals;

    private Integer awayTeamGoals;

    private boolean multiplier = false;

    public Long getPredictionId() {
        return predictionId;
    }

    public void setPredictionId(Long predictionId) {
        this.predictionId = predictionId;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public Integer getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public void setHomeTeamGoals(Integer homeTeamGoals) {
        this.homeTeamGoals = homeTeamGoals;
    }

    public Integer getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(Integer awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
    }

    public Boolean getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(Boolean multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public String toString() {
        return "PredictionDTO{" +
                "predictionId=" + predictionId +
                ", matchId=" + matchId +
                ", homeTeamGoals=" + homeTeamGoals +
                ", awayTeamGoals=" + awayTeamGoals +
                ", multiplier=" + multiplier +
                '}';
    }
}
