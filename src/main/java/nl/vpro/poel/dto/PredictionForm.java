package nl.vpro.poel.dto;

import java.util.List;

// TODO: Validator
public class PredictionForm {

    private List<Prediction> predictions;

    public List<Prediction> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<Prediction> predictions) {
        this.predictions = predictions;
    }
}
