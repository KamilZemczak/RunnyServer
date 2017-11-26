/**
 * ***********************************************************
 * Autorskie Prawa Majątkowe Kamil Zemczak
 *
 * Copyright 2017 Kamil Zemczak
 * ************************************************************
 * Utworzono 26-10-2017, Kamil Zemczak
 * ************************************************************
 */
package kamilzemczak.runny.controller;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import kamilzemczak.runny.dao.RoleRepository;
import kamilzemczak.runny.dao.UserRepository;
import kamilzemczak.runny.model.Role;
import kamilzemczak.runny.model.User;
import kamilzemczak.runny.service.SecurityService;
import kamilzemczak.runny.service.UserService;
import kamilzemczak.runny.validator.UserValidator;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST, produces = "application/json")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findOne(1));
        userForm.setRoles(roles);
        userService.save(userForm);

        // securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
        return "";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Niewłaściwe hasło lub login.");
            return "Wrong.";
        }

        if (logout != null) {
            model.addAttribute("message", "Wylogowanie powiodło sie.");
            return "Wrong2.";
        }

        return "Zalogowanie udane.";
    }

    @RequestMapping(value = "/user_details", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    User userDetails(Model model, User userForm) {
        User userToGet = new User();
        User userToSet = new User();
        if (userRepository.findByUsername(userForm.getUsername()) != null) {
            userToGet = userRepository.findByUsername(userForm.getUsername());
        }
        Integer id = userToGet.getId();
        String name = userToGet.getName();
        String surname = userToGet.getSurname();
        String username = userToGet.getUsername();
        String email = userToGet.getEmail();
        Integer age = userToGet.getAge();
        String gender = userToGet.getGender();
        Integer weight = null;
        Integer height = null;
        String city = null;
        String about = null;

        if (userToGet.getWeight() != null) {
            weight = userToGet.getWeight();
        }
        if (userToGet.getHeight() != null) {
            height = userToGet.getHeight();
        }
        if (userToGet.getCity() != null) {
            city = userToGet.getCity();
        }
        if (userToGet.getAbout() != null) {
            about = userToGet.getAbout();
        }

        userToSet.setId(id);
        userToSet.setName(name);
        userToSet.setSurname(surname);
        userToSet.setUsername(username);
        userToSet.setEmail(email);
        userToSet.setAge(age);
        userToSet.setGender(gender);
        userToSet.setWeight(weight);
        userToSet.setHeight(height);
        userToSet.setCity(city);
        userToSet.setAbout(about);

        return userToSet;
    }

    @RequestMapping(value = "/user_update", method = RequestMethod.POST, produces = "application/json")
    public String userUpdate(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        User userToUpdate = new User();
        userToUpdate = userRepository.findById(userForm.getId());
        userToUpdate.setUsername(userForm.getUsername());
        userToUpdate.setAge(userForm.getAge());
        if (userForm.getWeight() != null) {
            userToUpdate.setWeight(userForm.getWeight());
        }

        if (userForm.getHeight() != null) {
            userToUpdate.setHeight(userForm.getHeight());
        }

        if (userForm.getCity() != null) {
            userToUpdate.setCity(userForm.getCity());
        }

        if (userForm.getAbout() != null) {
            userToUpdate.setAbout(userForm.getAbout());
        }
        userService.update(userToUpdate);
        return "";
    }
}

/**
 * @RequestMapping(value = "/login", method = RequestMethod.GET, produces =
 * "application/json") public @ResponseBody User login(Model model, String
 * error, String logout, User userForm) {
 * if(userRepository.findByUsername(userForm.getUsername())!=null) {
 * System.out.println(); }
 *
 * if (error != null) { model.addAttribute("error", "Niewłaściwe hasło lub
 * login."); // return "Niewłaściwe hasło lub login."; }
 *
 * if (logout != null) { model.addAttribute("message", "Wylogowanie powiodło
 * sie."); //return "Wylogowanie powiodło się."; }
 *
 * userForm.getAge(); userForm.getEmail(); userForm.getName();
 * userForm.getSurname(); userForm.getGender(); //user.setAge(55); //return
 * user;
 */
// return userForm;
//return "Zalogowanie udane.";
    // }
