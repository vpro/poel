package nl.vpro.poel.service;

import nl.vpro.poel.domain.Bulletin;
import nl.vpro.poel.dto.BulletinForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BulletinService {

    List<Bulletin> findAll();

    void setBulletins(BulletinForm bulletinForm);
}
