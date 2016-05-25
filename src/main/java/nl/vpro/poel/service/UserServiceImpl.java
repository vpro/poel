package nl.vpro.poel.service;

import nl.vpro.poel.domain.Role;
import nl.vpro.poel.domain.User;
import nl.vpro.poel.domain.UserGroup;
import nl.vpro.poel.dto.UsersDTO;
import nl.vpro.poel.dto.UsersForm;
import nl.vpro.poel.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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
        User newUser = new User(username, Role.USER, username, username);
        return userRepository.save(newUser);
    }

    @Override
    public Boolean updateUser ( User updatedUser ) {
        String username = updatedUser.getUsername();
        String gameName = updatedUser.getGameName();

        if ( username != null && gameName != null ) {
            User existingUser = getUserByUsername(username).orElse(null);
            if ( existingUser != null ) {

                existingUser.setGameName(gameName);
                userRepository.saveAndFlush(existingUser);
                return true;

            } else {
                logger.warn("Ignoring user update {}, because no user exists for this username", updatedUser );
            }
        } else {
            logger.warn("Ignoring user update {}, because it is incomplete");
        }

        return false;
    }

    @Override
    public void updateUserGroupForUsers(UsersForm usersForm) {

        for (UsersDTO usersDTO : usersForm.getUsers()) {

            User existingUser = getUserById(usersDTO.getUserId()).orElse(null);
            Long userGroupId = usersDTO.getUserGroupId();

            if ( existingUser != null ) {

                if ( userGroupId == null ) {

                    existingUser.setUserGroup(null);
                    userRepository.saveAndFlush(existingUser);

                } else {

                    UserGroup userGroup = userGroupService.findById(userGroupId).orElse(null);
                    if ( userGroup != null ) {

                        existingUser.setUserGroup(userGroup);
                        userRepository.saveAndFlush(existingUser);

                    } else {
                        logger.warn("Ignoring user update {}, because no user group exists for this id", userGroupId);
                    }
                }

            } else {
                logger.warn("Ignoring user update {}, because no user exists for this id", usersDTO.getUserId() );
            }
        }
    }
}
