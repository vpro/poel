package nl.vpro.poel.service;

import nl.vpro.poel.domain.User;
import nl.vpro.poel.domain.UserGroup;
import nl.vpro.poel.dto.UserGroupRankingEntry;
import nl.vpro.poel.dto.UserRankingEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RankingServiceImpl implements RankingService {

    private final UserService userService;
    private final UserGroupService userGroupService;
    private final ScoreService scoreService;

    @Autowired
    RankingServiceImpl(UserService userService, UserGroupService userGroupService, ScoreService scoreService) {
        this.userService = userService;
        this.userGroupService = userGroupService;
        this.scoreService = scoreService;
    }

    @Override
    @Cacheable(cacheNames = "userRanking", sync = true)
    public Optional<UserRankingEntry> getRankingEntry(User user) {
        for (UserRankingEntry rankingEntry : getUserRanking()) {
            if (rankingEntry.getUser().equals(user)) {
                return Optional.of(rankingEntry);
            }
        }
        return Optional.empty();
    }

    @Override
    @Cacheable(cacheNames = "userRanking", sync = true)
    public List<UserRankingEntry> getUserRanking() {
        List<UserRankingEntry> ranking = new ArrayList<>();
        int rank = 1;
        for (Map.Entry<Integer, List<User>> entry : getUsersByScore().descendingMap().entrySet()) {
            List<User> users = entry.getValue();
            for (User user : users) {
                Integer score = entry.getKey();
                ranking.add(new UserRankingEntry(rank, user, score));
            }
            rank += users.size(); // Make sure if two users share rank 1, the next user has rank 3
        }
        return ranking;
    }

    @Override
    @Cacheable(cacheNames = "userGroupRanking", sync = true)
    public Optional<UserGroupRankingEntry> getRankingEntry(UserGroup userGroup) {
        for (UserGroupRankingEntry rankingEntry : getUserGroupRanking()) {
            if (rankingEntry.getUserGroup().equals(userGroup)) {
                return Optional.of(rankingEntry);
            }
        }
        return Optional.empty();
    }

    @Override
    @Cacheable(cacheNames = "userGroupRanking", sync = true)
    public List<UserGroupRankingEntry> getUserGroupRanking() {
        List<UserGroupRankingEntry> ranking = new ArrayList<>();
        int rank = 1;
        for (Map.Entry<Double, List<UserGroup>> entry : getUserGroupsByAverageUserScore().descendingMap().entrySet()) {
            List<UserGroup> userGroups = entry.getValue();
            for (UserGroup userGroup : userGroups) {
                Double score = entry.getKey();
                ranking.add(new UserGroupRankingEntry(rank, userGroup, score));
            }
            rank += userGroups.size(); // Make sure if two groups share rank 1, the next group has rank 3
        }
        return ranking;
    }

    private NavigableMap<Double, List<UserGroup>> getUserGroupsByAverageUserScore() {
        NavigableMap<Double, List<UserGroup>> userGroupsByScore = new TreeMap<>();
        for (UserGroup userGroup : userGroupService.findAll()) {
            Double groupScore = scoreService.getAverageScore(userGroup);
            List<UserGroup> userGroups = userGroupsByScore.getOrDefault(groupScore, new ArrayList<>());
            userGroups.add(userGroup);
            userGroupsByScore.put(groupScore, userGroups);
        }
        return userGroupsByScore;
    }

    private NavigableMap<Integer, List<User>> getUsersByScore() {
        NavigableMap<Integer, List<User>> usersByScore = new TreeMap<>();
        for (User user : userService.getAllUsers()) {
            Integer score = scoreService.getScore(user);
            List<User> users = usersByScore.getOrDefault(score, new ArrayList<>());
            users.add(user);
            usersByScore.put(score, users);
        }
        return usersByScore;
    }
}
