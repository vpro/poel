package nl.vpro.poel.service;

import nl.vpro.poel.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ScoreServiceImpl implements ScoreService {

    private final PredictionService predictionService;

    private final int pointsForCorrectWinner;
    private final int pointsForCorrectMatchResult;
    private final int scoreMultiplierFactor;

    @Autowired
    ScoreServiceImpl(
            PredictionService predictionService,
            @Value("${poel.pointsForCorrectWinner}") int pointsForCorrectWinner,
            @Value("${poel.pointsForCorrectMatchResult}") int pointsForCorrectMatchResult,
            @Value("${poel.scoreMultiplierFactor}") int scoreMultiplierFactor
    ) {
        this.predictionService = predictionService;
        this.pointsForCorrectWinner = pointsForCorrectWinner;
        this.pointsForCorrectMatchResult = pointsForCorrectMatchResult;
        this.scoreMultiplierFactor = scoreMultiplierFactor;
    }

    @Override
    public int getScore(User user) {
        return predictionService.getPredictions(user).stream()
                .collect(Collectors.summingInt(this::getScore));
    }

    @Override
    public int getScore(Prediction prediction) {

        MatchResult predictedResult = prediction.getMatchResult();
        if (predictedResult == null) {
            return 0;
        }

        Match match = prediction.getMatch();
        if (match == null ) {
            return 0;
        }

        MatchResult actualResult = match.getMatchResult();
        if (actualResult == null) {
            return 0;
        }

        int scoreForWinner = getScoreForWinner(predictedResult, actualResult);
        int scoreForResult = getScoreForResult(predictedResult, actualResult);
        int score = scoreForWinner + scoreForResult;
        if (prediction.getMultiplier()) {
            score *= scoreMultiplierFactor;
        }
        return score;
    }

    private int getScoreForWinner(MatchResult predictedResult, MatchResult actualResult) {
        Winner actualWinner = actualResult.getWinner();
        Winner predictedWinner = predictedResult.getWinner();
        return Objects.equals(actualWinner, predictedWinner) ? pointsForCorrectWinner : 0;
    }

    private int getScoreForResult(MatchResult predictedResult, MatchResult actualResult) {
        return Objects.equals(actualResult, predictedResult) ? pointsForCorrectMatchResult : 0;
    }
}
