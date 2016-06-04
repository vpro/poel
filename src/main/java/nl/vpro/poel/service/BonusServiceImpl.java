package nl.vpro.poel.service;

import lombok.extern.slf4j.Slf4j;
import nl.vpro.poel.domain.Bonus;
import nl.vpro.poel.domain.BonusCategory;
import nl.vpro.poel.domain.BonusChoice;
import nl.vpro.poel.domain.MatchDay;
import nl.vpro.poel.dto.BonusDTO;
import nl.vpro.poel.dto.BonusForm;
import nl.vpro.poel.repository.BonusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BonusServiceImpl implements BonusService{

    private final BonusRepository bonusRepository;
    private final BonusChoiceService bonusChoiceService;
    private final MatchDayService matchDayService;

    @Autowired
    public BonusServiceImpl(BonusRepository bonusRepository, BonusChoiceService bonusChoiceService, MatchDayService matchDayService) {
        this.bonusRepository = bonusRepository;
        this.bonusChoiceService = bonusChoiceService;
        this.matchDayService = matchDayService;
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
        Set<Long> idsToRemove = findAll().stream().map(Bonus::getId).collect(Collectors.toSet());

        for (BonusDTO bonusDTO : bonusForm.getBonuses()) {
            Long id = bonusDTO.getId();

            idsToRemove.remove(id);

            String question = bonusDTO.getQuestion();
            Date start = bonusDTO.getStart();

            if ( question == null || start == null ) {
                log.warn("Ignoring bonus update {}, because it is incomplete", bonusDTO);
                continue;
            }

            Bonus bonus;
            if (id == null) {
                bonus = new Bonus();
            } else {
                bonus = bonusRepository.findOne(id);

                if (bonus == null) {
                    log.warn("Ignoring bonus update {}, because no bonus exists fo this id", bonusDTO);
                    continue;
                }
            }

            bonus.setQuestion(question);
            bonus.setStart(start);

            Integer score = bonusDTO.getScore();

            if ( score != null ) {
                bonus.setScore(score);
            } else {
                bonus.setScore(3);
            }

            BonusCategory category = bonusDTO.getCategory();
            bonus.setCategory(category);

            Long answerId = bonusDTO.getAnswerId();
            if ( answerId == null ) {
                bonus.setAnswer(null);
            } else {
                Optional<BonusChoice> answer = bonusChoiceService.findById(answerId);
                if (answer.isPresent()) {
                    bonus.setAnswer(answer.get());
                } else {
                    log.warn("Ignoring bonus update {}, because no choice exists for this id", bonusDTO);
                    continue;
                }
            }

            Long matchDayId = bonusDTO.getMatchDayId();
            if (matchDayId == null) {
                bonus.setMatchDay(null);
            } else {
                Optional<MatchDay> matchDay = matchDayService.findById(matchDayId);
                if (matchDay.isPresent()) {
                    bonus.setMatchDay(matchDay.get());
                } else {
                    log.warn("Ignoring bonus update {}, because no match day exists for this id", bonusDTO);
                    continue;
                }
            }

            bonusRepository.save(bonus);
        }

        // Bonuses not included in the form are deleted from the repository
        idsToRemove.stream().forEach(bonusRepository::delete);
    }
}
