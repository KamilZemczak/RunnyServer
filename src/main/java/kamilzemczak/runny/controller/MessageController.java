package kamilzemczak.runny.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kamilzemczak.runny.dao.MessageRepository;
import kamilzemczak.runny.dao.UserRepository;
import kamilzemczak.runny.model.Message;
import kamilzemczak.runny.model.User;
import kamilzemczak.runny.service.MessageService;

@Controller
public class MessageController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/message_send", method = RequestMethod.POST, produces = "application/json")
    public String create(@ModelAttribute("userForm") User userForm, String recipientUsername, String contents) {
        User author = userRepository.findByUsername(userForm.getUsername());
        User recipient = userRepository.findByUsername(recipientUsername);
        Message message = messageService.create(author, recipient, contents);
        messageRepository.save(message);
        return "";
    }

    @RequestMapping(value = "/messages_find", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    List<Message> find(Model model, User userForm, String recipientUsername) {
        User author = userRepository.findByUsername(userForm.getUsername());
        User recipient = userRepository.findByUsername(recipientUsername);
        Integer authorId = author.getId();
        Integer recipientId = recipient.getId();
        List<Message> allMessages = messageService.findAll();
        List<Message> messagesToSend = new ArrayList<>();
        messageService.find(allMessages, authorId, recipientId, messagesToSend);
        messageService.clear(messagesToSend);
        return messagesToSend;
    }
}
