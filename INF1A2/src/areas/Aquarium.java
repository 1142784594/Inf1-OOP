package areas;

import animals.Animal;

import java.util.ArrayList;

/**
 * This class inherent from Class Area.
 * This class has a specific type and only seals, sharks and starfishes can be added to this type of area.
 */
public class Aquarium extends Area {
    public Aquarium(int capacity){
        super(capacity);
        type = "aquarium";
    }

    /**
     * Test whether given animal has been put into the wrong habitat.
     * @param animal The animal to be test.
     * @return false if the given animal is seal, shark or starfish and true for otherwise.
     */
    public boolean wrongHabitat(Animal animal){
        if( animal.species.equals("seal") ){
            return false;
        }if( animal.species.equals("shark") ){
            return false;
        }if( animal.species.equals("starfish") ){
            return false;
        }
        return true;
    }
}
