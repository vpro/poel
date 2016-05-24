package nl.vpro.poel.service;

import nl.vpro.poel.domain.UserGroup;
import nl.vpro.poel.dto.UserGroupForm;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserGroupService {

    Optional<UserGroup> findById(Long id);

    Optional<UserGroup> findByName(String name);

    List<UserGroup> findAll();

    void setUserGroups(UserGroupForm userGroupForm);
}
