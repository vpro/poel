package nl.vpro.poel.service;

import nl.vpro.poel.domain.Match;
import nl.vpro.poel.domain.MatchResult;
import nl.vpro.poel.dto.MatchDTO;
import nl.vpro.poel.dto.MatchForm;
import nl.vpro.poel.repository.MatchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl implements MatchService {

    private static final Logger logger = LoggerFactory.getLogger(MatchServiceImpl.class);

    private final MatchRepository matchRepository;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public Optional<Match> findById(Long id) {
        return Optional.ofNullable(matchRepository.findOne(id));
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
    @Transactional
    public void save(MatchForm matchForm) {
        Set<Long> idsToRemove = findAll().stream().map(Match::getId).collect(Collectors.toSet());

        for (MatchDTO matchDTO : matchForm.getMatches()) {
            Long id = matchDTO.getId();

            idsToRemove.remove(id); // Don't delete matches for which we receive an update

            String homeTeam = matchDTO.getHomeTeam();
            String awayTeam = matchDTO.getAwayTeam();
            Date start = matchDTO.getStart();

            if (homeTeam == null || awayTeam == null || start == null) {
                logger.warn("Ignoring match update {}, because it is incomplete");
                continue;
            }

            Match match;
            if (id == null) {
                match = new Match();
            } else {
                match = matchRepository.findOne(id);

                if (match == null) {
                    logger.warn("Ignoring match update {}, because no match exists for this id", matchDTO);
                    continue;
                }
            }

            match.setHomeTeam(homeTeam);
            match.setAwayTeam(awayTeam);
            match.setStart(start);

            // Set match result if present
            Integer homeTeamGoals = matchDTO.getHomeTeamGoals();
            Integer awayTeamGoals = matchDTO.getAwayTeamGoals();
            if (homeTeamGoals != null && awayTeamGoals != null) {
                match.setMatchResult(new MatchResult(homeTeamGoals, awayTeamGoals));
            }

            matchRepository.save(match);
        }

        // Matches not included in the form are deleted from the repository
        idsToRemove.stream().forEach(matchRepository::delete);
    }
}
