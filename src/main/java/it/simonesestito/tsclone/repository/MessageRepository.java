package it.simonesestito.tsclone.repository;

import it.simonesestito.tsclone.model.db.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByAddresseeUsernameOrderByDate(String username);
    List<Message> findAllByAddresseeUsernameAndSecretFalseOrderByDate(String username);
    List<Message> findAllBySenderUsername(String username);
}
