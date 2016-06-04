package nl.vpro.poel.service;

import lombok.extern.slf4j.Slf4j;
import nl.vpro.poel.domain.Round;
import nl.vpro.poel.dto.RoundDTO;
import nl.vpro.poel.dto.RoundsForm;
import nl.vpro.poel.repository.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RoundServiceImpl implements RoundService {

    private final RoundRepository roundRepository;

    @Autowired
    public RoundServiceImpl(RoundRepository roundRepository){
        this.roundRepository = roundRepository;
    }

    @Override
    public Optional<Round> findById(Long id) {
        return Optional.ofNullable(roundRepository.findOne(id));
    }

    @Override
    public Optional<Round> findByName(String name) {
        return roundRepository.findByName(name);
    }

    @Override
    public List<Round> findAll() {
        return roundRepository.findAll();
    }

    @Override
    public void setRounds(RoundsForm roundsForm) {
        Set<Long> idsToRemove = findAll().stream().map(Round::getId).collect(Collectors.toSet());

        for (RoundDTO roundDTO : roundsForm.getRounds()) {
            Long id = roundDTO.getId();

            idsToRemove.remove(id);

            String name = roundDTO.getName();

            if (name == null) {
                log.warn("Ignoring round update {}, because it has no name", roundDTO);
                continue;
            }

            Round round;
            if (id == null) {
                round = new Round();
            } else {
                round = roundRepository.findOne(id);

                if (round == null) {
                    log.warn("Ignoring round update {}, because no round exists for this id", roundDTO);
                    continue;
                }
            }

            round.setName(name);

            roundRepository.save(round);
        }

        // Rounds not included in the form will be deleted
        // TODO: Detach them from Matches/Bonus questions first before deleting.
        idsToRemove.stream().forEach(roundRepository::delete);
    }
}
