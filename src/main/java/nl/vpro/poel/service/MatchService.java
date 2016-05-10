package nl.vpro.poel.service;

import nl.vpro.poel.domain.Match;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;

@Service
public interface MatchService {

    Collection<Match> getValidMatches(Instant instant);
}
