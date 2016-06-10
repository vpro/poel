package nl.vpro.poel.service;

import nl.vpro.poel.domain.Match;
import nl.vpro.poel.domain.MatchResult;
import nl.vpro.poel.domain.Prediction;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreServiceImplTest {

    private ScoreService scoreService;

    @Before
    public void setup() {
        UserService userService = null;
        PredictionService predictionService = null;
        int pointsForCorrectWinner = 1;
        int pointsForCorrectMatchResult = 3;
        int scoreMultiplierFactor = 2;
        scoreService = new ScoreServiceImpl(userService, predictionService, pointsForCorrectWinner, pointsForCorrectMatchResult, scoreMultiplierFactor);
    }

    @Test
    public void scoreForCorrectWinnerAndResult() {
        checkScore("0-0", "0-0", 3);
    }

    @Test
    public void scoreForCorrectWinnerAndResultWithMultiplier() {
        checkScoreWithMultiplier("1-3", "1-3", 6);
    }

    @Test
    public void scoreForCorrectWinnerWrongResult() {
        checkScore("1-0", "2-0", 1);
    }

    @Test
    public void scoreForCorrectWinnerWrongResultWithMultiplier() {
        checkScoreWithMultiplier("5-0", "1-0", 2);
    }

    @Test
    public void scoreForWrongWinner() {
        checkScore("1-0", "0-1", 0);
    }

    @Test
    public void scoreForWrongWinnerWithMultiplier() {
        checkScoreWithMultiplier("5-4", "3-3", 0);
    }

    @Test
    public void scoreForNoPredictedResult() {
        checkScore(null, "1-0", 0);
    }

    @Test
    public void scoreForNoPredictedResultWithMultiplier() {
        checkScore(null, "1-0", 0);
    }

    @Test
    public void scoreForNoActualResult() {
        checkScore("1-0", null, 0);
    }

    @Test
    public void scoreForNoActualResultWithMultiplier() {
        checkScoreWithMultiplier("1-0", null, 0);
    }

    @Test
    public void scoreForNoResultsAtAll() {
        checkScore(null, null, 0);
    }

    @Test
    public void scoreForNoResultsAtAllWithMultiplier() {
        checkScoreWithMultiplier(null, null, 0);
    }

    private void checkScoreWithMultiplier(String predictedResult, String actualResult, int expectedScore) {
        checkScore(predictedResult, actualResult, true, expectedScore);
    }

    private void checkScore(String predictedResult, String actualResult, int expectedScore) {
        checkScore(predictedResult, actualResult, false, expectedScore);
    }

    private void checkScore(String predicted, String actual, boolean multiplier, int expectedScore) {
        MatchResult predictedResult = toMatchResult(predicted);
        MatchResult actualResult = toMatchResult(actual);
        Prediction prediction = new Prediction(null, new Match("Home", "Away", null, actualResult), predictedResult, multiplier);
        int actualScore = scoreService.getScore(prediction);
        assertThat(actualScore).isEqualTo(expectedScore);
    }

    private MatchResult toMatchResult(String matchResult) {
        if (matchResult == null) {
            return null;
        }
        String[] goals = matchResult.split("-");
        int homeTeamGoals = Integer.valueOf(goals[0]);
        int awayTeamGoals = Integer.valueOf(goals[1]);
        return new MatchResult(homeTeamGoals, awayTeamGoals);
    }
}