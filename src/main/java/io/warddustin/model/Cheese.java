package io.warddustin.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Created by dward on 3/13/17.
 */
public class Cheese {

    @NotNull
    @Size(min=3, max=25, message="The cheese name must be between 3 and 25 characters")
    private String name;

    @NotNull
    @Size(min=1, message = "Description cannot be empty")
    private String description;

    @Min(value = 1, message = "The value must be between 1 and 5")
    @Max(value = 5, message = "The value must be between 1 and 5")
    private int rating;

    private int cheeseId;
    private static int nextId = 1;
    private CheeseType type;

    public Cheese(String name, String description) {
        this();
        this.name = name;
        this.description = description;
    }

    public Cheese(){
        cheeseId = nextId;
        nextId++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCheeseId() {
        return cheeseId;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }

    public CheeseType getType() {
        return type;
    }

    public void setType(CheeseType type) {
        this.type = type;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString(){
        return this.getName();
    }

    @Override
    public boolean equals(Object obj){
        // If the object is compared with itself then return true
        if (obj == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(obj instanceof Cheese)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Cheese c = (Cheese) obj;

        // Compare the data members and return accordingly
        return cheeseId == c.cheeseId;
    }

    @Override
    public int hashCode(){
        return Objects.hash(name, description, cheeseId);
    }

}
