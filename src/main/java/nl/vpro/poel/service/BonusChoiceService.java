package nl.vpro.poel.service;

import nl.vpro.poel.domain.BonusCategory;
import nl.vpro.poel.domain.BonusChoice;
import nl.vpro.poel.dto.BonusChoiceForm;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BonusChoiceService {

    Optional<BonusChoice> findById(Long id);

    List<BonusChoice> choicesFor(BonusCategory category);

    List<BonusChoice> findAll();

    void setChoices(BonusChoiceForm bonusChoiceForm);
}
