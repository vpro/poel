package nl.vpro.poel.service;

import nl.vpro.poel.domain.Match;
import nl.vpro.poel.dto.MatchForm;
import nl.vpro.poel.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public void setMatches(MatchForm matchForm) {
        Set<Long> idsToRemove = findAll().stream().map(Match::getId).collect(Collectors.toSet());

        for (Match match : matchForm.getMatches()) {
            Long id = match.getId();
            if (id != null) {
                idsToRemove.remove(id);
            }
            matchRepository.save(match);
        }

        // Matches not included in the form are deleted from the repository
        idsToRemove.stream().forEach(matchRepository::delete);
    }
}
