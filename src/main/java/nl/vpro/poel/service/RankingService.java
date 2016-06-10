package nl.vpro.poel.service;

import nl.vpro.poel.domain.User;
import nl.vpro.poel.domain.UserGroup;
import nl.vpro.poel.dto.UserGroupRankingEntry;
import nl.vpro.poel.dto.UserRankingEntry;

import java.util.List;
import java.util.Optional;

public interface RankingService {

    Optional<UserRankingEntry> getRankingEntry(User user);

    Optional<UserGroupRankingEntry> getRankingEntry(UserGroup userGroup);

    List<UserRankingEntry> getUserRanking();

    List<UserGroupRankingEntry> getUserGroupRanking();
}
