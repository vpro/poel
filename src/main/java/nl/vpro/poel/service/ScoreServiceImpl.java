package nl.vpro.poel.service;

import nl.vpro.poel.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScoreServiceImpl implements ScoreService {

    private final PredictionService predictionService;

    private final OutcomeService outcomeService;

    @Autowired
    ScoreServiceImpl(PredictionService predictionService, OutcomeService outcomeService) {
        this.predictionService = predictionService;
        this.outcomeService = outcomeService;
    }

    @Override
    public int getScore(User user) {
        return predictionService.getPredictions(user).stream()
                .collect(Collectors.summingInt(this::getScore));
    }

    @Override
    public int getScore(Prediction prediction) {
        MatchResult predictedMatchResult = prediction.getMatchResult();

        Match match = prediction.getMatch();
        Optional<Outcome> outcome = outcomeService.getOutcome(match);
        if (outcome.isPresent()) {
            MatchResult outcomeMatchResult = outcome.get().getMatchResult();
            return outcomeMatchResult.equals(predictedMatchResult) ? 3 : 0; // TODO: Implement more sophisticated scoring algorithm
        }
        return 0;
    }
}
