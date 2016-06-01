package nl.vpro.poel.service;

import lombok.extern.slf4j.Slf4j;
import nl.vpro.poel.domain.Role;
import nl.vpro.poel.domain.User;
import nl.vpro.poel.domain.UserGroup;
import nl.vpro.poel.dto.UserForm;
import nl.vpro.poel.dto.UserAndUserGroupDTO;
import nl.vpro.poel.dto.UsersForm;
import nl.vpro.poel.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserGroupService userGroupService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserGroupService userGroupService) {
        this.userRepository = userRepository;
        this.userGroupService = userGroupService;
    }

    @Override
    public Optional<User> getUserById(long id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
         return userRepository.findOneByUsernameIgnoreCase(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getAllUsersForUserGroup(UserGroup userGroup) {
        return userRepository.findAllByUserGroup(userGroup);
    }

    @Override
    public User getOrCreate(String username) {
        Optional<User> existingUser = getUserByUsername(username);
        if (existingUser.isPresent()) {
            return existingUser.get();
        }
        String usernameWithoutDomain = StringUtils.substringBefore(username, "@");
        User newUser = new User(username, Role.USER, usernameWithoutDomain, usernameWithoutDomain);
        return userRepository.save(newUser);
    }

    @Override
    public void updateUser(User user, UserForm userForm) {
        user.setRealName(userForm.getRealName());
        user.setGameName(userForm.getGameName());
        userRepository.save(user);
    }

    @Override
    public void updateUserGroupForUsers(UsersForm usersForm) {
        for (UserAndUserGroupDTO userDTO : usersForm.getUsers()) {
            Optional<User> existingUser = getUserById(userDTO.getUserId());
            if (existingUser.isPresent()) {
                User user = existingUser.get();
                Long userGroupId = userDTO.getUserGroupId();
                if (userGroupId == null) {
                    user.setUserGroup(null);
                    userRepository.save(user);
                } else {
                    Optional<UserGroup> existingUserGroup = userGroupService.findById(userGroupId);
                    if (existingUserGroup.isPresent()) {
                        UserGroup userGroup = existingUserGroup.get();
                        user.setUserGroup(userGroup);
                        userRepository.save(user);
                    } else {
                        log.warn("Ignoring user update {}, because no user group exists for this id", userDTO);
                    }
                }
            } else {
                log.warn("Ignoring user update {}, because no user exists for this id", userDTO);
            }
        }
    }
}
