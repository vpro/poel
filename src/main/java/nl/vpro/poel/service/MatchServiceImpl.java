package nl.vpro.poel.service;

import nl.vpro.poel.domain.Match;
import nl.vpro.poel.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
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
                    new Match(teamService.getTeamById(1L).get(), teamService.getTeamById(2L).get(), null, Instant.now()),
                    new Match(teamService.getTeamById(3L).get(), teamService.getTeamById(4L).get(), null, Instant.now()),
                    new Match(teamService.getTeamById(5L).get(), teamService.getTeamById(6L).get(), Instant.now(), null)
            );
            matchRepository.save(defaultMatches);
        }
    }

    @Override
    public List<Match> getValidMatches(Instant instant) {
        return matchRepository.findAll().stream()
                .filter(match -> match.canBePredicatedAt(instant))
                .collect(Collectors.toList());
    }
}
