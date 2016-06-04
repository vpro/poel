package nl.vpro.poel.service;

import lombok.extern.slf4j.Slf4j;
import nl.vpro.poel.domain.Bulletin;
import nl.vpro.poel.dto.BulletinForm;
import nl.vpro.poel.repository.BulletinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BulletinServiceImpl implements BulletinService {

    private final BulletinRepository bulletinRepository;

    @Autowired
    public BulletinServiceImpl(BulletinRepository bulletinRepository) {
        this.bulletinRepository = bulletinRepository;
    }

    @Override
    public List<Bulletin> findAll() {
        return bulletinRepository.findAll();
    }

    @Override
    public void setBulletins(BulletinForm bulletinForm) {
        Set<Long> idsToRemove = findAll().stream().map(Bulletin::getId).collect(Collectors.toSet());

        for (Bulletin postedBulletin : bulletinForm.getBulletins()) {

            Long id = postedBulletin.getId();

            idsToRemove.remove(id);

            String key = postedBulletin.getKey();
            String text = postedBulletin.getText();

            String date = postedBulletin.getDate();
            String description = postedBulletin.getDescription();

            if (key == null || text == null || date == null || description == null) {
                log.warn("Ignoring bulletin update {}, because it is incomplete", postedBulletin);
                continue;
            }

            Bulletin bulletin;
            if (id == null) {
                bulletin = new Bulletin();
            } else {
                bulletin = bulletinRepository.findOne(id);

                if (bulletin == null) {
                    log.warn("Ignoring bulletin update {}, because no bulletin exists for this id", postedBulletin);
                    continue;
                }
            }

            bulletin.setKey(key);
            bulletin.setText(text);

            bulletin.setDescription(description);
            bulletin.setDate(date);

            bulletinRepository.save(bulletin);
        }

        // Bulletins not included in the form are deleted from the repository
        idsToRemove.stream().forEach(bulletinRepository::delete);
    }
}
