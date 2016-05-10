package nl.vpro.poel.service;

import nl.vpro.poel.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

// TODO: Cache this stuff?
@Service
public class RankingServiceImpl implements RankingService {

    private final UserService userService;

    private final ScoreService scoreService;

    @Autowired
    RankingServiceImpl(UserService userService, ScoreService scoreService) {
        this.userService = userService;
        this.scoreService = scoreService;
    }

    @Override
    public Optional<Integer> getRank(User user) {
        // TODO: Find an easier way to find the rank in the overall ranking
        int rank = 1;
        for (Map.Entry<Integer, User> rankingEntry : getOverallRanking().entrySet()) {
            if (rankingEntry.getValue().equals(user)) {
                return Optional.of(rank);
            }
            rank++;
        }
        return Optional.empty();
    }

    @Override
    public SortedMap<Integer, User> getOverallRanking() {
        return userService.getAllUsers().stream()
                .collect(Collectors.toMap(
                        scoreService::getScore,
                        Function.identity(),
                        (k, v) -> { throw new RuntimeException(String.format("Duplicate key %s", k)); },
                        TreeMap::new));
    }
}
