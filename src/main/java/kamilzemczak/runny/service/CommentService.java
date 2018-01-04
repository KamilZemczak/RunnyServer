/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kamilzemczak.runny.service;

import java.util.ArrayList;
import java.util.List;
import kamilzemczak.runny.dao.CommentRepository;
import kamilzemczak.runny.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    
    @Autowired
    private CommentRepository commentRepository;

    
    public List<Comment> findAll() {
        List<Comment> comments = new ArrayList<>();
        for (Comment comment : commentRepository.findAll()) {
            comments.add(comment);
        }
        return comments;
    }
    
     public void update(Comment comment) {
        commentRepository.save(comment);
    }
}
