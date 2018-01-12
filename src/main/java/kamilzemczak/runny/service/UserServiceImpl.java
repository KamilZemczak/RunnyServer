/**
 * ***********************************************************
 * Autorskie Prawa Majątkowe Kamil Zemczak
 *
 * Copyright 2017 Kamil Zemczak
 * ************************************************************
 * Utworzono 26-10-2017, Kamil Zemczak
 * ************************************************************
 */
package kamilzemczak.runny.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import kamilzemczak.runny.dao.UserRepository;
import kamilzemczak.runny.model.User;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getUsername(Integer id) {
        return userRepository.findOne(id);
    }

    public String getUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            return auth.getName();
        }
        return "";
    }

    public User getUserId() {
        User user = userRepository.findByUsername(getUserName());
        if (user == null) {
            throw new SecurityException();
        }
        return user;
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            users.add(user);
        }
        return users;
    }

    public void addFriend(User userToGet, User userToAdd) {
        if (userToGet.getFriends() == null) {
            ArrayList<User> friends = new ArrayList<>();
            friends.add(userToAdd);
            userToGet.setFriends(friends);
        } else {
            userToGet.getFriends().add(userToAdd);
        }
    }

    public void deleteFriend(User userToGet, User userToAdd) {
        if (userToGet.getFriends() != null) {
            userToGet.getFriends().remove(userToAdd);
        }
    }

    public List<User> prepareFriendsList(User currentUser) {
        List<User> friendList = currentUser.getFriends();
        List<User> friendsToSend = new ArrayList<>();
        for (User friend : friendList) {
            User userToSend = new User();
            userToSend.setId(friend.getId());
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

    public List<Integer> getUserFriendsId(User currentUser) {
        List<User> userFriends = currentUser.getFriends();
        List<Integer> userFriendsId = new ArrayList<>();
        for (User friend : userFriends) {
            userFriendsId.add(friend.getId());
        }
        return userFriendsId;
    }

    public void setDetails(User userToSet, Integer id, String name, String surname, String username, String email, Integer age, String gender, Integer weight, Integer height, String city, String about) {
        userToSet.setId(id);
        userToSet.setName(name);
        userToSet.setSurname(surname);
        userToSet.setUsername(username);
        userToSet.setEmail(email);
        userToSet.setAge(age);
        userToSet.setGender(gender);
        userToSet.setWeight(weight);
        userToSet.setHeight(height);
        userToSet.setCity(city);
        userToSet.setAbout(about);
    }

    public void setUpdateValues(User userToUpdate, User userForm) {
        userToUpdate.setUsername(userForm.getUsername());
        userToUpdate.setAge(userForm.getAge());
        if (userForm.getWeight() != null) {
            userToUpdate.setWeight(userForm.getWeight());
        }

        if (userForm.getHeight() != null) {
            userToUpdate.setHeight(userForm.getHeight());
        }

        if (userForm.getCity() != null) {
            userToUpdate.setCity(userForm.getCity());
        }

        if (userForm.getAbout() != null) {
            userToUpdate.setAbout(userForm.getAbout());
        }
    }

    public List<User> prepareUsersToSend(List<User> allUsers) {
        List<User> usersToSend = new ArrayList<>();
        for (User user : allUsers) {
            User userToSend = new User();
            userToSend.setId(user.getId());
            userToSend.setUsername(user.getUsername());
            userToSend.setName(user.getName());
            userToSend.setSurname(user.getSurname());
            userToSend.setEmail(user.getEmail());
            userToSend.setAge(user.getAge());
            userToSend.setGender(user.getGender());
            userToSend.setWeight(user.getHeight());
            userToSend.setHeight(user.getHeight());
            userToSend.setCity(user.getCity());
            userToSend.setAbout(user.getAbout());
            usersToSend.add(userToSend);
        }
        return usersToSend;
    }
}
