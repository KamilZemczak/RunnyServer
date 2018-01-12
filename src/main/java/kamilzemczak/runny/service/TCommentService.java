package kamilzemczak.runny.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kamilzemczak.runny.dao.TCommentRepository;
import kamilzemczak.runny.model.TComment;
import kamilzemczak.runny.model.Training;
import kamilzemczak.runny.model.User;

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

    public TComment create(User author, String contents, Training trainingToModify, List<TComment> comments) {
        TComment tComment = new TComment();
        tComment.setAuthor(author);
        tComment.setContents(contents);
        tComment.setTraining(trainingToModify);
        comments.add(tComment);
        return tComment;
    }

    public void clear(Training trainingToModify, List<TComment> comments, TComment tComment) {
        if (trainingToModify.getTcomments() == null) {
            trainingToModify.setTcomments(comments);
        } else {
            trainingToModify.getTcomments().add(tComment);
        }
    }

    public void find(List<TComment> allTrainingComments, Integer trainingId, List<TComment> trainingsToSend) {
        for (TComment tComment : allTrainingComments) {
            if (tComment.getAuthor().getFriends() != null) {
                tComment.getAuthor().getFriends().clear();
                tComment.getTraining().getAuthor().getFriends().clear();
            }
            if (tComment.getTraining().getId().equals(trainingId)) {
                trainingsToSend.add(tComment);
            }
            tComment.getTraining().getTcomments().clear();
        }
    }

    public List<TComment> find(List<TComment> allTrainingComments, Integer trainingId) {
        List<TComment> trainingsToSend = new ArrayList<>();
        for (TComment tComment : allTrainingComments) {
            if (tComment.getAuthor().getFriends() != null) {
                tComment.getAuthor().getFriends().clear();
                tComment.getTraining().getAuthor().getFriends().clear();
            }
            if (tComment.getTraining().getId().equals(trainingId)) {
                trainingsToSend.add(tComment);
            }
            tComment.getTraining().getTcomments().clear();
        }
        return trainingsToSend;
    }
}
