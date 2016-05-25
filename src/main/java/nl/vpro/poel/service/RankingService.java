package nl.vpro.poel.service;

import nl.vpro.poel.domain.User;
import nl.vpro.poel.domain.UserGroup;
import nl.vpro.poel.dto.RankingEntry;
import nl.vpro.poel.dto.UserGroupRankingEntry;

import java.util.List;
import java.util.Optional;

public interface RankingService {

    Optional<RankingEntry> getRankingEntry(User user);

    Optional<UserGroupRankingEntry> getUserGroupRankingEntry(UserGroup userGroup);

    List<RankingEntry> getRanking();

    List<UserGroupRankingEntry> getUserGroupRanking();
}
