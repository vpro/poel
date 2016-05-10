package nl.vpro.poel.service;

import nl.vpro.poel.domain.Prediction;
import nl.vpro.poel.domain.User;

import java.util.Collection;

public interface PredictionService {

    Collection<Prediction> getPredictions(User user);
}
