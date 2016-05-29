package nl.vpro.poel.dto;

import lombok.Data;
import nl.vpro.poel.domain.Prediction;

@Data
public class ScoredPrediction {

    private final Prediction prediction;

    private final int score;
}
