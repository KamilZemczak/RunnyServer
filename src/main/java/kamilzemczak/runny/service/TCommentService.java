/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kamilzemczak.runny.service;

import java.util.ArrayList;
import java.util.List;
import kamilzemczak.runny.dao.TCommentRepository;
import kamilzemczak.runny.model.TComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TCommentService {
    
    @Autowired
    private TCommentRepository tCommentRepository;

    
    public List<TComment> findAll() {
        List<TComment> tComments = new ArrayList<>();
        for (TComment tComment : tCommentRepository.findAll()) {
            tComments.add(tComment);
        }
        return tComments;
    }
    
     public void update(TComment tComment) {
        tCommentRepository.save(tComment);
    }
}
