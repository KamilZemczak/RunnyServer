package kamilzemczak.runny.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kamilzemczak.runny.dao.ObjectiveRepository;
import kamilzemczak.runny.model.Objective;
import kamilzemczak.runny.model.Training;
import kamilzemczak.runny.model.User;

@Service
public class ObjectiveService {

    @Autowired
    private ObjectiveRepository objectiveRepository;

    @Autowired
    private ObjectiveService objectiveService;

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

    public Objective create(User currentUser, String type, String objective) {
        Objective objectiveToSave = new Objective();
        objectiveToSave.setAuthor(currentUser);
        objectiveToSave.setType(type);
        objectiveToSave.setObjective(objective);
        objectiveToSave.setExecuted("N");
        return objectiveToSave;
    }

    public List<Objective> find(List<Objective> allObjectives, User currentUser) {
        List<Objective> objectivesToSend = new ArrayList<>();
        for (Objective objective : allObjectives) {
            if (objective.getAuthor().getId().equals(currentUser.getId())) {
                objectivesToSend.add(objective);
            }
        }
        return objectivesToSend;
    }

    public void clear(List<Objective> objectivesToSend) {
        for (Objective objective : objectivesToSend) {
            if (objective.getAuthor().getFriends() != null) {
                objective.getAuthor().getFriends().clear();
            }
        }
    }

    public void getUserObjectives(List<Objective> objectives, User currentUser, List<Objective> userObjectives) {
        for (Objective objective : objectives) {
            if (objective.getAuthor().getId().equals(currentUser.getId())) {
                userObjectives.add(objective);
            }

        }
    }

    public void updateObjective(List<Objective> userObjectives, List<Training> userTrainings) throws NumberFormatException {
        for (Objective objective : userObjectives) {
            if (objective.getExecuted().equals("N")) {
                for (Training training : userTrainings) {
                    String nameOfObjective = objective.getObjective();
                    if (nameOfObjective.endsWith("km")) {
                        String objectiveToProcess = nameOfObjective.replace("km", "");
                        Integer objectivei = Integer.valueOf(objectiveToProcess);
                        if (objectivei <= training.getDistance() && training.getTime().after(objective.getTime())) {
                            objective.setExecuted("Y");
                            objectiveService.update(objective);
                        }
                    } else if (nameOfObjective.endsWith("kcal")) {
                        String objectiveToProcess = nameOfObjective.replace("kcal", "");
                        Integer objectivei = Integer.valueOf(objectiveToProcess);
                        if (objectivei <= training.getCalories() && training.getTime().after(objective.getTime())) {
                            objective.setExecuted("Y");
                            objectiveService.update(objective);
                        }
                    }
                }
            }
        }
    }
}
