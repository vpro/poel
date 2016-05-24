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
       // TODO save posted groups
    }
}
