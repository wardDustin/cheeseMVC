package io.warddustin.controllers;

import io.warddustin.model.Cheese;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by dward on 3/9/17.
 */

@Controller
@RequestMapping("cheese")
public class CheeseController {

    static ArrayList<Cheese> cheeseList = new ArrayList<>();

    @RequestMapping("")
    public String index(Model model){
        model.addAttribute("cheeses", cheeseList);
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model){
        model.addAttribute("title", "Add Cheese");
        model.addAttribute("error", "");
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@RequestParam String cheeseName, @RequestParam String cheeseDescription, Model model){
        if (cheeseName == "" || !cheeseName.matches("[a-zA-Z\\s]+")){
            String error = "Cheese names only contain characters";
            if (cheeseName == "")
                error = "You did not specify a cheese!";

            model.addAttribute("title", "Add Cheese");
            model.addAttribute("error", error);
            return "cheese/add";
        }
        Cheese cheese = new Cheese();
        cheese.setName(cheeseName);
        cheese.setDescription(cheeseDescription);

        cheeseList.add(cheese);
        return "redirect:";
    }

    @RequestMapping(value="remove", method = RequestMethod.GET)
    public String displayRemoveCheesesForm(Model model){
        model.addAttribute("cheeses", cheeseList);
        model.addAttribute("title", "Remove Cheese");
        model.addAttribute("error", "");
        return "cheese/remove";
    }

    @RequestMapping(value="remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam String cheeseName, Model model){
        Cheese cheese = new Cheese();
        cheese.setName(cheeseName);


        for (Cheese singleCheese: cheeseList){
            if (singleCheese.equals(cheese)){
                cheeseList.remove(cheese);
                return "redirect:";
            }
        }

        model.addAttribute("cheeses", cheeseList);
        model.addAttribute("title", "Remove Cheese");
        model.addAttribute("error", "This cheese does not exist in the list of cheeses!");
        return "/cheese/remove";

    }
}
