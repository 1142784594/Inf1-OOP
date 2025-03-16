package animals;
/**
 *This class inherent from Animal Class.
 *This class has special specie(Seal) and can check whether it is compatible with other animals.
 * This class creates the Seal with its nickname.
 */
public class Seal extends Animal{
    /**
     * The constructor of Class Seal can give seal a nickname and get its unique specie "seal".
     * @param nickname nickname name of each seal.
     */
    public Seal(String nickname){
        //call constructor of parent class(Animal) and accept nickname parameter
        super(nickname);
        species = "seal";
    }
    /**
     * Check whether two animals can live together.
     * @param animal The animal for which to check compatibility with this animal.
     * @return Returns true for seal and starfish, and false otherwise,
     * since it cannot live with other animal except itself and starfish.
     */
    public boolean isCompatibleWith(Animal animal){
        if (animal.species == "seal" || animal.species == "starfish"){
            return true;
        }
        return false;
    }
}
