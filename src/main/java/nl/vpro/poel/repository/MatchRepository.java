package nl.vpro.poel.repository;

import nl.vpro.poel.domain.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findByMatchResultIsNull();

    List<Match> findByMatchResultNotNull();

    List<Match> findByMatchResultIsNullAndStartIsAfter(Date instant);

    List<Match> findByMatchResultIsNullAndStartIsBefore(Date instant);
}
