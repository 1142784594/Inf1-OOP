package areas;

import animals.Animal;
/**
 * This class inherent from Class Area.
 * This class has type "picnic" .
 */
public class PicnicArea extends Area{
    public PicnicArea(){
        super();
        type = "picnic";
    }
    /**
     * Test whether given animal has been put into the wrong habitat.
     * @param animal The animal to be test.
     * @return false for all, since picnic area cannot contain any animal.
     */
    public boolean wrongHabitat(Animal animal){
        return true;
    }
}
