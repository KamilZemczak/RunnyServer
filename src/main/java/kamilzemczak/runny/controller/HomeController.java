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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import kamilzemczak.runny.Message;
import kamilzemczak.runny.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;


@Controller
@RequestMapping("/*")
public class HomeController {
    
   


    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        logger.info("Spring Android Basic Auth");
        return "home";
    }

    @RequestMapping(value = "/getmessage", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Message getMessage() {
        logger.info("Accessing protected resource");
        return new Message(100, "Congratulations!", "w koncu sie udalo!");
    }

}
