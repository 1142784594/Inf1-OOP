package animals;
/**
 *This class inherent from Animal Class.
 *This class has special specie(lion) and can check whether it is compatible with other animals.
 * This class creates the Lion with its nickname.
 */
public class Lion extends Animal {
    /**
     * The constructor of Class Lion can give Lion a nickname and get its unique specie "lion".
     * @param nickname nickname name of each lion.
     */
    public Lion(String nickname){
        //call constructor of parent class(Animal) and accept nickname parameter
        super(nickname);
        species = "lion";
    }

    /**
     * Check whether two animals can live together.
     * @param animal The animal for which to check compatibility with this animal.
     * @return Returns true for lions and false otherwise, since it cannot live with other animal.
     */
    public boolean isCompatibleWith(Animal animal){
        if(animal.species.equals("lion") ){
            return true;
        }
        return false;
    }
}
