package nl.vpro.poel.service;

import nl.vpro.poel.domain.User;
import nl.vpro.poel.dto.RankingEntry;

import java.util.List;
import java.util.Optional;

public interface RankingService {

    Optional<RankingEntry> getRankingEntry(User user);

    List<RankingEntry> getRanking();
}
