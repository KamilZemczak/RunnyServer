package kamilzemczak.runny.controller;

import java.util.ArrayList;
import java.util.List;
import kamilzemczak.runny.dao.TrainingRepository;
import kamilzemczak.runny.dao.UserRepository;
import kamilzemczak.runny.model.Post;
import kamilzemczak.runny.model.Training;
import kamilzemczak.runny.model.User;
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
public class TrainingController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private TrainingService trainingService;

    @RequestMapping(value = "/training_add", method = RequestMethod.POST, produces = "application/json")
    public String send(@ModelAttribute("userForm") User userForm, String sdistance, String sduration, String notes, String hours, String mins) {
        User author = new User();
        author = userRepository.findByUsername(userForm.getUsername());
        Integer age = author.getAge();
        Integer weight = author.getWeight();

        Integer heartRate = 160;
        Integer distance = Integer.valueOf(sdistance);
        Integer duration = Integer.valueOf(sduration);

        Double calories = null;
        if (author.getGender().equals("M")) {
            calories = (((age * 0.2017) - (weight * 0.09036) + (heartRate * 0.6309) - 55.0969) * (duration / 4.184));
        } else if (author.getGender().equals("F")) {
            calories = (((age * 0.074) - (weight * 0.05741) + (heartRate * 0.4472) - 20.4022) * (duration / 4.184));
        }

        Math.round(calories);
        Integer caloriesi = calories.intValue();

        String contents = null;
        if (author.getGender().equals("M")) {
            contents = author.getName() + " " + author.getSurname() + " " + "przebiegł" + " " + distance + "km" + " " + "w" + " " + hours + "g" + ":" + mins + "m.";
        } else if (author.getGender().equals("F")) {
            contents = author.getName() + " " + author.getSurname() + " " + "przebiegła" + " " + distance + "km" + " " + "w" + " " + hours + "g" + ":" + mins + "m.";
        }

        Training training = new Training();
        training.setAuthor(author);
        training.setDuration(duration);
        training.setDistance(distance);
        training.setCalories(caloriesi);
        training.setContents(contents);
        if (!notes.isEmpty()) {
            training.setNotes(notes);
        }

        trainingRepository.save(training);
        return "";
    }

    @RequestMapping(value = "/trainings_find", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    List<Training> find(Model model, User userForm) {
        boolean test = true;
        User currentUser = new User();
        currentUser = userRepository.findByUsername(userForm.getUsername());

        List<User> userFriends = currentUser.getFriends();
        List<Integer> myList = new ArrayList<>();

        for (User friend : userFriends) {
            myList.add(friend.getId());
        }

        List<Training> trainings = new ArrayList<>();
        List<Training> trainingsToSend = new ArrayList<>();

        trainings = trainingService.findAll();

        for (Training training : trainings) {
            test = true;
            for (Integer postAthuorId : myList) {
                if ((training.getAuthor().getId().equals(postAthuorId)) || (training.getAuthor().getId().equals(currentUser.getId())) && test) {
                    trainingsToSend.add(training);
                    test = false;
                }
            }
        }

        for (Training trainigs2 : trainingsToSend) {

            trainigs2.getAuthor().getFriends().clear();
            if (trainigs2.getTcomments() != null) {
                trainigs2.getTcomments().clear();
            }

        }
        return trainingsToSend;
    }

    @RequestMapping(value = "/trainings_own_find", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    List<Training> find_own(Model model, User userForm) {
        User currentUser = new User();
        currentUser = userRepository.findByUsername(userForm.getUsername());

        List<Training> trainings = new ArrayList<>();
        List<Training> trainingsToSend = new ArrayList<>();
        trainings = trainingService.findAll();

        for (Training training : trainings) {
            if (training.getAuthor().getId().equals(currentUser.getId())) {
                trainingsToSend.add(training);
            }
        }

        for (Training training2 : trainingsToSend) {

            training2.getAuthor().getFriends().clear();
            if (training2.getTcomments() != null) {
                training2.getTcomments().clear();
            }

        }
        return trainingsToSend;
    }

    @RequestMapping(value = "/trainings_comment_size", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    List<Integer> comment_size(Model model, User userForm) {
        boolean test = true;
        User currentUser = new User();
        currentUser = userRepository.findByUsername(userForm.getUsername());

        List<User> userFriends = currentUser.getFriends();
        List<Integer> myList = new ArrayList<>();

        for (User friend : userFriends) {
            myList.add(friend.getId());
        }

        List<Training> trainings = new ArrayList<>();
        List<Training> trainingsToSend = new ArrayList<>();

        trainings = trainingService.findAll();

        for (Training training : trainings) {
            test = true;
            for (Integer postAthuorId : myList) {
                if ((training.getAuthor().getId().equals(postAthuorId)) || (training.getAuthor().getId().equals(currentUser.getId())) && test) {
                    trainingsToSend.add(training);
                    test = false;
                }
            }
        }

        List<Integer> commentNumber = new ArrayList<>();
        for (Training trainings2 : trainingsToSend) {

            trainings2.getAuthor().getFriends().clear();
            if (trainings2.getTcomments() != null) {
                commentNumber.add(trainings2.getTcomments().size());
                trainings2.getTcomments().clear();
            }

        }

        return commentNumber;
    }
}
