package kamilzemczak.runny.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kamilzemczak.runny.dao.ObjectiveRepository;
import kamilzemczak.runny.dao.UserRepository;
import kamilzemczak.runny.model.Objective;
import kamilzemczak.runny.model.Training;
import kamilzemczak.runny.model.User;
import kamilzemczak.runny.service.ObjectiveService;
import kamilzemczak.runny.service.TrainingService;

@Controller
public class ObjectiveController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectiveRepository objectiveRepository;

    @Autowired
    private ObjectiveService objectiveService;

    @Autowired
    private TrainingService trainingService;

    @RequestMapping(value = "/objective_add", method = RequestMethod.POST, produces = "application/json")
    public String create(@ModelAttribute("userForm") User userForm, String type, String objective) {
        User currentUser = userRepository.findByUsername(userForm.getUsername());
        Objective objectiveToSave = objectiveService.create(currentUser, type, objective);
        objectiveRepository.save(objectiveToSave);
        return "";
    }

    @RequestMapping(value = "/objectives_find", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    List<Objective> find(User userForm) {
        User currentUser = userRepository.findByUsername(userForm.getUsername());
        List<Objective> allObjectives = objectiveService.findAll();
        List<Objective> objectivesToSend = objectiveService.find(allObjectives, currentUser);
        objectiveService.clear(objectivesToSend);
        return objectivesToSend;
    }

    @RequestMapping(value = "/objective_update", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    String update(User userForm) {
        User currentUser = userRepository.findByUsername(userForm.getUsername());
        List<Objective> objectives = objectiveService.findAll();
        List<Objective> userObjectives = new ArrayList<>();
        List<Training> trainings = trainingService.findAll();
        List<Training> userTrainings = trainingService.getUserTrainings(trainings, currentUser);
        objectiveService.getUserObjectives(objectives, currentUser, userObjectives);
        objectiveService.updateObjective(userObjectives, userTrainings);
        return "";
    }
    
    @RequestMapping(value = "/objective_delete", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    String update(String sObjectiveId) {
        Integer objectiveId = Integer.valueOf(sObjectiveId);
        Objective objective = objectiveRepository.findById(objectiveId);
        objectiveRepository.delete(objective);
        return "";
    }
}
