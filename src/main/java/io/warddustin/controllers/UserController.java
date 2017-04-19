package io.warddustin.controllers;

import io.warddustin.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Created by dward on 3/25/17.
 */
@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("title", "User Signup");
        model.addAttribute(new User());
        return "user/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String add(Model model,  @ModelAttribute @Valid User user, Errors errors){
        if (errors.hasErrors()){
            model.addAttribute("title", "User Signup");
            user.setPassword("");
            return "user/add";
        }

        model.addAttribute("title", "User Page");
        model.addAttribute("success", "Congratulations " + user.getUsername() + ", you have been successfully signed up!");
        model.addAttribute(user);
        return "user/index";
    }
}
