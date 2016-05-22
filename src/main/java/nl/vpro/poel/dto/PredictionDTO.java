package nl.vpro.poel.dto;

public class PredictionDTO {

    private Long predictionId;

    private Long matchId;

    private Integer homeTeamGoals;

    private Integer awayTeamGoals;

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
                "predictionId=" + predictionId +
                ", matchId=" + matchId +
                ", homeTeamGoals=" + homeTeamGoals +
                ", awayTeamGoals=" + awayTeamGoals +
                '}';
    }
}
