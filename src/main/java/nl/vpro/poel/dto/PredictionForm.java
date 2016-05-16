package nl.vpro.poel.dto;

// TODO: Validator
public class PredictionForm {

    private Long matchId;

    private int homeTeamGoals;

    private int awayTeamGoals;

    private PredictionForm() {}

    public PredictionForm(Long matchId, int homeTeamGoals, int awayTeamGoals) {
        this.matchId = matchId;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public int getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public void setHomeTeamGoals(int homeTeamGoals) {
        this.homeTeamGoals = homeTeamGoals;
    }

    public int getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(int awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
    }

    @Override
    public String toString() {
        return "PredictionForm{" +
                "matchId=" + matchId +
                ", homeTeamGoals=" + homeTeamGoals +
                ", awayTeamGoals=" + awayTeamGoals +
                '}';
    }
}
