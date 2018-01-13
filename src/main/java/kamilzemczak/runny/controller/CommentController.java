package kamilzemczak.runny.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kamilzemczak.runny.dao.CommentRepository;
import kamilzemczak.runny.dao.PostRepository;
import kamilzemczak.runny.dao.UserRepository;
import kamilzemczak.runny.model.Comment;
import kamilzemczak.runny.model.Post;
import kamilzemczak.runny.model.User;
import kamilzemczak.runny.service.CommentService;
import kamilzemczak.runny.service.PostService;

@Controller
public class CommentController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/comment_send", method = RequestMethod.POST, produces = "application/json")
    public String create(@ModelAttribute("userForm") User userForm, String contents, String sPostId) {
        Integer postId = Integer.valueOf(sPostId);
        User author = userRepository.findByUsername(userForm.getUsername());
        Post postToModify = postRepository.findById(postId);
        List<Comment> comments = new ArrayList<>();
        Comment comment = commentService.create(author, contents, postToModify, comments);
        commentService.modifyPost(postToModify, comments, comment);
        commentRepository.save(comment);
        postService.update(postToModify);
        return "";
    }

    @RequestMapping(value = "/comments_find", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    List<Comment> find(String sPostId) {
        Integer postId = Integer.valueOf(sPostId);
        List<Comment> allComments = commentRepository.findAll();
        List<Comment> commentsToSend = new ArrayList<>();
        commentService.find(allComments, postId, commentsToSend);
        return commentsToSend;
    }
    
    @RequestMapping(value = "/comment_update", method = RequestMethod.POST, produces = "application/json")
    public String update(String sCommentId, String content) {
        Integer commentId = Integer.valueOf(sCommentId);
        Comment comment = commentRepository.findById(commentId);
        comment.setContents(content);
        commentService.update(comment);
        return "";
    }

    @RequestMapping(value = "/comment_delete", method = RequestMethod.POST, produces = "application/json")
    public String delete(String sCommentId) {
        Integer commentId = Integer.valueOf(sCommentId);
        Comment comment = commentRepository.findById(commentId);
        commentRepository.delete(comment);
        return "";
    }
}
