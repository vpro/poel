package nl.vpro.poel.service;

import nl.vpro.poel.domain.Prediction;
import nl.vpro.poel.domain.User;

import java.util.List;

public interface PredictionService {

    List<Prediction> getPredictions(User user);
}
