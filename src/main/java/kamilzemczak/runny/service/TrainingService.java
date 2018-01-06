package kamilzemczak.runny.service;

import java.util.ArrayList;
import java.util.List;
import kamilzemczak.runny.dao.TrainingRepository;
import kamilzemczak.runny.model.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingService {
    
    @Autowired
    private TrainingRepository trainingRepository;

    public List<Training> findAll() {
        List<Training> trainings = new ArrayList<>();
        for (Training training : trainingRepository.findAll()) {
            trainings.add(training);
        }
        return trainings;
    }
    
     public void update(Training training) {
        trainingRepository.save(training);
    }
}