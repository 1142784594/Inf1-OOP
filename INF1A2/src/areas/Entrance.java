package areas;

import animals.Animal;

import java.util.ArrayList;

/**
 * This class inherent from Class Area.
 * This class has type "entrance" and unique ID 0.
 */
public class Entrance extends Area {
    public Entrance(){
        super();
        type = "entrance";
        uniqueID = 0;
    }

    /**
     * Test whether given animal has been put into the wrong habitat.
     * @param animal The animal to be test.
     * @return false for all, since entrance cannot contain any animal.
     */
    public boolean wrongHabitat(Animal animal){
        return true;
    }
}
