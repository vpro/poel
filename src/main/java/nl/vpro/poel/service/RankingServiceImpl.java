package nl.vpro.poel.service;

import nl.vpro.poel.domain.Prediction;
import nl.vpro.poel.domain.User;
import nl.vpro.poel.domain.UserGroup;
import nl.vpro.poel.dto.RankingEntry;
import nl.vpro.poel.dto.UserGroupRankingEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public Optional<UserGroupRankingEntry> getUserGroupRankingEntry(UserGroup userGroup) {
        for (UserGroupRankingEntry userGroupRankingEntry : getUserGroupRanking()) {
            if (userGroupRankingEntry.getUserGroup().equals(userGroup)) {
                return Optional.of(userGroupRankingEntry);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<UserGroupRankingEntry> getUserGroupRanking() {
        List<UserGroupRankingEntry> ranking = new ArrayList<>();
        int rank = 1;
        for (Map.Entry<Integer, List<UserGroup>> entry : getUserGroupsByScore().descendingMap().entrySet()) {
            List<UserGroup> userGroups = entry.getValue();
            for (UserGroup userGroup : userGroups) {
                Integer score = entry.getKey();
                ranking.add(new UserGroupRankingEntry(rank, userGroup, score));
            }
            rank += userGroups.size(); // Make sure if two groups share rank 1, the next group has rank 3
        }
        return ranking;
    }

    private NavigableMap<Integer, List<UserGroup>> getUserGroupsByScore() {
        NavigableMap<Integer, List<UserGroup>> userGroupsByScore = new TreeMap<>();
        for (UserGroup userGroup : userGroupService.findAll()) {

            Integer groupScore = 0;

            for (User user : userService.getAllUsersForUserGroup(userGroup)) {
                groupScore += getScore(user);
            }

            List<UserGroup> userGroups = userGroupsByScore.getOrDefault(groupScore, new ArrayList<>());
            userGroups.add(userGroup);
            userGroupsByScore.put( groupScore, userGroups );
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
