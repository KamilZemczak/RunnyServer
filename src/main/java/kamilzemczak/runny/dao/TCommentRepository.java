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

import kamilzemczak.runny.model.Comment;
import kamilzemczak.runny.model.TComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TCommentRepository extends JpaRepository<TComment, Integer> {

    Comment findById(Integer id);

}
