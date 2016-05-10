package nl.vpro.poel.repository;

import nl.vpro.poel.domain.Match;
import nl.vpro.poel.domain.Outcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OutcomeRepository extends JpaRepository<Outcome, Long> {

    Optional<Outcome> findOneByMatch(Match match);
}
