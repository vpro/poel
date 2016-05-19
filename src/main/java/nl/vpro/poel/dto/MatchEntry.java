package nl.vpro.poel.dto;

import nl.vpro.poel.domain.Match;
import nl.vpro.poel.domain.MatchResult;

public class MatchEntry {

    private final Match match;

    private final MatchResult prediction;

    private final int score;

    public MatchEntry(Match match, MatchResult prediction, int score) {
        this.match = match;
        this.prediction = prediction;
        this.score = score;
    }

    public Match getMatch() {
        return match;
    }

    public MatchResult getPrediction() {
        return prediction;
    }

    public int getScore() {
        return score;
    }
}
