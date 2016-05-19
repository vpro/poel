package nl.vpro.poel.dto;

import java.util.List;

// TODO: Validator
public class PredictionForm {

    private List<PredictionDTO> predictions;

    public List<PredictionDTO> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<PredictionDTO> predictions) {
        this.predictions = predictions;
    }

    @Override
    public String toString() {
        return "PredictionForm{" +
                "predictions=" + predictions +
                '}';
    }
}
