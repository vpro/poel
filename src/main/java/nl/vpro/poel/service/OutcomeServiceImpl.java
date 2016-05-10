package nl.vpro.poel.service;

import nl.vpro.poel.domain.Match;
import nl.vpro.poel.domain.Outcome;
import nl.vpro.poel.repository.OutcomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OutcomeServiceImpl implements OutcomeService {

    private final OutcomeRepository outcomeRepository;

    @Autowired
    OutcomeServiceImpl(OutcomeRepository outcomeRepository) {
        this.outcomeRepository = outcomeRepository;
    }

    @Override
    public Optional<Outcome> getOutcome(Match match) {
        return outcomeRepository.findOneByMatch(match);
    }
}
