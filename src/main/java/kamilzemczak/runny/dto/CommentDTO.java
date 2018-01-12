package kamilzemczak.runny.dto;

import java.util.Date;
import kamilzemczak.runny.model.Post;
import kamilzemczak.runny.model.User;

public class CommentDTO {

    private Integer id;
    private User author;
    private Post post;
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
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
        return "CommentDTO{" + "id=" + id + ", author=" + author + ", post=" + post + ", contents=" + contents + ", time=" + time + '}';
    }
}
