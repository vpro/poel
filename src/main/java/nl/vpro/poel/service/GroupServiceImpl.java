package nl.vpro.poel.service;

import nl.vpro.poel.domain.Group;
import nl.vpro.poel.dto.GroupForm;
import nl.vpro.poel.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

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
