package nl.vpro.poel.service;

import nl.vpro.poel.domain.User;
import nl.vpro.poel.domain.UserGroup;
import nl.vpro.poel.dto.RankingEntry;

import java.util.List;
import java.util.Optional;

public interface RankingService {

    Optional<RankingEntry<User>> getRankingEntry(User user);

    Optional<RankingEntry<UserGroup>> getRankingEntry(UserGroup userGroup);

    List<RankingEntry<User>> getUserRanking();

    List<RankingEntry<UserGroup>> getUserGroupRanking();
}
