package kamilzemczak.runny.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kamilzemczak.runny.dao.PostRepository;
import kamilzemczak.runny.dao.UserRepository;
import kamilzemczak.runny.model.Post;
import kamilzemczak.runny.model.User;
import kamilzemczak.runny.service.CommentService;
import kamilzemczak.runny.service.PostService;
import kamilzemczak.runny.service.UserService;

@Controller
public class PostController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/post_send", method = RequestMethod.POST, produces = "application/json")
    public String creat(@ModelAttribute("userForm") User userForm, String contents) {
        User author = userRepository.findByUsername(userForm.getUsername());
        Post post = postService.create(author, contents);
        postRepository.save(post);
        return "";
    }

    @RequestMapping(value = "/posts_find", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    List<Post> find(User userForm) {
        boolean flag = true;
        User currentUser = userRepository.findByUsername(userForm.getUsername());
        List<Integer> userFriendsId = userService.getUserFriendsId(currentUser);
        List<Post> posts = postService.findAll();
        List<Post> postsToSend = postService.find(posts, flag, userFriendsId, currentUser);
        postService.clear(postsToSend);
        return postsToSend;
    }

    @RequestMapping(value = "/posts_comment_size", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    List<Integer> size(User userForm) {
        boolean flag = true;
        User currentUser = userRepository.findByUsername(userForm.getUsername());
        List<Integer> userFriendsId = userService.getUserFriendsId(currentUser);
        List<Post> posts = postService.findAll();
        List<Post> postsToSend = postService.find(posts, flag, userFriendsId, currentUser);
        List<Integer> commentNumber = commentService.getCommentSize(postsToSend);
        return commentNumber;
    }
}
