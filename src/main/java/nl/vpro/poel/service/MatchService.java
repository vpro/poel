package nl.vpro.poel.service;

import nl.vpro.poel.domain.Match;
import nl.vpro.poel.dto.MatchForm;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public interface MatchService {

    Optional<Match> findById(Long id);

    List<Match> findAll();

    List<Match> findAllCompleted();

    List<Match> findAllUnfinished(Instant instant);

    List<Match> findMatchesToPredict(Instant instant);

    void setMatches(MatchForm matchForm);
}
