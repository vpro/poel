package nl.vpro.poel.service;

import nl.vpro.poel.domain.Bonus;
import nl.vpro.poel.domain.Match;
import nl.vpro.poel.domain.Prediction;
import nl.vpro.poel.domain.User;
import nl.vpro.poel.dto.PredictionForm;
import nl.vpro.poel.exception.MultiplierException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface PredictionService {

    int save(User user, PredictionForm predictionForm, Instant submittedAt) throws MultiplierException;

    List<Prediction> getPredictions(User user);

    Optional<Prediction> getPredictionForMatch(User user, Match match);

    Optional<Prediction> getPredictionForBonus(User user, Bonus bonus);

    Optional<Prediction> getPredictionForSubject(User user, Match match);

    Optional<Prediction> getPredictionForSubject(User user, Bonus bonus);
}
