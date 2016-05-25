package nl.vpro.poel.service;

import nl.vpro.poel.domain.Prediction;
import nl.vpro.poel.domain.User;

public interface ScoreService {

    int getScore(User user);

    int getScore(Prediction prediction);
}
