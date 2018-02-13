package it.simonesestito.tsclone.service;

import it.simonesestito.tsclone.model.db.Message;

import java.util.List;

public interface MessageService {
    List<Message> findByUsername(String username);
    List<Message> findBySender(String username);
    void save(Message message);
}
