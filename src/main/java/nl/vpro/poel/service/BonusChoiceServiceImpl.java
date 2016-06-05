package nl.vpro.poel.service;

import lombok.extern.slf4j.Slf4j;
import nl.vpro.poel.domain.BonusCategory;
import nl.vpro.poel.domain.BonusChoice;
import nl.vpro.poel.dto.BonusChoiceDTO;
import nl.vpro.poel.dto.BonusChoiceForm;
import nl.vpro.poel.repository.BonusChoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BonusChoiceServiceImpl implements BonusChoiceService  {

    private final BonusChoiceRepository bonusChoiceRepository;

    @Autowired
    public BonusChoiceServiceImpl(BonusChoiceRepository bonusChoiceRepository) {
        this.bonusChoiceRepository = bonusChoiceRepository;
    }

    @Override
    public Optional<BonusChoice> findById(Long id) {
        return Optional.ofNullable(bonusChoiceRepository.findOne(id));
    }

    @Override
    public List<BonusChoice> choicesFor(BonusCategory category) {
        return bonusChoiceRepository.findByCategoryOrderByValueAsc(category);
    }

    @Override
    public List<BonusChoice> findAllOrderedByCategoryAndValue() {
        return bonusChoiceRepository.findAll().stream()
                .sorted(Comparator.nullsLast(
                        Comparator.comparing(BonusChoice::getCategory)
                        .thenComparing(BonusChoice::getValue)
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void setChoices(BonusChoiceForm bonusChoiceForm) {
        Set<Long> idsToRemove = findAllOrderedByCategoryAndValue().stream().map(BonusChoice::getId).collect(Collectors.toSet());

        for (BonusChoiceDTO bonusChoiceDTO : bonusChoiceForm.getChoices()) {
            Long id = bonusChoiceDTO.getId();

            idsToRemove.remove(id);

            String value = bonusChoiceDTO.getValue();

            if (value == null) {
                log.warn("Ignoring bonus choice update {}, because it has no name", bonusChoiceDTO);
                continue;
            }

            BonusChoice bonusChoice;
            if (id == null) {
                bonusChoice = new BonusChoice();
            } else {
                bonusChoice = bonusChoiceRepository.findOne(id);

                if (bonusChoice == null) {
                    log.warn("Ignoring bonus choice update {}, because no choice exists for this id", bonusChoiceDTO);
                    continue;
                }
            }

            BonusCategory category = bonusChoiceDTO.getCategory();
            bonusChoice.setCategory(category);

            bonusChoice.setValue(value);

            bonusChoiceRepository.save(bonusChoice);
        }

        // Bonus choices not included in the form will be deleted
        // TODO: delete should be cascading...yaadieyadieyah
        idsToRemove.stream().forEach(bonusChoiceRepository::delete);
    }
}
