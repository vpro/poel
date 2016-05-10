package nl.vpro.poel.service;

import nl.vpro.poel.domain.Outcome;
import nl.vpro.poel.domain.Match;

import java.util.Optional;

public interface OutcomeService {

    Optional<Outcome> getOutcome(Match match);
}
