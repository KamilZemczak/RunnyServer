package kamilzemczak.runny.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kamilzemczak.runny.dao.TrainingRepository;
import kamilzemczak.runny.model.Training;
import kamilzemczak.runny.model.User;

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

    public List<Training> getUserTrainings(List<Training> trainings, User currentUser) {
        List<Training> userTrainings = new ArrayList<>();
        for (Training training : trainings) {
            if (training.getAuthor().getId().equals(currentUser.getId())) {
                userTrainings.add(training);
            }
        }
        return userTrainings;
    }

    public Integer calculateCalories(User author, Double calories, Integer age, Integer weight, Integer heartRate, Integer duration) {
        if (author.getGender().equals("M")) {
            calories = (((age * 0.2017) - (weight * 0.09036) + (heartRate * 0.6309) - 55.0969) * (duration / 4.184));
        } else if (author.getGender().equals("F")) {
            calories = (((age * 0.074) - (weight * 0.05741) + (heartRate * 0.4472) - 20.4022) * (duration / 4.184));
        }
        Math.round(calories);
        Integer iCalories = calories.intValue();
        return iCalories;
    }

    public Training create(User author, Integer distance, String hours, String mins, Integer duration, Integer iCalories, String notes) {
        Training training = new Training();
        String contents = null;
        if (author.getGender().equals("M")) {
            contents = author.getName() + " " + author.getSurname() + " " + "przebiegł" + " " + distance + "km" + " " + "w" + " " + hours + "g" + ":" + mins + "m.";
        } else if (author.getGender().equals("F")) {
            contents = author.getName() + " " + author.getSurname() + " " + "przebiegła" + " " + distance + "km" + " " + "w" + " " + hours + "g" + ":" + mins + "m.";
        }
        training.setAuthor(author);
        training.setDuration(duration);
        training.setDistance(distance);
        training.setCalories(iCalories);
        training.setContents(contents);
        if (!notes.isEmpty()) {
            training.setNotes(notes);
        }
        return training;
    }

    public List<Training> find(List<Training> trainings, boolean flag, List<Integer> userFriendsId, User currentUser) {
        List<Training> trainingsToSend = new ArrayList<>();
        for (Training training : trainings) {
            flag = true;
            for (Integer postAthuorId : userFriendsId) {
                if ((training.getAuthor().getId().equals(postAthuorId)) || (training.getAuthor().getId().equals(currentUser.getId())) && flag) {
                    trainingsToSend.add(training);
                    flag = false;
                }
            }
        }
        return trainingsToSend;
    }

    public void clear(List<Training> trainingsToSend) {
        for (Training trainigs : trainingsToSend) {
            trainigs.getAuthor().getFriends().clear();
            if (trainigs.getTcomments() != null) {
                trainigs.getTcomments().clear();
            }

        }
    }

    public List<Training> findOwn(List<Training> trainings, User currentUser) {
        List<Training> trainingsToSend = new ArrayList<>();
        for (Training training : trainings) {
            if (training.getAuthor().getId().equals(currentUser.getId())) {
                trainingsToSend.add(training);
            }
        }
        return trainingsToSend;
    }

    public List<Integer> getCommentSize(List<Training> trainingsToSend) {
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
