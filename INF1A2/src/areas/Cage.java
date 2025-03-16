package areas;

import animals.Animal;

import java.util.ArrayList;
/**
 * This class inherent from Class Area.
 * This class has a specific type and only buzzards and parrot can be added to this type of area.
 */
public class Cage extends Area{
    public Cage(int capacity){
        super(capacity);
        type = "cage";
    }

    /**
     * Test whether given animal has been put into the wrong habitat.
     * @param animal The animal to be test.
     * @return false if the given animal is buzzard or parrot, and true for otherwise.
     */
    public boolean wrongHabitat(Animal animal){
        if( animal.species.equals("buzzard") ){
            return false;
        }if( animal.species.equals("parrot") ){
            return false;
        }
        return true;
    }
}
