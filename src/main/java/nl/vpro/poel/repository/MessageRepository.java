package nl.vpro.poel.repository;

import nl.vpro.poel.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{

    Message findByKey(String key);
}
