package nl.vpro.poel.service;

import nl.vpro.poel.domain.Round;
import nl.vpro.poel.dto.RoundsForm;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RoundService {

    Optional<Round> findById(Long id);

    Optional<Round> findByName(String name);

    List<Round> findAll();

    void setRounds(RoundsForm roundsForm);
}
