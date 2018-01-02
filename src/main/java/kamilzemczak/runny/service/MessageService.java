/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kamilzemczak.runny.service;

import java.util.ArrayList;
import java.util.List;
import kamilzemczak.runny.dao.MessageRepository;
import kamilzemczak.runny.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
