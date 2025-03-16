package animals;
/**
 *This class inherent from Animal Class.
 *This class has special specie(Shark) and can check whether it is compatible with other animals.
 * This class creates the Shark with its nickname.
 */
public class Shark extends Animal{
    public Shark(String nickname){
        //call constructor of parent class(Animal) and accept nickname parameter
        super(nickname);
        species = "shark";
    }
    /**
     * Check whether two animals can live together.
     * @param animal The animal for which to check compatibility with this animal.
     * @return Returns true for sharks and starfishes, and false otherwise,
     * since it cannot live with other animal except sharks and starfishes .
     */
    public boolean isCompatibleWith(Animal animal){
        if (animal.species == "shark" || animal.species == "starfish"){
            return true;
        }
        return false;
    }
}
