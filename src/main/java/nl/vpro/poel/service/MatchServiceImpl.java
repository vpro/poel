package nl.vpro.poel.service;

import nl.vpro.poel.domain.Match;
import nl.vpro.poel.domain.MatchResult;
import nl.vpro.poel.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository, TeamService teamService) {
        this.matchRepository = matchRepository;

        // Initialize default matches
        if (matchRepository.count() == 0) {
            List<Match> defaultMatches = Arrays.asList(
                    new Match(teamService.getTeamByName("Frankrijk").get(), teamService.getTeamByName("Duitsland").get(), null, Instant.now()),
                    new Match(teamService.getTeamByName("Spanje").get(), teamService.getTeamByName("Engeland").get(), null, Instant.now()),
                    new Match(teamService.getTeamByName("Portugal").get(), teamService.getTeamByName("België").get(), Instant.now(), null),
                    new Match(teamService.getTeamByName("België").get(), teamService.getTeamByName("Engeland").get(), new MatchResult(5,4))
            );
            matchRepository.save(defaultMatches);
        }
    }

    @Override
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    @Override
    public List<Match> getAllCompletedMatches() {
        return matchRepository.findByMatchResultNotNull();
    }

    @Override
    public List<Match> getAllUnfinishedMatches() {
        return matchRepository.findByMatchResultIsNull();
    }

    @Override
    public List<Match> getMatchesToPredict(Instant instant) {
        return matchRepository.findAll().stream()
                .filter(match -> match.canBePredictedAt(instant))
                .collect(Collectors.toList());
    }

    @Override
    public List<Match> getInvalidMatches(Instant instant) {
        return matchRepository.findAll().stream()
                .filter(match -> !match.canBePredictedAt(instant))
                .collect(Collectors.toList());
    }
}
