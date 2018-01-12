package kamilzemczak.runny.dto;

import java.util.Date;
import kamilzemczak.runny.model.Training;
import kamilzemczak.runny.model.User;

public class TCommentDTO {
    
    private Integer id;
    private User author;
    private Training training;
    private String contents;
    private Date time;

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

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "TCommentDTO{" + "id=" + id + ", author=" + author + ", training=" + training + ", contents=" + contents + ", time=" + time + '}';
    }
}
