package areas;

import animals.Animal;
/**
 * This class inherent from Class Area.
 * This class has a specific type and only lions, gazelles and zebras can be added to this type of area.
 */
public class Enclosure extends Area{
    public Enclosure(int capacity){
        super(capacity);
        type = "enclosure";
    }
    /**
     * Test whether given animal has been put into the wrong habitat.
     * @param animal The animal to be test.
     * @return false if the given animal is lion, gazelle or zebra, and true for otherwise.
     */
    public boolean wrongHabitat(Animal animal){
        if( animal.species.equals("lion")){
            return false;
        }if( animal.species.equals("gazelle")){
            return false;
        }if( animal.species.equals("zebra")){
            return false;
        }
        return true;
    }
}
