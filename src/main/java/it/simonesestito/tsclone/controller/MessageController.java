package it.simonesestito.tsclone.controller;

import it.simonesestito.tsclone.Utils;
import it.simonesestito.tsclone.model.db.Message;
import it.simonesestito.tsclone.model.db.User;
import it.simonesestito.tsclone.model.entity.MessageInputDto;
import it.simonesestito.tsclone.model.entity.MessageResult;
import it.simonesestito.tsclone.model.entity.SimpleResult;
import it.simonesestito.tsclone.service.MessageService;
import it.simonesestito.tsclone.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/my")
@RestController
public class MessageController {
    private final MessageService messageService;
    private final UserService userService;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public MessageController(MessageService messageService, UserService userService){
        this.messageService = messageService;
        this.userService = userService;
    }

    @PostMapping("/send")
    public SimpleResult send(@RequestBody @Validated MessageInputDto messageDto,
                             BindingResult bindingResult){
        SimpleResult result = new SimpleResult();
        if (bindingResult.hasErrors()){
            return result.setBindingError(bindingResult);
        }

        Message message = modelMapper.map(messageDto, Message.class);

        String username = messageDto.getTo();
        User user = userService.findByUsername(username);
        if (user == null){
            result.setMessage("User " + username + " doesn't exist.");
            result.setSuccess(false);
            return result;
        }
        message.setAddressee(user);
        messageService.save(message);

        result.setSuccess(true);
        return result;
    }

    @RequestMapping("/inbox")
    public MessageResult listMyMessages(){
        return listMessages(Utils.getCurrentUsername());
    }

    @RequestMapping("/inbox/{username}")
    public MessageResult listMessages(@PathVariable String username){
        List<MessageResult.Data> messages = new ArrayList<>();
        messageService.findByUsername(username).forEach(m ->
            messages.add(modelMapper.map(m, MessageResult.Data.class))
        );
        return new MessageResult(username, messages);
    }

    @RequestMapping("/sent")
    public MessageResult listSent(){
        String username = Utils.getCurrentUsername();
        List<MessageResult.Data> messages = new ArrayList<>();
        messageService.findBySender(username).forEach(m ->
            messages.add(modelMapper.map(m, MessageResult.Data.class))
        );
        return new MessageResult(username, messages);
    }
}
