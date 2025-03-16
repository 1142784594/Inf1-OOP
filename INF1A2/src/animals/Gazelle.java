package animals;

/**
 *This class inherent from Animal Class.
 *This class has special specie and can check whether it is compatible with other animals.
 * This class creates the gazelle with its nickname.
 */
public class Gazelle extends Animal{
    /**
     * The constructor of Class Buzzard can give gazelle a nickname and get its unique specie "gazelle".
     * @param nickname nickname name of each gazelle.
     */
    public Gazelle(String nickname){
        //call constructor of parent class(Animal) and accept nickname parameter
        super(nickname);
        species = "gazelle";
    }

    /**
     * Check whether two animals can live together.
     * @param animal The animal for which to check compatibility with this animal.
     * @return Returns true for gazelle and zebra, and false otherwise,
     * since it cannot live with other animal excluding gazelles and zebra.
     */
    public boolean isCompatibleWith(Animal animal){
        if (animal.species.equals( "gazelle")  || animal.species.equals("zebra") ){
            return true;
        }
        return false;
    }
}
