package nl.vpro.poel.repository;

import nl.vpro.poel.domain.BonusCategory;
import nl.vpro.poel.domain.BonusChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BonusChoiceRepository extends JpaRepository<BonusChoice, Long> {

    List<BonusChoice> findByCategoryOrderByValueAsc(BonusCategory category);
}
