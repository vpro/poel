package nl.vpro.poel.repository;


import nl.vpro.poel.domain.MatchDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchDayRepository extends JpaRepository<MatchDay, Long>{

    MatchDay findByName(String name);
}
