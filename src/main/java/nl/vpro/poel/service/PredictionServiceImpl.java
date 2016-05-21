package nl.vpro.poel.service;

import nl.vpro.poel.domain.Match;
import nl.vpro.poel.domain.MatchResult;
import nl.vpro.poel.domain.Prediction;
import nl.vpro.poel.domain.User;
import nl.vpro.poel.dto.PredictionDTO;
import nl.vpro.poel.dto.PredictionForm;
import nl.vpro.poel.repository.PredictionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class PredictionServiceImpl implements PredictionService {

    private static final Logger logger = LoggerFactory.getLogger(PredictionServiceImpl.class);

    private final PredictionRepository predictionRepository;

    private final MatchService matchService;

    @Autowired
    PredictionServiceImpl(PredictionRepository predictionRepository, MatchService matchService) {
        this.predictionRepository = predictionRepository;
        this.matchService = matchService;
    }

    @Override
    public void save(User user, PredictionForm predictionForm, Instant submittedAt) {
        for (PredictionDTO predictionDTO : predictionForm.getPredictions()) {
            Long matchId = predictionDTO.getMatchId();
            Integer homeTeamGoals = predictionDTO.getHomeTeamGoals();
            Integer awayTeamGoals = predictionDTO.getAwayTeamGoals();

            if (matchId == null || homeTeamGoals == null || awayTeamGoals == null) {
                logger.info("Ignoring incomplete prediction {} from {}", predictionDTO, user);
                continue;
            }

            Match match = matchService.findById(matchId).orElseThrow(() -> new RuntimeException("Ignoring " + predictionDTO + ", unable to find match for id " + matchId));

            Instant matchStart = match.getStart().toInstant();
            if (submittedAt.isAfter(matchStart)) {
                logger.info("Ignoring prediction {} from {}, submitted after match start", predictionDTO, user);
                continue;
            }

            Prediction prediction = predictionRepository.findOneByUserAndMatch(user, match)
                    .orElseGet(() -> new Prediction(user, match, null));

            MatchResult predictedResult = new MatchResult(homeTeamGoals, awayTeamGoals);
            prediction.setMatchResult(predictedResult);
            predictionRepository.save(prediction);
        }
    }

    @Override
    public List<Prediction> getPredictions(User user) {
        return predictionRepository.findAllByUser(user);
    }

    @Override
    public Optional<Prediction> getPredictionForMatch(User user, Match match) {
        return predictionRepository.findOneByUserAndMatch(user, match);
    }
}
