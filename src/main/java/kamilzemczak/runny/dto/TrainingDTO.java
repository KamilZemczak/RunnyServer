package kamilzemczak.runny.dto;

import java.util.Date;
import java.util.List;
import kamilzemczak.runny.model.TComment;
import kamilzemczak.runny.model.User;

public class TrainingDTO {
    
    private Integer id;
    private User author;
    private String contents;
    private Integer distance;
    private Integer duration;
    private Integer calories;
    private String notes;
    private Date time;
    private List<TComment> tcomments;
    
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

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public List<TComment> getTcomments() {
        return tcomments;
    }

    public void setTcomments(List<TComment> tcomments) {
        this.tcomments = tcomments;
    }

    @Override
    public String toString() {
        return "TrainingDTO{" + "id=" + id + ", author=" + author + ", contents=" + contents + ", distance=" + distance + ", duration=" + duration + ", calories=" + calories + ", notes=" + notes + ", time=" + time + ", tcomments=" + tcomments + '}';
    }   
}
