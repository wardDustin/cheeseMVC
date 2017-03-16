package io.warddustin.model;

import java.util.Objects;

/**
 * Created by dward on 3/13/17.
 */
public class Cheese {
    private String name;
    private boolean smellsGood;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean doesSmellsGood() {
        return smellsGood;
    }

    public void setSmellsGood(boolean smellsGood) {
        this.smellsGood = smellsGood;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return name.equals(c.name)
                && smellsGood == c.doesSmellsGood();
    }

    @Override
    public int hashCode(){
        return Objects.hash(name, smellsGood, description);
    }

}
