package animals;
/**
 *This class inherent from Animal Class.
 *This class has special specie(starfish) and can check whether it is compatible with other animals.
 * This class creates the buzzard with its nickname.
 */
public class Zebra extends Animal{
    /**
     * The constructor of Class Zebra can give zebra a nickname and get its unique specie "zebra".
     * @param nickname nickname name of each zebra.
     */
    public Zebra(String nickname){
        //call constructor of parent class(Animal) and accept nickname parameter
        super(nickname);
        species = "zebra";
    }
    /**
     * Check whether two animals can live together.
     * @param animal The animal for which to check compatibility with this animal.
     * @return Returns true for gazelles and zebras, and false otherwise,
     * since it cannot live with other animal excluding zebras and gazelles.
     */
    public boolean isCompatibleWith(Animal animal){
        if (animal.species == "gazelle" || animal.species == "zebra"){
            return true;
        }
        return false;
    }
}
