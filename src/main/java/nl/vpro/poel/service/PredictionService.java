package nl.vpro.poel.service;

import nl.vpro.poel.domain.Match;
import nl.vpro.poel.domain.Prediction;
import nl.vpro.poel.domain.User;

import java.util.List;
import java.util.Optional;

public interface PredictionService {

    List<Prediction> getPredictions(User user);

    Optional<Prediction> getPredictionForMatch(User user, Match match);
}
