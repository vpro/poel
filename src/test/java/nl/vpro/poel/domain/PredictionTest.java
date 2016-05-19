package nl.vpro.poel.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PredictionTest {

    @Test
    public void scoreForCorrectWinnerAndResult() {
        checkScore(new MatchResult(0, 0), new MatchResult(0, 0), 3);
    }

    @Test
    public void scoreForCorrectWinnerWrongResult() {
        checkScore(new MatchResult(1, 0), new MatchResult(2, 0), 2);
    }

    @Test
    public void scoreForWrongWinner() {
        checkScore(new MatchResult(1, 0), new MatchResult(0, 1), 0);
    }

    @Test
    public void scoreForNoPredictedResult() {
        checkScore(null, new MatchResult(1, 0), 0);
    }

    @Test
    public void scoreForNoActualResult() {
        checkScore(new MatchResult(1, 0), null, 0);
    }

    @Test
    public void scoreForNoResultsAtAll() {
        checkScore(null, null, 0);
    }

    private void checkScore(MatchResult predictedResult, MatchResult actualResult, int expectedScore) {
        Prediction prediction = new Prediction(null, new Match("Home", "Away", null, actualResult), predictedResult);
        int actualScore = prediction.getScore();
        assertThat(actualScore).isEqualTo(expectedScore);
    }
}