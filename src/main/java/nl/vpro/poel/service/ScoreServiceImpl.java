package nl.vpro.poel.service;

import nl.vpro.poel.domain.Match;
import nl.vpro.poel.domain.Outcome;
import nl.vpro.poel.domain.Prediction;
import nl.vpro.poel.domain.User;
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
        String predictedValue = prediction.getValue();

        Match match = prediction.getMatch();
        Optional<Outcome> outcome = outcomeService.getOutcome(match);
        if (outcome.isPresent()) {
            String outcomeValue = outcome.get().getValue();
            return outcomeValue.equals(predictedValue) ? 3 : 0; // TODO: Implement more sophisticated scoring algorithm
        }
        return 0;
    }
}
