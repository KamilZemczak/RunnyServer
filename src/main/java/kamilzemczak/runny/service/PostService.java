package kamilzemczak.runny.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kamilzemczak.runny.dao.PostRepository;
import kamilzemczak.runny.model.Post;
import kamilzemczak.runny.model.User;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();
        for (Post post : postRepository.findAll()) {
            posts.add(post);
        }
        return posts;
    }

    public void update(Post post) {
        postRepository.save(post);
    }

    public Post create(User author, String contents) {
        Post post = new Post();
        post.setAuthor(author);
        post.setContents(contents);
        return post;
    }

    public List<Post> find(List<Post> posts, boolean flag, List<Integer> userFriendsId, User currentUser) {
        List<Post> postsToSend = new ArrayList<>();
        for (Post post : posts) {
            flag = true;
            for (Integer postAthuorId : userFriendsId) {
                if ((post.getAuthor().getId().equals(postAthuorId)) || (post.getAuthor().getId().equals(currentUser.getId())) && flag) {
                    postsToSend.add(post);
                    flag = false;
                }
            }
        }
        return postsToSend;
    }

    public void clear(List<Post> postsToSend) {
        for (Post posts2 : postsToSend) {
            posts2.getAuthor().getFriends().clear();
            if (posts2.getComments() != null) {
                posts2.getComments().clear();
            }
        }
    }
}
