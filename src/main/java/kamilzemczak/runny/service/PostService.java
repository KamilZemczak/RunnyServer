/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kamilzemczak.runny.service;

import java.util.ArrayList;
import java.util.List;
import kamilzemczak.runny.dao.PostRepository;
import kamilzemczak.runny.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
