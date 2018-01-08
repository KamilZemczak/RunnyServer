package kamilzemczak.runny.controller;

import java.util.ArrayList;
import java.util.List;
import kamilzemczak.runny.dao.ObjectiveRepository;
import kamilzemczak.runny.dao.TrainingRepository;
import kamilzemczak.runny.dao.UserRepository;
import kamilzemczak.runny.model.Objective;
import kamilzemczak.runny.model.Training;
import kamilzemczak.runny.model.User;
import kamilzemczak.runny.service.ObjectiveService;
import kamilzemczak.runny.service.TrainingService;
import kamilzemczak.runny.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ObjectiveController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectiveRepository objectiveRepository;
    
    @Autowired
    private ObjectiveService objectiveService;
    
        @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private TrainingService trainingService;



    @RequestMapping(value = "/objective_add", method = RequestMethod.POST, produces = "application/json")
    public String add(@ModelAttribute("userForm") User userForm, String type, String objective) {
        User currentUser = new User();
        currentUser = userRepository.findByUsername(userForm.getUsername());
        
        Objective objectiveToSave = new Objective();
        
        objectiveToSave.setAuthor(currentUser);
        objectiveToSave.setType(type);
        objectiveToSave.setObjective(objective);
        objectiveToSave.setExecuted("N");
        
        objectiveRepository.save(objectiveToSave);
              
        return "";
    }
    
    @RequestMapping(value = "/objectives_find", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    List<Objective> findg(Model model, User userForm) {
        User currentUser = new User();
        currentUser = userRepository.findByUsername(userForm.getUsername());
        
        List<Objective> objectives = new ArrayList<>();
          List<Objective> objectivesToSend = new ArrayList<>();
        objectives = objectiveService.findAll();
        
        for(Objective objective : objectives) {
            if(objective.getAuthor().getId().equals(currentUser.getId())) {
                objectivesToSend.add(objective);
            }
            
        }
        
        for(Objective objective2 : objectivesToSend) {
            if(objective2.getAuthor().getFriends()!=null) {
                objective2.getAuthor().getFriends().clear();
            }
        }
        
        return objectivesToSend;
    }
    
    
    @RequestMapping(value = "/objective_update", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    String update(Model model, User userForm) {
        User currentUser = new User();
        currentUser = userRepository.findByUsername(userForm.getUsername());
        
        List<Objective> objectives = new ArrayList<>();
        List<Objective> userObjectives = new ArrayList<>();
        objectives = objectiveService.findAll();
        
        
        List<Training> trainings = new ArrayList<>();
        List<Training> userTrainings = new ArrayList<>();
        trainings = trainingService.findAll();
        
        for(Training training : trainings ) {
            if(currentUser.getId().equals(training.getAuthor().getId())) {
                userTrainings.add(training);
            }
        }
        
        for(Objective objective : objectives) {
            if(objective.getAuthor().getId().equals(currentUser.getId())) {
                userObjectives.add(objective);
            }
            
        }
        
        for(Objective objective2 : userObjectives) {
            for(Training training2 : userTrainings) {
                if(objective2.getExecuted().equals("N")) {
                   String objective = objective2.getObjective();
                   String objectiveToProcess = objective.replace("km","");
                   Integer objectivei = Integer.valueOf(objectiveToProcess);
                   if(objectivei<=training2.getDistance() && training2.getTime().after(objective2.getTime())) {
                       objective2.setExecuted("Y");
                       objectiveService.update(objective2);
                   }
                }
            }
        }
        
      
    
        return "";
    }
  
}