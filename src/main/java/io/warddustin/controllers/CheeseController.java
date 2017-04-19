package io.warddustin.controllers;

import io.warddustin.model.Cheese;
import io.warddustin.model.CheeseData;
import io.warddustin.model.CheeseType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

/**
 * Created by dward on 3/9/17.
 */

@Controller
@RequestMapping("cheese")
public class CheeseController {

    @RequestMapping("")
    public String index(Model model){
        model.addAttribute("cheeses", CheeseData.getAllCheeses());
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model){
        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese());
        model.addAttribute("cheeseTypes", CheeseType.values());
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute @Valid Cheese cheese, Errors errors, Model model){

        if (errors.hasErrors()){
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("cheeseTypes", CheeseType.values());
            return "cheese/add";
        }

        CheeseData.add(cheese);
        model.addAttribute("title", "My Cheeses");
        model.addAttribute("cheeses", CheeseData.getAllCheeses());
        model.addAttribute("success", "You successfully added the Cheese " + cheese.getName());
        return "/cheese/index";
    }

    @RequestMapping(value="remove", method = RequestMethod.GET)
    public String displayRemoveCheesesForm(Model model){
        model.addAttribute("cheeses", CheeseData.getAllCheeses());
        model.addAttribute("title", "Remove Cheese");
        return "cheese/remove";
    }

    @RequestMapping(value="remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds, Model model){

        ArrayList<Cheese> cheeseList = new ArrayList<>();
        for (int cheeseId : cheeseIds){
            cheeseList.add(CheeseData.getById(cheeseId));
            CheeseData.remove(cheeseId);
        }

        model.addAttribute("title", "My Cheeses");
        model.addAttribute("cheeses", CheeseData.getAllCheeses());
        model.addAttribute("success", "You have successfully removed " + cheeseList);
        return "/cheese/index";

    }

    @RequestMapping(value="edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable("cheeseId") int cheeseId){
        Cheese cheese = CheeseData.getById(cheeseId);
        model.addAttribute("cheeseTypes", CheeseType.values());
        model.addAttribute("title", "Edit Cheese");
        model.addAttribute(cheese);
        return "cheese/edit";
    }

    @RequestMapping(value="edit/{cheese.cheeseId}", method = RequestMethod.POST)
    public String processEditForm(@ModelAttribute @Valid Cheese cheese, Errors errors, Model model){

        CheeseData.remove(cheese.getCheeseId());

        if (errors.hasErrors()){
            model.addAttribute("title", "Edit Cheese");
            model.addAttribute("cheeseTypes", CheeseType.values());
            return "cheese/edit";
        }

        CheeseData.add(cheese);
        model.addAttribute("cheeseTypes", CheeseType.values());
        model.addAttribute("cheeses", CheeseData.getAllCheeses());
        model.addAttribute("success", "You have successfully edited " + cheese.getName());
        return "/cheese/index";
    }
}
