/**
 * ***********************************************************
 * Autorskie Prawa Majątkowe Kamil Zemczak
 *
 * Copyright 2017 Kamil Zemczak
 * ************************************************************
 * Utworzono 26-10-2017, Kamil Zemczak
 * ************************************************************
 */
package kamilzemczak.runny.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import kamilzemczak.runny.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
