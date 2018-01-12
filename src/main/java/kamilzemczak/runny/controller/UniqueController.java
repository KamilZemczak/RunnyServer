/**
 * ***********************************************************
 * Autorskie Prawa Majątkowe Kamil Zemczak
 *
 * Copyright 2017 Kamil Zemczak
 * ************************************************************
 * Utworzono 05-11-2017, Kamil Zemczak
 * ************************************************************
 */
package kamilzemczak.runny.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kamilzemczak.runny.dao.MessageRepository;
import kamilzemczak.runny.dao.UserRepository;
import kamilzemczak.runny.model.User;
import kamilzemczak.runny.service.MessageService;
import kamilzemczak.runny.service.UserDetailsServiceImpl;
import kamilzemczak.runny.service.UserService;


@Controller
public class UniqueController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/unique_user", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    Boolean username(User userForm) {
        if (userRepository.findByUsername(userForm.getUsername()) != null) {
            userDetailsServiceImpl.loadUserByUsername(userForm.getUsername());
            return false;
        } else {
            return true;
        }
    }

    @RequestMapping(value = "/unique_email", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    Boolean email(User userForm) {
        if (userRepository.findByEmail(userForm.getEmail()) != null) {
            return false;
        } else {
            return true;
        }
    }

    @RequestMapping(value = "/is_friend", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    Boolean friend(User userForm, String friendUsername) {
        Boolean uniqueFriend = null;
        User currentUser = userRepository.findByUsername(userForm.getUsername());
        List<User> friendList = currentUser.getFriends();
        for (User userFriend : friendList) {
            if (!userFriend.getUsername().matches(friendUsername)) {
                uniqueFriend = false;
            } else {
                return uniqueFriend = true;
            }
        }
        return uniqueFriend;
    }
}
