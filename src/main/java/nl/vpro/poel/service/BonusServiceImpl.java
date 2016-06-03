package nl.vpro.poel.service;

import lombok.extern.slf4j.Slf4j;
import nl.vpro.poel.domain.Bonus;
import nl.vpro.poel.dto.BonusForm;
import nl.vpro.poel.repository.BonusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BonusServiceImpl implements BonusService{

    private BonusRepository bonusRepository;

    @Autowired
    public BonusServiceImpl(BonusRepository bonusRepository) {
        this.bonusRepository = bonusRepository;
    }

    @Override
    public Optional<Bonus> findById(Long id) {
        return Optional.ofNullable(bonusRepository.findOne(id));
    }

    @Override
    public List<Bonus> findAll() {
        return bonusRepository.findAll();
    }

    @Override
    public List<Bonus> findAllCompleted() {
        return bonusRepository.findByAnswerNotNull();
    }

    @Override
    public List<Bonus> findAllUnfinished(Instant instant) {
        return bonusRepository.findByAnswerIsNullAndStartIsBefore(Date.from(instant));
    }

    @Override
    public List<Bonus> findBonusesToPredict(Instant instant) {
        return bonusRepository.findByAnswerIsNullAndStartIsAfter(Date.from(instant));
    }

    @Override
    @Transactional
    public void save(BonusForm bonusForm) {

    }
}
