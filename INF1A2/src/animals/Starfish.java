package animals;
/**
 *This class inherent from Animal Class.
 *This class has special specie(starfish) and can check whether it is compatible with other animals.
 * This class creates the buzzard with its nickname.
 */
public class Starfish extends Animal{
    /**
     * The constructor of Class Starfish can give starfish a nickname and get its unique specie "starfish".
     * @param nickname nickname name of each starfish.
     */
    public Starfish(String nickname){
        //call constructor of parent class(Animal) and accept nickname parameter
        super(nickname);
        species = "starfish";
    }

    /**
     * Check whether two animals can live together.
     * @param animal The animal for which to check compatibility with this animal.
     * @return Returns true for Starfishes, sharks and seals, and false otherwise,
     * since it cannot live with other animal excluding sharks, starfishes and seals.
     */
    public boolean isCompatibleWith(Animal animal){
        if (animal.species == "shark" || animal.species == "starfish" || animal.species == "seal"){
            return true;
        }
        return false;
    }
}
