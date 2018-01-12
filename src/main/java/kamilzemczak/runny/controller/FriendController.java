package kamilzemczak.runny.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kamilzemczak.runny.dao.UserRepository;
import kamilzemczak.runny.model.User;
import kamilzemczak.runny.service.UserService;

@Controller
public class FriendController {
 
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/friend_add", method = RequestMethod.POST, produces = "application/json")
    public String add(@ModelAttribute("userForm") User userForm, String friendToAdd) {
        User userToAdd = userRepository.findByUsername(friendToAdd);
        User userToGet = userRepository.findByUsername(userForm.getUsername());
        userService.addFriend(userToGet, userToAdd); 
        userService.update(userToGet);
        return "";
    }

    @RequestMapping(value = "/friend_delete", method = RequestMethod.POST, produces = "application/json")
    public String delete(@ModelAttribute("userForm") User userForm, String friendToDelete) {
        User userToAdd = userRepository.findByUsername(friendToDelete);
        User userToGet = userRepository.findByUsername(userForm.getUsername());
        userService.deleteFriend(userToGet, userToAdd);
        userService.update(userToGet);
        return "";
    }

   
    
    @RequestMapping(value = "/friends_find", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    List<User> findAll(User userForm) {
        User currentUser = userRepository.findByUsername(userForm.getUsername());
        List<User> friendsToSend = userService.prepareFriendsList(currentUser);
        return friendsToSend;
    }
}