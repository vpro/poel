package nl.vpro.poel.service;

import nl.vpro.poel.domain.Role;
import nl.vpro.poel.domain.User;
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

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
