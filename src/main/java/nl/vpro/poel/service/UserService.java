package nl.vpro.poel.service;

import nl.vpro.poel.domain.User;
import nl.vpro.poel.dto.UsersForm;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getUserByUsername(String username);

    List<User> getAllUsers();

    User getOrCreate(String username);

    Boolean updateUser(User user);

    void updateUserGroupsForUsers(UsersForm usersForm);
}
