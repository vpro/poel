package nl.vpro.poel.dto;

import nl.vpro.poel.domain.Prediction;

public class ScoredPrediction {

    private final Prediction prediction;

    private final int score;

    public ScoredPrediction(Prediction prediction, int score) {
        this.prediction = prediction;
        this.score = score;
    }

    public Prediction getPrediction() {
        return prediction;
    }

    public int getScore() {
        return score;
    }
}
