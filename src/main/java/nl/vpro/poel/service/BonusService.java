package nl.vpro.poel.service;

import nl.vpro.poel.domain.Bonus;
import nl.vpro.poel.dto.BonusForm;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface BonusService {

    Optional<Bonus> findById(Long id);

    List<Bonus> findAll();

    List<Bonus> findAllCompleted();

    List<Bonus> findAllUnfinished(Instant instant);

    List<Bonus> findBonusesToPredict(Instant instant);

    void save(BonusForm bonusForm);
}
