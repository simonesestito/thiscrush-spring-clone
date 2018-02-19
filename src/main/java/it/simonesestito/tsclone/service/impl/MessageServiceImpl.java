package it.simonesestito.tsclone.service.impl;

import it.simonesestito.tsclone.Utils;
import it.simonesestito.tsclone.model.entity.Message;
import it.simonesestito.tsclone.model.entity.User;
import it.simonesestito.tsclone.repository.MessageRepository;
import it.simonesestito.tsclone.service.MessageService;
import it.simonesestito.tsclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository repository;
    private final UserService userService;

    @Autowired
    public MessageServiceImpl(MessageRepository repository, UserService userService){
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public List<Message> findByUsername(String username) {
        String current = Utils.getCurrentUsername();
        if (username.equals(current))
            return repository.findAllByAddresseeUsernameOrderByDate(username);
        else
            return repository.findAllByAddresseeUsernameAndSecretFalseOrderByDate(username);
    }

    @Override
    public void save(Message message) {
        if (message.getDate() == null)
            message.setDate(System.currentTimeMillis());

        String username = Utils.getCurrentUsername();
        User sender = userService.findByUsername(username);
        message.setSender(sender);

        repository.save(message);
    }

    @Override
    public List<Message> findBySender(String username) {
        return repository.findAllBySenderUsername(username);
    }
}
