package nl.vpro.poel.service;

import lombok.extern.slf4j.Slf4j;
import nl.vpro.poel.domain.UserGroup;
import nl.vpro.poel.dto.UserGroupDTO;
import nl.vpro.poel.dto.UserGroupForm;
import nl.vpro.poel.repository.UserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserGroupServiceImpl implements UserGroupService {

    private final UserGroupRepository userGroupRepository;

    @Autowired
    public UserGroupServiceImpl(UserGroupRepository userGroupRepository) {
        this.userGroupRepository = userGroupRepository;
    }

    @Override
    public Optional<UserGroup> findById(Long id) {
        return Optional.ofNullable(userGroupRepository.findOne(id));
    }

    @Override
    public Optional<UserGroup> findByName(String name) {
        return Optional.ofNullable(userGroupRepository.findByName(name));
    }

    @Override
    public List<UserGroup> findAll() {
        return userGroupRepository.findAll();
    }

    @Override
    public void setUserGroups(UserGroupForm userGroupForm) {
        Set<Long> idsToRemove = findAll().stream().map(UserGroup::getId).collect(Collectors.toSet());

        for (UserGroupDTO userGroupDTO : userGroupForm.getUserGroups()) {
            Long id = userGroupDTO.getId();

            idsToRemove.remove(id);

            String name = userGroupDTO.getName();

            if (name == null) {
                log.warn("Ignoring user group update {}, because it has no name", userGroupDTO);
                continue;
            }

            UserGroup userGroup;
            if (id == null) {
                userGroup = new UserGroup();
            } else {
                userGroup = userGroupRepository.findOne(id);

                if (userGroup == null) {
                    log.warn("Ignoring user group update {}, because no user group exists for this id", userGroupDTO);
                    continue;
                }
            }

            userGroup.setName(name);

            userGroupRepository.save(userGroup);
        }

        // UserGroups not included in the form are deleted from the repository
        // TODO: Delete should be cascading. Usergroups should be decoupled from users before being removed.
        idsToRemove.stream().forEach(userGroupRepository::delete);
    }
}
