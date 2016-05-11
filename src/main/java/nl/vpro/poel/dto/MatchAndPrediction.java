package nl.vpro.poel.dto;

import nl.vpro.poel.domain.Match;
import nl.vpro.poel.domain.Prediction;

public class MatchAndPrediction {

    private final Match match;

    private final Prediction prediction;

    public MatchAndPrediction(Match match, Prediction prediction) {
        this.match = match;
        this.prediction = prediction;
    }

    public Match getMatch() {
        return match;
    }

    public Prediction getPrediction() {
        return prediction;
    }
}
