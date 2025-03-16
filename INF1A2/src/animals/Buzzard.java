package animals;

/**
 *This class inherent from Animal Class.
 *This class has special specie(buzzard) and can check whether it is compatible with other animals.
 * This class creates the buzzard with its nickname.
 */
public class Buzzard extends Animal{
    /**
     * The constructor of Class Buzzard can give buzzard a nickname and get its unique specie "buzzard".
     * @param nickname nickname name of each buzzard.
     */
    public Buzzard(String nickname){
        //call constructor of parent class(Animal) and accept nickname parameter
        super(nickname);
        species = "buzzard";
    }

    /**
     * Check whether two animals can live together.
     * @param animal The animal for which to check compatibility with this animal.
     * @return Returns true for buzzards and false otherwise, since it cannot live with other animal.
     */
    public boolean isCompatibleWith(Animal animal){
        if (animal.species.equals("buzzard") ){
            return true;
        }
        return false;
    }

}
