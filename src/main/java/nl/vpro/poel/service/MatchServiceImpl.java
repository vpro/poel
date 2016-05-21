package nl.vpro.poel.service;

import nl.vpro.poel.domain.Match;
import nl.vpro.poel.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public Optional<Match> findById(Long id) {
        return Optional.ofNullable(matchRepository.getOne(id));
    }

    @Override
    public List<Match> findAll() {
        return matchRepository.findAll();
    }

    @Override
    public List<Match> findAllCompleted() {
        return matchRepository.findByMatchResultNotNull();
    }

    @Override
    public List<Match> findAllUnfinished(Instant instant) {
        return matchRepository.findByMatchResultIsNullAndStartIsBefore(Date.from(instant));
    }

    @Override
    public List<Match> findMatchesToPredict(Instant instant) {
        return matchRepository.findByMatchResultIsNullAndStartIsAfter(Date.from(instant));
    }
}
