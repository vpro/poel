package nl.vpro.poel.service;


import nl.vpro.poel.domain.Group;
import nl.vpro.poel.dto.GroupForm;
import nl.vpro.poel.repository.GroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

    private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    private final GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) { this.groupRepository = groupRepository; }


    @Override
    public Optional<Group> findById(Long id) {
        return Optional.ofNullable(groupRepository.findOne(id));
    }

    @Override
    public Optional<Group> findByName(String name) {
        return Optional.ofNullable(groupRepository.findByName(name));
    }

    @Override
    public List<Group> findAll() { return groupRepository.findAll(); }

    @Override
    public void setGroups(GroupForm groupForm) {
       // TODO save posted groups
    }
}
