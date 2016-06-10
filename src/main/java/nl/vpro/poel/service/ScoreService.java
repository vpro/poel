package nl.vpro.poel.service;

import nl.vpro.poel.domain.Prediction;
import nl.vpro.poel.domain.User;
import nl.vpro.poel.domain.UserGroup;

public interface ScoreService {

    double getAverageScore(UserGroup userGroup);

    int getScore(User user);

    int getScore(Prediction prediction);
}
