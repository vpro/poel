package nl.vpro.poel.service;

import nl.vpro.poel.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ScoreServiceImpl implements ScoreService {

    private final UserService userService;

    private final PredictionService predictionService;

    private final int pointsForCorrectMatchWinner;
    private final int pointsForCorrectMatchResult;
    private final int scoreMultiplierFactor;

    @Autowired
    ScoreServiceImpl(
            UserService userService,
            PredictionService predictionService,
            @Value("${poel.pointsForCorrectMatchWinner}") int pointsForCorrectMatchWinner,
            @Value("${poel.pointsForCorrectMatchResult}") int pointsForCorrectMatchResult,
            @Value("${poel.scoreMultiplierFactor}") int scoreMultiplierFactor
    ) {
        this.userService = userService;
        this.predictionService = predictionService;
        this.pointsForCorrectMatchWinner = pointsForCorrectMatchWinner;
        this.pointsForCorrectMatchResult = pointsForCorrectMatchResult;
        this.scoreMultiplierFactor = scoreMultiplierFactor;
    }

    @Override
    public double getAverageScore(UserGroup userGroup) {
        return userService.getAllUsersForUserGroup(userGroup).stream()
                .collect(Collectors.averagingInt(this::getScore));
    }

    @Override
    public int getScore(User user) {
        return predictionService.getPredictions(user).stream()
                .collect(Collectors.summingInt(this::getScore));
    }

    @Override
    public int getScore(Prediction prediction) {

        if (prediction.getMatch() != null) {
            return getScoreForMatchPrediction(prediction);

        } else if ( prediction.getBonus() != null ) {
            return getScoreForBonusPrediction(prediction);

        } else {
            return 0;
        }
    }

    private int getScoreForMatchPrediction(Prediction prediction) {

        MatchResult predictedMatchResult = prediction.getMatchResult();
        if (predictedMatchResult == null) {
            return 0;
        }

        Match match = prediction.getMatch();
        if (match == null ) {
            return 0;
        }

        MatchResult actualMatchResult = match.getMatchResult();
        if (actualMatchResult == null) {
            return 0;
        }

        int scoreForWinner = getScoreForWinner(predictedMatchResult, actualMatchResult);
        int scoreForResult = getScoreForResult(predictedMatchResult, actualMatchResult);
        int score = scoreForWinner + scoreForResult;
        if (prediction.isMultiplier()) {
            score *= scoreMultiplierFactor;
        }
        return score;
    }

    private int getScoreForBonusPrediction(Prediction prediction) {

        BonusChoice predictedAnswer = prediction.getAnswer();
        if (predictedAnswer == null) {
            return 0;
        }

        Bonus bonus = prediction.getBonus();
        if (bonus == null) {
            return 0;
        }

        BonusChoice actualAnswer = bonus.getAnswer();
        if (actualAnswer == null) {
            return 0;
        }

        int score = getScoreForResult(predictedAnswer, actualAnswer, bonus.getScore());
        if (prediction.isMultiplier()) {
            score *= scoreMultiplierFactor;
        }
        return score;
    }

    private int getScoreForWinner(MatchResult predictedResult, MatchResult actualResult) {
        MatchWinner actualWinner = getMatchWinner(actualResult);
        MatchWinner predictedWinner = getMatchWinner(predictedResult);
        return Objects.equals(actualWinner, predictedWinner) ? pointsForCorrectMatchWinner : 0;
    }

    private int getScoreForResult(MatchResult predictedResult, MatchResult actualResult) {
        return Objects.equals(actualResult, predictedResult) ? pointsForCorrectMatchResult : 0;
    }

    /**
     * @param predictedResult
     * @param actualResult
     * @param bonusScore - Admins can define a specific score for bonus questions
     * @return
     */
    private int getScoreForResult(BonusChoice predictedResult, BonusChoice actualResult, int bonusScore) {
        return Objects.equals(actualResult, predictedResult) ? bonusScore : 0;
    }

    private MatchWinner getMatchWinner(MatchResult matchResult) {
        int homeTeamGoals = matchResult.getHomeTeamGoals();
        int awayTeamGoals = matchResult.getAwayTeamGoals();

        if (homeTeamGoals > awayTeamGoals) {
            return MatchWinner.HOME;
        }
        if (homeTeamGoals < awayTeamGoals) {
            return MatchWinner.AWAY;
        }
        return MatchWinner.NEITHER;
    }
}
