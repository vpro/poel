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

    public int getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public int getAwayTeamGoals() {
        return awayTeamGoals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PredictionForm that = (PredictionForm) o;

        if (homeTeamGoals != that.homeTeamGoals) return false;
        if (awayTeamGoals != that.awayTeamGoals) return false;
        return matchId.equals(that.matchId);

    }

    @Override
    public int hashCode() {
        int result = matchId.hashCode();
        result = 31 * result + homeTeamGoals;
        result = 31 * result + awayTeamGoals;
        return result;
    }
}
