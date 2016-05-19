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
    public Optional<RankingEntry> getRankingEntry(User user) {
        for (RankingEntry rankingEntry : getRanking()) {
            if (rankingEntry.getUser().equals(user)) {
                return Optional.of(rankingEntry);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<RankingEntry> getRanking() {
        List<RankingEntry> ranking = new ArrayList<>();
        int rank = 1;
        for (Map.Entry<Integer, List<User>> entry : getUsersByScore().descendingMap().entrySet()) {
            List<User> users = entry.getValue();
            for (User user : users) {
                Integer score = entry.getKey();
                ranking.add(new RankingEntry(rank, user, score));
            }
            rank += users.size(); // Make sure if two users share rank 1, the next user has rank 3
        }
        return ranking;
    }

    private NavigableMap<Integer, List<User>> getUsersByScore() {
        NavigableMap<Integer, List<User>> usersByScore = new TreeMap<>();
        for (User user : userService.getAllUsers()) {
            Integer score = getScore(user);
            List<User> users = usersByScore.getOrDefault(score, new ArrayList<>());
            users.add(user);
            usersByScore.put(score, users);
        }
        return usersByScore;
    }

    private Integer getScore(User user) {
        return predictionService.getPredictions(user).stream()
                .collect(Collectors.summingInt(Prediction::getScore));
    }
}
