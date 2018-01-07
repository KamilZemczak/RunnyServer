package kamilzemczak.runny.controller;

import java.util.ArrayList;
import java.util.List;
import kamilzemczak.runny.dao.ObjectiveRepository;
import kamilzemczak.runny.dao.UserRepository;
import kamilzemczak.runny.model.Objective;
import kamilzemczak.runny.model.User;
import kamilzemczak.runny.service.ObjectiveService;
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


    @RequestMapping(value = "/objective_add", method = RequestMethod.POST, produces = "application/json")
    public String add(@ModelAttribute("userForm") User userForm, String type, String objective) {
        User currentUser = new User();
        currentUser = userRepository.findByUsername(userForm.getUsername());
        
        Objective objectiveToSave = new Objective();
        
        objectiveToSave.setAuthor(currentUser);
        objectiveToSave.setType(type);
        objectiveToSave.setObjective(objective);
      
        
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
  
}