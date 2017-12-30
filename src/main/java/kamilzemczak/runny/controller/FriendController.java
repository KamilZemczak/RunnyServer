package kamilzemczak.runny.controller;

import java.util.ArrayList;
import java.util.List;
import kamilzemczak.runny.dao.UserRepository;
import kamilzemczak.runny.model.User;
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
public class FriendController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value = "/friend_add", method = RequestMethod.POST, produces = "application/json")
    public String addFriend(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model, String friendToAdd) {
        User userToAdd = new User();
        userToAdd = userRepository.findByUsername(friendToAdd);
        
        User userToGet = new User();
        userToGet = userRepository.findByUsername(userForm.getUsername());
        
        if(userToGet.getFriends()==null) { //TODO: ma już w znajomych gościa
            ArrayList<User> friends = new ArrayList<>();
            friends.add(userToAdd);
            userToGet.setFriends(friends);
        } else {
             userToGet.getFriends().add(userToAdd);
        }
        
        userService.update(userToGet);
              
        return "";
    }
    
    @RequestMapping(value = "/friend_delete", method = RequestMethod.POST, produces = "application/json")
    public String deleteFriend(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model, String friendToDelete) {
        User userToAdd = new User();
        userToAdd = userRepository.findByUsername(friendToDelete);
        
        User userToGet = new User();
        userToGet = userRepository.findByUsername(userForm.getUsername());
        
        if(userToGet.getFriends()!=null) { //TODO: ma już w znajomych gościa
            userToGet.getFriends().remove(userToAdd);
        }
        
        userService.update(userToGet);
              
        return "";
    }
    
    @RequestMapping(value = "/friends_find", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    List<User> findAll(Model model, User userForm) {
        User currentUser = new User();
        currentUser = userRepository.findByUsername(userForm.getUsername());
        List<User> friendList = new ArrayList<>();
        List<User> friendsToSend = new ArrayList<>();
        friendList = currentUser.getFriends();

        for(User friend : friendList) {
            User userToSend = new User();
            userToSend.setUsername(friend.getUsername());
            userToSend.setName(friend.getName());
            userToSend.setSurname(friend.getSurname());
            userToSend.setEmail(friend.getEmail());
            userToSend.setAge(friend.getAge());
            userToSend.setGender(friend.getGender());
            userToSend.setWeight(friend.getHeight());
            userToSend.setHeight(friend.getHeight());
            userToSend.setCity(friend.getCity());
            userToSend.setAbout(friend.getAbout());

            friendsToSend.add(userToSend);
        }

        return friendsToSend;
    }
}