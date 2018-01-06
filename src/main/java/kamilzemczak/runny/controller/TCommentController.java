package kamilzemczak.runny.controller;

import java.util.ArrayList;
import java.util.List;
import kamilzemczak.runny.dao.TCommentRepository;
import kamilzemczak.runny.dao.TrainingRepository;
import kamilzemczak.runny.dao.UserRepository;
import kamilzemczak.runny.model.Comment;
import kamilzemczak.runny.model.Post;
import kamilzemczak.runny.model.TComment;
import kamilzemczak.runny.model.Training;
import kamilzemczak.runny.model.User;
import kamilzemczak.runny.service.TCommentService;
import kamilzemczak.runny.service.TrainingService;
import kamilzemczak.runny.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TCommentController {

    @Autowired
    private UserService userService;

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
    public String send(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model, String contents, String trainingId) {
        Integer trainingId2 = Integer.valueOf(trainingId);

        User author = new User();
        author = userRepository.findByUsername(userForm.getUsername());

        Training trainingToModify = new Training();
        trainingToModify = trainingRepository.findById(trainingId2);

        List<TComment> comments = new ArrayList<>();
        TComment tComment = new TComment();
        tComment.setAuthor(author);
        tComment.setContents(contents);
        tComment.setTraining(trainingToModify);
        comments.add(tComment);

        if (trainingToModify.getTcomments() == null) { //TODO: ma już w znajomych gościa
            trainingToModify.setTcomments(comments);
        } else {
            trainingToModify.getTcomments().add(tComment);
        }

        tCommentRepository.save(tComment);
        trainingService.update(trainingToModify);
        return "";
    }

    @RequestMapping(value = "/tcomments_find", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    List<TComment> find(Model model, User userForm, String trainingId) {
        Integer trainingID2 = Integer.valueOf(trainingId);
        Training test2 = new Training();
        test2 = trainingRepository.findById(trainingID2);

 
         List<TComment> blbl = new ArrayList<>();
         
         List<TComment> blblToSend = new ArrayList<>();
         
         blbl = tCommentRepository.findAll();
         
         for(TComment tComment : blbl) {
             if(tComment.getAuthor().getFriends()!=null) {
                 tComment.getAuthor().getFriends().clear();
                  tComment.getTraining().getAuthor().getFriends().clear();
             }
             if(tComment.getTraining().getId().equals(trainingID2)) {
                 blblToSend.add(tComment);
             }
             tComment.getTraining().getTcomments().clear();
         }

        return blblToSend;
    }
}
