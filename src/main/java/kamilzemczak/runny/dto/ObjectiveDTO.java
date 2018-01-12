package kamilzemczak.runny.dto;

import java.util.Date;
import kamilzemczak.runny.model.User;

public class ObjectiveDTO {
 
    private Integer id;
    private User author;
    private String type;
    private String objective;
    private Date time;
    private String executed;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getExecuted() {
        return executed;
    }

    public void setExecuted(String executed) {
        this.executed = executed;
    }

    @Override
    public String toString() {
        return "ObjectiveDTO{" + "id=" + id + ", author=" + author + ", type=" + type + ", objective=" + objective + ", time=" + time + ", executed=" + executed + '}';
    }
}
