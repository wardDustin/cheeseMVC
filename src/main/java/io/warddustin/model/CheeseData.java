package io.warddustin.model;

import java.util.ArrayList;

/**
 * Created by dward on 3/22/17.
 */
public class CheeseData {

    static ArrayList<Cheese> cheeseList = new ArrayList<>();

    public static ArrayList<Cheese> getAllCheeses(){
        return cheeseList;
    }

    public static void add(Cheese newCheese){
        cheeseList.add(newCheese);
    }

    public static void remove(int id){
        Cheese cheese = getById(id);
        if (cheese != null)
            cheeseList.remove(cheese);
    }

    public static Cheese getById(int id){
        for (Cheese cheese : cheeseList){
            if (cheese.getCheeseId() == id){
                return cheese;
            }
        }
        return null;
    }
}
