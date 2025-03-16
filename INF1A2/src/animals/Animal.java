package animals;

/**
 * This class can create a new animal with nickname and check
 * whether it can live with other animals.
 */
public abstract class Animal
{
	//nickname for each animal given by the editor.
	public String nickname;
	// A String of the specie of each animal.
	public String species;
	/**
	 *The constructor of Class Animal to change field nickname.
	 * @param nickname The nickname of animal to give animal its nickname.
	 */
	public Animal(String nickname){
		//Change the field name nickname to parameter nickname.
		this.nickname = nickname;
	}

	/**
	 * @return Returns this animal's given name.
	 */
	public String getNickname(){
		return nickname;
	}
	
	/**
	 * Check whether two animals can live together.
	 * @param animal The animal for which to check compatibility with this animal.
	 * @return Returns true for compatible animals and false otherwise.
	 */
	public abstract boolean isCompatibleWith(Animal animal);
}
