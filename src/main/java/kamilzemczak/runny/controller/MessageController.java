package kamilzemczak.runny.controller;

import java.util.ArrayList;
import java.util.List;
import kamilzemczak.runny.dao.MessageRepository;
import kamilzemczak.runny.dao.UserRepository;
import kamilzemczak.runny.model.Message;
import kamilzemczak.runny.model.User;
import kamilzemczak.runny.service.MessageService;
import kamilzemczak.runny.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MessageController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/message_send", method = RequestMethod.POST, produces = "application/json")
    public String send(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model, String recipientUsername, String contents) {
        User author = new User();
        author = userRepository.findByUsername(userForm.getUsername());

        User recipient = new User();
        recipient = userRepository.findByUsername(recipientUsername);

        Message message = new Message();
        message.setAuthor(author);
        message.setRecipient(recipient);
        message.setContents(contents);

        messageRepository.save(message);
        return "";
    }

    @RequestMapping(value = "/messages_find", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    List<Message> find(Model model, User userForm, String recipientUsername) {

        User author = new User();
        author = userRepository.findByUsername(userForm.getUsername());

        User recipient = new User();
        recipient = userRepository.findByUsername(recipientUsername);

        Integer authorId = author.getId();
        Integer recipientId = recipient.getId();

        List<Message> messages = new ArrayList<>();
        List<Message> messagesToSend = new ArrayList<>();

        messages = messageService.findAll();

        for (Message message : messages) {
            Integer messageAuthorId = message.getAuthor().getId();
            Integer messageRecipientId = message.getRecipient().getId();

            if (messageAuthorId.equals(authorId) && messageRecipientId.equals(recipientId) || messageAuthorId.equals(recipientId) && messageRecipientId.equals(authorId)) {
                messagesToSend.add(message);
            }

            
        }
        
        for (Message messages2 : messagesToSend) {
               
                      messages2.getAuthor().getFriends().clear();
                
              
            }
        return messagesToSend;
    }
}
