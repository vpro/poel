package nl.vpro.poel.service;

import lombok.extern.slf4j.Slf4j;
import nl.vpro.poel.domain.MatchDay;
import nl.vpro.poel.dto.MatchDayDTO;
import nl.vpro.poel.dto.MatchDayForm;
import nl.vpro.poel.repository.MatchDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MatchDayServiceImpl implements MatchDayService {

    private final MatchDayRepository matchDayRepository;

    @Autowired
    public MatchDayServiceImpl(MatchDayRepository matchDayRepository){
        this.matchDayRepository = matchDayRepository;
    }

    @Override
    public Optional<MatchDay> findById(Long id) {
        return Optional.ofNullable(matchDayRepository.findOne(id));
    }

    @Override
    public Optional<MatchDay> findByName(String name) {
        return Optional.ofNullable(matchDayRepository.findByName(name));
    }

    @Override
    public List<MatchDay> findAll() {
        return matchDayRepository.findAll();
    }

    @Override
    public void setMatchDays(MatchDayForm matchDayForm) {
        Set<Long> idsToRemove = findAll().stream().map(MatchDay::getId).collect(Collectors.toSet());

        for (MatchDayDTO matchDayDTO : matchDayForm.getMatchDays()) {
            Long id = matchDayDTO.getId();

            idsToRemove.remove(id);

            String name = matchDayDTO.getName();

            if (name == null) {
                log.warn("Ignoring match day update {}, because it has no name", matchDayDTO);
                continue;
            }

            MatchDay matchDay;
            if (id == null) {
                matchDay = new MatchDay();
            } else {
                matchDay = matchDayRepository.findOne(id);

                if (matchDay == null) {
                    log.warn("Ignoring match day update {}, because no match day exists for this id", matchDayDTO);
                    continue;
                }
            }

            matchDay.setName(name);

            matchDayRepository.save(matchDay);
        }

        // Match days not included in the form will be deleted
        // TODO: delete should be cascading. For now detach them from Matches first before deleting.
        idsToRemove.stream().forEach(matchDayRepository::delete);
    }
}
