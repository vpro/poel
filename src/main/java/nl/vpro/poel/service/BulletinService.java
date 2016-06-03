package nl.vpro.poel.service;

import nl.vpro.poel.domain.Bulletin;
import nl.vpro.poel.dto.BulletinForm;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BulletinService {

    Optional<Bulletin> findById(Long id);

    Optional<Bulletin> findByKey(String key);

    Optional<String> getText(String key);

    Optional<String> getDescription(String description);

    Optional<String> getDate(String date);

    List<Bulletin> findAll();

    void setBulletins(BulletinForm bulletinForm);
}
