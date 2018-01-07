/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kamilzemczak.runny.service;

import java.util.ArrayList;
import java.util.List;
import kamilzemczak.runny.dao.ObjectiveRepository;
import kamilzemczak.runny.model.Objective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjectiveService {
    
    @Autowired
    private ObjectiveRepository objectiveRepository;

    
    public List<Objective> findAll() {
        List<Objective> objectives = new ArrayList<>();
        for (Objective Objective : objectiveRepository.findAll()) {
            objectives.add(Objective);
        }
        return objectives;
    }
    
     public void update(Objective objective) {
        objectiveRepository.save(objective);
    }
}
