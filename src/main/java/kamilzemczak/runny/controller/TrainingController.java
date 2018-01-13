package kamilzemczak.runny.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kamilzemczak.runny.dao.TrainingRepository;
import kamilzemczak.runny.dao.UserRepository;
import kamilzemczak.runny.model.Comment;
import kamilzemczak.runny.model.TComment;
import kamilzemczak.runny.model.Training;
import kamilzemczak.runny.model.User;
import kamilzemczak.runny.service.TCommentService;
import kamilzemczak.runny.service.TrainingService;
import kamilzemczak.runny.service.UserService;

@Controller
public class TrainingController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private TrainingService trainingService;

    @Autowired
    private UserService userService;

    @Autowired
    private TCommentService tCommentService;

    @RequestMapping(value = "/training_add", method = RequestMethod.POST, produces = "application/json")
    public String create(@ModelAttribute("userForm") User userForm, String sDistance, String sDuration, String notes, String hours, String mins) {
        Integer distance = Integer.valueOf(sDistance);
        Integer duration = Integer.valueOf(sDuration);
        User author = userRepository.findByUsername(userForm.getUsername());
        Integer age = author.getAge();
        Integer weight = author.getWeight();
        Integer heartRate = 160;
        Double calories = null;
        Integer iCalories = trainingService.calculateCalories(author, calories, age, weight, heartRate, duration);
        Training training = trainingService.create(author, distance, hours, mins, duration, iCalories, notes);
        trainingRepository.save(training);
        return "";
    }

    @RequestMapping(value = "/trainings_find", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    List<Training> find(User userForm) {
        boolean flag = true;
        User currentUser = userRepository.findByUsername(userForm.getUsername());
        List<Integer> userFriendsId = userService.getUserFriendsId(currentUser);
        List<Training> trainings = trainingService.findAll();
        List<Training> trainingsToSend = trainingService.find(trainings, flag, userFriendsId, currentUser);
        trainingService.clear(trainingsToSend);
        return trainingsToSend;
    }

    @RequestMapping(value = "/trainings_own_find", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    List<Training> findOwn(User userForm) {
        User currentUser = userRepository.findByUsername(userForm.getUsername());
        List<Training> trainings = trainingService.findAll();
        List<Training> trainingsToSend = trainingService.findOwn(trainings, currentUser);
        trainingService.clear(trainingsToSend);
        return trainingsToSend;
    }

    @RequestMapping(value = "/trainings_comment_size", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    List<Integer> size(User userForm) {
        boolean flag = true;
        User currentUser = userRepository.findByUsername(userForm.getUsername());
        List<Integer> userFriendsId = userService.getUserFriendsId(currentUser);
        List<Training> trainings = trainingService.findAll();
        List<Training> trainingsToSend = trainingService.find(trainings, flag, userFriendsId, currentUser);
        List<Integer> commentNumber = trainingService.getCommentSize(trainingsToSend);
        return commentNumber;
    }

    @RequestMapping(value = "/training_update", method = RequestMethod.POST, produces = "application/json")
    public String update(@ModelAttribute("userForm") User userForm, String sTrainingId, String sDistance, String sDuration, String notes, String hours, String mins) {
        Integer distance = Integer.valueOf(sDistance);
        Integer duration = Integer.valueOf(sDuration);
        Integer trainingId = Integer.valueOf(sTrainingId);
        Training training = trainingRepository.findById(trainingId);
        User author = userRepository.findByUsername(userForm.getUsername());
        Integer age = author.getAge();
        Integer weight = author.getWeight();
        Integer heartRate = 160;
        Double calories = null;
        Integer iCalories = trainingService.calculateCalories(author, calories, age, weight, heartRate, duration);
        trainingService.updateValues(author, distance, hours, mins, training, duration, iCalories, notes);
        trainingService.update(training);
        return "";
    }

    @RequestMapping(value = "/training_delete", method = RequestMethod.POST, produces = "application/json")
    public String delete(@ModelAttribute("userForm") User userForm, String sTrainingId) {
        Integer trainingId = Integer.valueOf(sTrainingId);
        List<TComment> tComments = tCommentService.findAll();
        tCommentService.deleteCommentWithTraining(tComments, trainingId);
        trainingRepository.delete(trainingId);
        return "";
    }
}
