package nl.vpro.poel.service;

import lombok.extern.slf4j.Slf4j;
import nl.vpro.poel.domain.*;
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
    private final BonusService bonusService;
    private final BonusChoiceService bonusChoiceService;

    @Value("${poel.maxMultipliers}")
    private int maxMultipliers;

    @Autowired
    PredictionServiceImpl(PredictionRepository predictionRepository, MatchService matchService, BonusService bonusService, BonusChoiceService bonusChoiceService) {
        this.predictionRepository = predictionRepository;
        this.matchService = matchService;
        this.bonusService = bonusService;
        this.bonusChoiceService = bonusChoiceService;
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

            Prediction prediction = null;
            Long matchId = predictionDTO.getMatchId();
            Long bonusId = predictionDTO.getBonusId();

            if ( matchId != null ) {

                Integer homeTeamGoals = predictionDTO.getHomeTeamGoals();
                Integer awayTeamGoals = predictionDTO.getAwayTeamGoals();

                if ( homeTeamGoals == null || awayTeamGoals == null) {
                    log.info("Ignoring incomplete prediction {} from {}", predictionDTO, user);
                    continue;
                }

                Match match = matchService.findById(matchId).orElseThrow(() -> new RuntimeException("Ignoring " + predictionDTO + ", unable to find match for id " + matchId));

                Instant matchStart = match.getStart().toInstant();
                if (submittedAt.isAfter(matchStart)) {
                    log.info("Ignoring prediction {} from {}, submitted after match start", predictionDTO, user);
                    continue;
                }

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

            } else if ( bonusId != null ) {

                Long answerId = predictionDTO.getAnswerId();
                Optional<BonusChoice> answer;

                if ( answerId == null) {
                    log.info("Ignoring incomplete prediction {} from {}", predictionDTO, user);
                    continue;
                }

                Bonus bonus = bonusService.findById(bonusId).orElseThrow(() -> new RuntimeException("Ignoring " + predictionDTO + ", unable to find bonus for id " + bonusId));

                Instant bonusStart = bonus.getStart().toInstant();
                if (submittedAt.isAfter(bonusStart)) {
                    log.info("Ignoring prediction {} from {}, submitted after bonus deadline", predictionDTO, user);
                    continue;
                }

                Long predictionId = predictionDTO.getPredictionId();
                if (predictionId != null) {
                    prediction = predictionRepository.findOne(predictionId);

                    if (prediction == null) {
                        log.info("Ignore prediction {} from {}, because no prediction exists for this id", predictionDTO, user);
                        continue;
                    }
                } else {
                    prediction = new Prediction(user, bonus, null);
                }

                answer = bonusChoiceService.findById(answerId);
                if (answer.isPresent()) {
                    prediction.setAnswer(answer.get());
                } else {
                    log.warn("Ignoring incomplete prediction {}, because no answer exists for this id", predictionDTO);
                    continue;
                }


            } else {
                log.info("Ignoring incomplete prediction {} from {}", predictionDTO, user);
                continue;
            }

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
        return getPredictionForSubject(user, match);
    }

    @Override
    public Optional<Prediction> getPredictionForBonus(User user, Bonus bonus) {
        return getPredictionForSubject(user, bonus);
    }

    @Override
    public Optional<Prediction> getPredictionForSubject(User user, Match match) {
        return predictionRepository.findOneByUserAndMatch(user, match);
    }

    @Override
    public Optional<Prediction> getPredictionForSubject(User user, Bonus bonus) {
        return predictionRepository.findOneByUserAndBonus(user, bonus);
    }
}
