package kamilzemczak.runny.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kamilzemczak.runny.dao.MessageRepository;
import kamilzemczak.runny.model.Message;
import kamilzemczak.runny.model.User;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> findAll() {
        List<Message> messages = new ArrayList<>();
        for (Message message : messageRepository.findAll()) {
            messages.add(message);
        }
        return messages;
    }

    public Message create(User author, User recipient, String contents) {
        Message message = new Message();
        message.setAuthor(author);
        message.setRecipient(recipient);
        message.setContents(contents);
        return message;
    }

    public void find(List<Message> allMessages, Integer authorId, Integer recipientId, List<Message> messagesToSend) {
        for (Message message : allMessages) {
            Integer messageAuthorId = message.getAuthor().getId();
            Integer messageRecipientId = message.getRecipient().getId();
            if (messageAuthorId.equals(authorId) && messageRecipientId.equals(recipientId) || messageAuthorId.equals(recipientId) && messageRecipientId.equals(authorId)) {
                messagesToSend.add(message);
            }
        }
    }

    public void clear(List<Message> messagesToSend) {
        for (Message messages : messagesToSend) {
            messages.getAuthor().getFriends().clear();
        }
    }
}
