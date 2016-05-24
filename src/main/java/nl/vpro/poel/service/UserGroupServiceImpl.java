package nl.vpro.poel.service;


import nl.vpro.poel.domain.UserGroup;
import nl.vpro.poel.dto.UserGroupForm;
import nl.vpro.poel.repository.UserGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserGroupServiceImpl implements UserGroupService {

    private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    private final UserGroupRepository userGroupRepository;

    @Autowired
    public UserGroupServiceImpl(UserGroupRepository userGroupRepository) { this.userGroupRepository = userGroupRepository; }


    @Override
    public Optional<UserGroup> findById(Long id) {
        return Optional.ofNullable(userGroupRepository.findOne(id));
    }

    @Override
    public Optional<UserGroup> findByName(String name) {
        return Optional.ofNullable(userGroupRepository.findByName(name));
    }

    @Override
    public List<UserGroup> findAll() { return userGroupRepository.findAll(); }

    @Override
    public void setUserGroups(UserGroupForm userGroupForm) {


        Set<Long> idsToRemove = findAll().stream().map(UserGroup::getId).collect(Collectors.toSet());

        for (UserGroup postedUserGroup : userGroupForm.getUserGroups()) {

            String name = postedUserGroup.getName();

            UserGroup userGroup = userGroupRepository.findByName( name );

            if ( userGroup == null ) {
                userGroup = new UserGroup( name );
            } else {
                idsToRemove.remove(userGroup.getId());
            }
            userGroupRepository.save(userGroup);
        }

        // UserGroups not included in the form are deleted from the repository
        // TODO: Delete should be cascading. Usergroups should be decoupled from users before being removed.
        idsToRemove.stream().forEach(userGroupRepository::delete);
    }

}
