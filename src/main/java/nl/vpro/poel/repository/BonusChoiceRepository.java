package nl.vpro.poel.repository;

import nl.vpro.poel.domain.BonusCategory;
import nl.vpro.poel.domain.BonusChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BonusChoiceRepository extends JpaRepository<BonusChoice, Long> {

    List<BonusChoice> findByCategory(BonusCategory category);

    Optional<BonusChoice> findByValue(String value);
}
