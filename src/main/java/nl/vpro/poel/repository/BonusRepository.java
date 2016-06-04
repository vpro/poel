package nl.vpro.poel.repository;

import nl.vpro.poel.domain.Bonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BonusRepository extends JpaRepository<Bonus, Long>{

    List<Bonus> findByAnswerIsNull();

    List<Bonus> findByAnswerNotNull();

    List<Bonus> findByAnswerIsNullAndStartIsAfter(Date instant);

    List<Bonus> findByAnswerIsNullAndStartIsBefore(Date instant);
}
