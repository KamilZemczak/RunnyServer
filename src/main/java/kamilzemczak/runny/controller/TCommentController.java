package kamilzemczak.runny.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kamilzemczak.runny.dao.TCommentRepository;
import kamilzemczak.runny.dao.TrainingRepository;
import kamilzemczak.runny.dao.UserRepository;
import kamilzemczak.runny.model.Comment;
import kamilzemczak.runny.model.TComment;
import kamilzemczak.runny.model.Training;
import kamilzemczak.runny.model.User;
import kamilzemczak.runny.service.TCommentService;
import kamilzemczak.runny.service.TrainingService;

@Controller
public class TCommentController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private TCommentRepository tCommentRepository;

    @Autowired
    private TrainingService trainingService;

    @Autowired
    private TCommentService tCommentService;

    @RequestMapping(value = "/tcomment_send", method = RequestMethod.POST, produces = "application/json")
    public String create(@ModelAttribute("userForm") User userForm, String contents, String sTrainingId) {
        Integer trainingId = Integer.valueOf(sTrainingId);
        User author = userRepository.findByUsername(userForm.getUsername());
        Training trainingToModify = trainingRepository.findById(trainingId);
        List<TComment> comments = new ArrayList<>();
        TComment tComment = tCommentService.create(author, contents, trainingToModify, comments);
        tCommentService.clear(trainingToModify, comments, tComment);
        tCommentRepository.save(tComment);
        trainingService.update(trainingToModify);
        return "";
    }

    @RequestMapping(value = "/tcomments_find", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    List<TComment> find(User userForm, String sTrainingId) {
        Integer trainingId = Integer.valueOf(sTrainingId);
        List<TComment> allTrainingComments = tCommentRepository.findAll();
        List<TComment> trainingsToSend = tCommentService.find(allTrainingComments, trainingId);
        return trainingsToSend;
    }
    
    @RequestMapping(value = "/tcomment_update", method = RequestMethod.POST, produces = "application/json")
    public String update(String sCommentId, String content) {
        Integer commentId = Integer.valueOf(sCommentId);
        TComment tComment = tCommentRepository.findById(commentId);
        tComment.setContents(content);
        tCommentService.update(tComment);
        return "";
    }

    @RequestMapping(value = "/tcomment_delete", method = RequestMethod.POST, produces = "application/json")
    public String delete(String sCommentId) {
        Integer commentId = Integer.valueOf(sCommentId);
        TComment tComment = tCommentRepository.findById(commentId);
        tCommentRepository.delete(tComment);
        return "";
    }
}
