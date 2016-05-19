package nl.vpro.poel.service;

import nl.vpro.poel.domain.Prediction;
import nl.vpro.poel.domain.User;
import nl.vpro.poel.dto.RankingEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RankingServiceImpl implements RankingService {

    private final UserService userService;

    private final PredictionService predictionService;

    @Autowired
    RankingServiceImpl(UserService userService, PredictionService predictionService) {
        this.userService = userService;
        this.predictionService = predictionService;
    }

    @Override
    public Optional<Integer> getRank(User user) {
        int rank = 1;
        for (RankingEntry rankingEntry : getRanking()) {
            if (rankingEntry.getUser().equals(user)) {
                return Optional.of(rank);
            }
            rank++;
        }
        return Optional.empty();
    }

    @Override
    public List<RankingEntry> getRanking() {
        return userService.getAllUsers().stream()
                .map(user -> new RankingEntry(user, predictionService.getPredictions(user).stream()
                        .collect(Collectors.summingInt(Prediction::getScore))))
                .sorted(Comparator.comparingInt(RankingEntry::getScore))
                .collect(Collectors.toList());
    }
}
