package nl.vpro.poel.service;

import lombok.extern.slf4j.Slf4j;
import nl.vpro.poel.domain.Match;
import nl.vpro.poel.domain.Round;
import nl.vpro.poel.domain.MatchResult;
import nl.vpro.poel.dto.MatchDTO;
import nl.vpro.poel.dto.MatchForm;
import nl.vpro.poel.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final RoundService roundService;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository, RoundService roundService) {
        this.matchRepository = matchRepository;
        this.roundService = roundService;
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
                log.warn("Ignoring match update {}, because it is incomplete", matchDTO);
                continue;
            }

            Match match;
            if (id == null) {
                match = new Match();
            } else {
                match = matchRepository.findOne(id);

                if (match == null) {
                    log.warn("Ignoring match update {}, because no match exists for this id", matchDTO);
                    continue;
                }
            }

            match.setHomeTeam(homeTeam);
            match.setAwayTeam(awayTeam);
            match.setStart(start);

            Integer homeTeamGoals = matchDTO.getHomeTeamGoals();
            Integer awayTeamGoals = matchDTO.getAwayTeamGoals();

            if (homeTeamGoals != null && awayTeamGoals != null) {
                // Valid match result
                match.setMatchResult(new MatchResult(homeTeamGoals, awayTeamGoals));
            } else if (homeTeamGoals == null && awayTeamGoals == null) {
                // No match result
                match.setMatchResult(null);
            } else {
                // Invalid match result, ignore so any previously saved match result is preserved
                log.warn("Ignoring incomplete match result from match update {}", matchDTO);
            }

            Long roundId = matchDTO.getRoundId();
            if (roundId == null) {
                match.setRound(null);
            } else {
                Optional<Round> round = roundService.findById(roundId);
                if (round.isPresent()) {
                    match.setRound(round.get());
                } else {
                    log.warn("Ignoring match update {}, because no round exists for this id", matchDTO);
                    continue;
                }
            }

            matchRepository.save(match);
        }

        // Matches not included in the form are deleted from the repository
        idsToRemove.stream().forEach(matchRepository::delete);
    }
}
