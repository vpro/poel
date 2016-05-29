package nl.vpro.poel.service;

import lombok.extern.slf4j.Slf4j;
import nl.vpro.poel.domain.Match;
import nl.vpro.poel.domain.MatchResult;
import nl.vpro.poel.domain.Prediction;
import nl.vpro.poel.domain.User;
import nl.vpro.poel.dto.PredictionDTO;
import nl.vpro.poel.dto.PredictionForm;
import nl.vpro.poel.exception.MultiplierException;
import nl.vpro.poel.repository.PredictionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PredictionServiceImpl implements PredictionService {

    private final PredictionRepository predictionRepository;

    private final MatchService matchService;

    @Value("${poel.maxMultipliers}")
    private int maxMultipliers;

    @Autowired
    PredictionServiceImpl(PredictionRepository predictionRepository, MatchService matchService) {
        this.predictionRepository = predictionRepository;
        this.matchService = matchService;
    }

    /**
     * Save the predictions from a form submission.
     *
     * @param user The user that submitted the predictions
     * @param predictionForm The form containing the submitted predictions
     * @param submittedAt The moment the predictions were submitted
     * @return The number of updated predictions
     */
    @Override
    @Transactional(rollbackFor = MultiplierException.class)
    public int save(User user, PredictionForm predictionForm, Instant submittedAt) throws MultiplierException {
        int updates = 0;
        for (PredictionDTO predictionDTO : predictionForm.getPredictions()) {

            Long matchId = predictionDTO.getMatchId();
            Integer homeTeamGoals = predictionDTO.getHomeTeamGoals();
            Integer awayTeamGoals = predictionDTO.getAwayTeamGoals();

            if (matchId == null || homeTeamGoals == null || awayTeamGoals == null) {
                log.info("Ignoring incomplete prediction {} from {}", predictionDTO, user);
                continue;
            }

            Match match = matchService.findById(matchId).orElseThrow(() -> new RuntimeException("Ignoring " + predictionDTO + ", unable to find match for id " + matchId));

            Instant matchStart = match.getStart().toInstant();
            if (submittedAt.isAfter(matchStart)) {
                log.info("Ignoring prediction {} from {}, submitted after match start", predictionDTO, user);
                continue;
            }

            Prediction prediction = null;

            Long predictionId = predictionDTO.getPredictionId();
            if (predictionId != null) {
                prediction = predictionRepository.findOne(predictionId);

                if (prediction == null) {
                    log.info("Ignore prediction {} from {}, because no prediction exists for this id", predictionDTO, user);
                    continue;
                }
            } else {
                prediction = new Prediction(user, match, null);
            }

            MatchResult predictedResult = new MatchResult(homeTeamGoals, awayTeamGoals);
            prediction.setMatchResult(predictedResult);

            prediction.setMultiplier(predictionDTO.isMultiplier());

            predictionRepository.save(prediction);
            updates++;
        }

        int multiplierCount = predictionRepository.countByUserAndMultiplierIsTrue(user);
        if (multiplierCount > maxMultipliers) {
            log.warn("Ignoring prediction form for {}, because processing it would mean this user had {} multipliers, but only {} are allowed", user, multiplierCount, maxMultipliers);
            throw new MultiplierException("De wijzigingen zijn niet opgeslagen, want je mag niet meer dan " + maxMultipliers + " joker" + (maxMultipliers != 1 ? "s" : "") + " gebruiken.");
        }

        return updates;
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
