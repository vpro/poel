package nl.vpro.poel.service;

import nl.vpro.poel.domain.User;
import nl.vpro.poel.domain.UserGroup;
import nl.vpro.poel.dto.RankingEntry;

import java.util.List;
import java.util.Optional;

public interface RankingService {

    Optional<RankingEntry<User, Integer>> getRankingEntry(User user);

    Optional<RankingEntry<UserGroup, Double>> getRankingEntry(UserGroup userGroup);

    List<RankingEntry<User, Integer>> getUserRanking();

    List<RankingEntry<UserGroup, Double>> getUserGroupRanking();
}
