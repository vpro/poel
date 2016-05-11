package nl.vpro.poel.repository;

import nl.vpro.poel.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {

    Optional<Team> findOneByName(String name);
}
