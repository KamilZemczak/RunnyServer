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
import kamilzemczak.runny.model.User;

public class MessageDTO {

    private Integer id;
    private User author;
    private User recipient;
    private Date time;
    private String contents;

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

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
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

    @Override
    public String toString() {
        return "MessageDTO{" + "id=" + id + ", author=" + author + ", recipient=" + recipient + ", time=" + time + ", contents=" + contents + '}';
    }
}
