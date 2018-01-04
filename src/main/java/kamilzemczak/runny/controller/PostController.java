package kamilzemczak.runny.controller;

import java.util.ArrayList;
import java.util.List;
import kamilzemczak.runny.dao.PostRepository;
import kamilzemczak.runny.dao.UserRepository;
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
public class PostController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/post_send", method = RequestMethod.POST, produces = "application/json")
    public String send(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model, String contents) {
        User author = new User();
        author = userRepository.findByUsername(userForm.getUsername());

        Post post = new Post();
        post.setAuthor(author);
        post.setContents(contents);
        
        postRepository.save(post);
        return "";
    }

    @RequestMapping(value = "/posts_find", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    List<Post> find(Model model, User userForm) {
        boolean test = true;
        User currentUser = new User();
        currentUser = userRepository.findByUsername(userForm.getUsername());

        List<User> userFriends = currentUser.getFriends();
        List<Integer> myList = new ArrayList<>();

        for (User friend : userFriends) {
            myList.add(friend.getId());
        }

        List<Post> posts = new ArrayList<>();
        List<Post> postsToSend = new ArrayList<>();

        posts = postService.findAll();

        for (Post post : posts) {
            test = true;
            for (Integer postAthuorId : myList) {
                if ((post.getAuthor().getId().equals(postAthuorId)) || (post.getAuthor().getId().equals(currentUser.getId())) && test) {
                      postsToSend.add(post);
                      test = false;
                }
            }
        }

        for (Post posts2 : postsToSend) {
               
                      posts2.getAuthor().getFriends().clear();
                      if(posts2.getComments()!=null) {
                          posts2.getComments().clear();
                      }
              
            }
        return postsToSend;
    }
    
    @RequestMapping(value = "/posts_comment_size", method = RequestMethod.POST, produces = "application/json")
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

        List<Post> posts = new ArrayList<>();
        List<Post> postsToSend = new ArrayList<>();

        posts = postService.findAll();

        for (Post post : posts) {
            test = true;
            for (Integer postAthuorId : myList) {
                if ((post.getAuthor().getId().equals(postAthuorId)) || (post.getAuthor().getId().equals(currentUser.getId())) && test) {
                      postsToSend.add(post);
                      test = false;
                }
            }
        }
        
        List<Integer> commentNumber = new ArrayList<>();
        for (Post posts2 : postsToSend) {
               
                      posts2.getAuthor().getFriends().clear();
                      if(posts2.getComments()!=null) {
                        commentNumber.add(posts2.getComments().size());
                         posts2.getComments().clear();
                      }
              
            }
        
       
       
        return commentNumber;
    }
}
