package kamilzemczak.runny.dto;

import kamilzemczak.runny.model.Post;
import kamilzemczak.runny.model.User;

public class PostLikeDTO {

    private Integer id;
    private User user;
    private Post post;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "PostLikeDTO{" + "id=" + id + ", user=" + user + ", post=" + post + '}';
    }
}
