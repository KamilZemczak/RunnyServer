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

import kamilzemczak.runny.model.User;

public interface UserService {

    void save(User user);
    
    void update(User User);

    User findByUsername(String username);
}
