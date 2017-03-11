package io.warddustin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by dward on 3/9/17.
 */

@Controller
@RequestMapping("cheese")
public class CheeseController {

    static ArrayList<String> cheeseList = new ArrayList<>();

    @RequestMapping("")
    public String index(Model model){
        model.addAttribute("cheeses", cheeseList);
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model){
        model.addAttribute("title", "Add Cheese");
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@RequestParam String cheeseName){
        cheeseList.add(cheeseName);
        return "redirect:";
    }
}
