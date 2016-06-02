package nl.vpro.poel.service;

import nl.vpro.poel.domain.MatchDay;
import nl.vpro.poel.dto.MatchDayForm;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MatchDayService {

    Optional<MatchDay> findById(Long id);

    Optional<MatchDay> findByName(String name);

    List<MatchDay> findAll();

    void setMatchDays(MatchDayForm matchDayForm);
}
