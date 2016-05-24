package nl.vpro.poel.repository;

import nl.vpro.poel.domain.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Long>{

    UserGroup findByName(String name);
}
