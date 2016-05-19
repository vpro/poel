package nl.vpro.poel.service;

import nl.vpro.poel.domain.Match;
import nl.vpro.poel.domain.MatchResult;
import nl.vpro.poel.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;

        // Initialize default matches
        if (matchRepository.count() == 0) {

            Date now = new Date();
            Date lastWeek = Date.from(now.toInstant().minus(7, ChronoUnit.DAYS));
            Date nextWeek = Date.from(now.toInstant().plus(7, ChronoUnit.DAYS));

            List<Match> defaultMatches = Arrays.asList(

                    // Finished
                    new Match("Zwitserland", "Frankrijk", lastWeek, new MatchResult(3, 1)),
                    new Match("België", "Engeland", lastWeek, new MatchResult(5, 4)),

                    // Unfinished
                    new Match("Frankrijk", "Duitsland", now),
                    new Match("Spanje", "Engeland", now),

                    // Future
                    new Match("Portugal", "België", nextWeek),
                    new Match("Engeland", "Oostenrijk", nextWeek)
            );
            matchRepository.save(defaultMatches);
        }
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
