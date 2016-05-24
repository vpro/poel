package nl.vpro.poel.service;

import nl.vpro.poel.domain.User;
import nl.vpro.poel.domain.UserGroup;
import nl.vpro.poel.dto.UsersForm;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getUserByUsername(String username);

    List<User> getAllUsers();

    List<User> getAllUsersForUserGroup(UserGroup userGroup);

    User getOrCreate(String username);

    Boolean updateUser(User user);

    void updateUserGroupForUsers(UsersForm usersForm);
}
