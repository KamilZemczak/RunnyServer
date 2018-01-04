/**
 * ***********************************************************
 * Autorskie Prawa Majątkowe Kamil Zemczak
 *
 * Copyright 2017 Kamil Zemczak
 * ************************************************************
 * Utworzono 30-12-2017, Kamil Zemczak
 * ************************************************************
 */
package kamilzemczak.runny.dto;

import java.util.Date;
import java.util.List;
import kamilzemczak.runny.model.User;

public class PostDTO {

    private Integer id;
    private User author;
    private Date time;
    private String contents;
    private List<User> comments;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public List<User> getComments() {
        return comments;
    }

    public void setComments(List<User> comments) {
        this.comments = comments;
    }
}
