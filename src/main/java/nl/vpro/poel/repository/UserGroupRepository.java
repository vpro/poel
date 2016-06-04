package nl.vpro.poel.repository;

import nl.vpro.poel.domain.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Long>{

    Optional<UserGroup> findByName(String name);
}
