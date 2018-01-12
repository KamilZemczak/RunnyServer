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

import java.util.List;
import kamilzemczak.runny.model.User;

public interface UserService {

    void save(User user);

    void update(User User);

    void addFriend(User userToGet, User userToAdd);

    void deleteFriend(User userToGet, User userToAdd);

    void setUpdateValues(User userToUpdate, User userForm);

    void setDetails(User userToSet, Integer id, String name, String surname, String username, String email, Integer age, String gender, Integer weight, Integer height, String city, String about);

    List<User> prepareFriendsList(User currentUser);

    List<Integer> getUserFriendsId(User currentUser);

    List<User> prepareUsersToSend(List<User> allUsers);

    User findByUsername(String username);
}
