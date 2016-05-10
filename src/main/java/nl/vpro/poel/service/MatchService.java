package nl.vpro.poel.service;

import nl.vpro.poel.domain.Match;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public interface MatchService {

    List<Match> getValidMatches(Instant instant);
}
