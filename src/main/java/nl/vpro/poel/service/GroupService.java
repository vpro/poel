package nl.vpro.poel.service;

import nl.vpro.poel.domain.Group;
import nl.vpro.poel.dto.GroupForm;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface GroupService {

    Optional<Group> findById(Long id);

    Optional<Group> findByName(String name);

    List<Group> findAll();

    void setGroups(GroupForm groupForm);
}
