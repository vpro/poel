package nl.vpro.poel.service;

import nl.vpro.poel.domain.Match;
import nl.vpro.poel.domain.MatchResult;
import nl.vpro.poel.domain.Prediction;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreServiceImplTest {

    private ScoreService scoreService = new ScoreServiceImpl(null, null, 2, 1, 2);

    @Test
    public void scoreForCorrectWinnerAndResult() {
        checkScore(new MatchResult(0, 0), new MatchResult(0, 0), 3);
    }

    @Test
    public void scoreForCorrectWinnerAndResultWithMultiplier() {
        checkScoreWithMultiplier(new MatchResult(1, 3), new MatchResult(1, 3), 6);
    }

    @Test
    public void scoreForCorrectWinnerWrongResult() {
        checkScore(new MatchResult(1, 0), new MatchResult(2, 0), 2);
    }

    @Test
    public void scoreForCorrectWinnerWrongResultWithMultiplier() {
        checkScoreWithMultiplier(new MatchResult(5, 0), new MatchResult(1, 0), 4);
    }

    @Test
    public void scoreForWrongWinner() {
        checkScore(new MatchResult(1, 0), new MatchResult(0, 1), 0);
    }

    @Test
    public void scoreForWrongWinnerWithMultiplier() {
        checkScoreWithMultiplier(new MatchResult(5, 4), new MatchResult(3, 3), 0);
    }

    @Test
    public void scoreForNoPredictedResult() {
        checkScore(null, new MatchResult(1, 0), 0);
    }

    @Test
    public void scoreForNoPredictedResultWithMultiplier() {
        checkScore(null, new MatchResult(1, 0), 0);
    }

    @Test
    public void scoreForNoActualResult() {
        checkScore(new MatchResult(1, 0), null, 0);
    }

    @Test
    public void scoreForNoActualResultWithMultiplier() {
        checkScoreWithMultiplier(new MatchResult(1, 0), null, 0);
    }

    @Test
    public void scoreForNoResultsAtAll() {
        checkScore(null, null, 0);
    }

    @Test
    public void scoreForNoResultsAtAllWithMultiplier() {
        checkScoreWithMultiplier(null, null, 0);
    }

    private void checkScoreWithMultiplier(MatchResult predictedResult, MatchResult actualResult, int expectedScore) {
        checkScore(predictedResult, actualResult, true, expectedScore);
    }

    private void checkScore(MatchResult predictedResult, MatchResult actualResult, int expectedScore) {
        checkScore(predictedResult, actualResult, false, expectedScore);
    }

    private void checkScore(MatchResult predictedResult, MatchResult actualResult, boolean multiplier, int expectedScore) {
        Prediction prediction = new Prediction(null, new Match("Home", "Away", null, actualResult), predictedResult, multiplier);
        int actualScore = scoreService.getScore(prediction);
        assertThat(actualScore).isEqualTo(expectedScore);
    }
}