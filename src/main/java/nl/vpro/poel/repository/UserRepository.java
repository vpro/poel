package nl.vpro.poel.repository;

import nl.vpro.poel.domain.User;
import nl.vpro.poel.domain.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByUsernameIgnoreCase(String username);

    List<User> findAllByUserGroup(UserGroup userGroup);
}