package nl.vpro.poel.repository;

import nl.vpro.poel.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
