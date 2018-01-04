package kamilzemczak.runny.controller;

import java.util.ArrayList;
import java.util.List;
import kamilzemczak.runny.dao.CommentRepository;
import kamilzemczak.runny.dao.PostRepository;
import kamilzemczak.runny.dao.UserRepository;
import kamilzemczak.runny.model.Comment;
import kamilzemczak.runny.model.Post;
import kamilzemczak.runny.model.User;
import kamilzemczak.runny.service.PostService;
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
public class CommentController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/comment_send", method = RequestMethod.POST, produces = "application/json")
    public String send(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model, String contents, String postId) {
        Integer postId2 = Integer.valueOf(postId);

        User author = new User();
        author = userRepository.findByUsername(userForm.getUsername());

        Post postToModify = new Post();
        postToModify = postRepository.findById(postId2);

        List<Comment> comments = new ArrayList<>();
        Comment comment = new Comment();
        comment.setAuthor(author);
        comment.setContents(contents);
        comment.setPost(postToModify);
        comments.add(comment);

        if (postToModify.getComments() == null) { //TODO: ma już w znajomych gościa
            postToModify.setComments(comments);
        } else {
            postToModify.getComments().add(comment);
        }

        commentRepository.save(comment);
        postService.update(postToModify);
        return "";
    }

    @RequestMapping(value = "/comments_find", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    List<Comment> find(Model model, User userForm, String postId) {
        Integer postId2 = Integer.valueOf(postId);
        Post test2 = new Post();
        test2 = postRepository.findById(postId2);

 
         List<Comment> blbl = new ArrayList<>();
         
         List<Comment> blblToSend = new ArrayList<>();
         
         blbl = commentRepository.findAll();
         
         for(Comment comment : blbl) {
             if(comment.getAuthor().getFriends()!=null) {
                 comment.getAuthor().getFriends().clear();
                  comment.getPost().getAuthor().getFriends().clear();
             }
             if(comment.getPost().getId().equals(postId2)) {
                 blblToSend.add(comment);
             }
             comment.getPost().getComments().clear();
         }

        return blblToSend;
    }
}
