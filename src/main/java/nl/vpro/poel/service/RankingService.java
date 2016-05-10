package nl.vpro.poel.service;

import nl.vpro.poel.domain.User;

import java.util.Optional;
import java.util.SortedMap;

interface RankingService {

    Optional<Integer> getRank(User user);

    // score,user TODO: create object for ranking?
    SortedMap<Integer, User> getOverallRanking();
}
