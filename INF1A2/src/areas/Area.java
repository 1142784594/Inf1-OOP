package areas;

import animals.Animal;

import java.util.ArrayList;

/**
 * This class can create an area and change its adjacent areas.
 * */
public abstract class Area implements IArea {
    //Give each class a unique type, so they can distinguish each subclass with different type.
    public String type;
    //each area has a unique ID
    public int uniqueID;
    //The maximum capacity of each area, which shows how many animals that this area can contain.
    public int max_capacity;
    /*This field is static, so it will keep the same with last created Area class,
     and it can count how many classes have been created.
     */
    public static int count;
    //An array list stores the animals added into the area.
    public ArrayList<Animal> animals;
    //An array List stores the ID of areas that are adjacent to this new created area.
    public ArrayList<Integer> adjacentAreas;

    /**
     * The constructor of Area Class for picnic and entrance, since they cannot contain animals.
     * The constructor will give each class a unique ID, which will be the number of how many classes have been created.
     * The constructor initialise two arrays animals and adjacentAreas.
     */
    public Area(){
        //count will be added one when each class is created.
        count = count +1;
        uniqueID = count;
        animals = new ArrayList<>();
        adjacentAreas = new ArrayList<>();
    }

    /**
     * The constructor for other areas excluding picnic and entrance.
     * The constructor will give each class a unique ID, which will be the number of how many classes have been created.
     * The constructor will set the max_capacity to the given parameter capacity.
     * The constructor initialise two arrays animals and adjacentAreas.
     * @param capacity The given parameter of the maximum capacity of the area.
     */
    public Area(int capacity){
        count = count +1;
        uniqueID = count;
        max_capacity = capacity;
        animals = new ArrayList<>();
        adjacentAreas = new ArrayList<>();
    }

    /**
     * @return Returns the IDs of the areas adjacent to this one.
     */
    public ArrayList<Integer> getAdjacentAreas() {
        return adjacentAreas;
    }

    /**
     * This method can add given area ID to the array that stores the areas adjacent to this area,
     * if the area ID hasn't been added into this array before.
     * @param areaId The ID of the area to be added.
     */
    public void addAdjacentArea(int areaId) {
        if(!(adjacentAreas.contains(areaId))){
            adjacentAreas.add(areaId);
        }
    }

    /**
     * If the given area is in the array(adjacentAreas), it will be removed from this array.
     * @param areaId The ID of the area to be removed.
     */
    public void removeAdjacentArea(int areaId) {
        if(adjacentAreas.contains(areaId)){
            adjacentAreas.remove(areaId);
        }

    }

    /**
     *
     * @return the type of each area.
     */
    public String getType(){
        return type;
    }

    /**
     *
     * @return the unique ID of each area.
     */
    public int getUniqueID(){
        return uniqueID;
    }

    /**
     *
     * @return The maximum capacity of the area.
     */
    public int getMax_capacity(){
        return max_capacity;
    }

    /**
     * This method test whether given animal is put in the wrong habitat.
     * @param animal The animal to be test.
     * @return return true if the animal is put in the wrong habitat.
     */
    public abstract boolean wrongHabitat(Animal animal);

    /**
     * This method can add given animal to array animals, which stores the animals contained in the area.
     * The same animal cannot be added twice.
     * @param animal The given animal need to add into the area.
     */
    public void addAnimal(Animal animal){
        //Test whether animal is already in the area.
        if(!(animals.contains(animal))){
            animals.add(animal);
        }
    }

    /**
     *
     * @return All the animals are in the area.
     */
    public ArrayList<Animal> getAnimals(){
        return animals;
    }

    /**
     *
     * @return The numbers of animals are in the area.
     */
    public int getAnimalSize(){
        return animals.size();
    }

    /**
     *Check whether the capacity of the area reach maximum.
     * @return true if animals in the area reach the maximum capacity of the area and false for otherwise.
     */
    public boolean reach_max_capacity(){
        if (getAnimalSize() == getMax_capacity()) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * Check whether given animal cannot live with other animals that have already stayed in the area.
     * @param animal The given animal to compare with other animals.
     * @return true if the given animal cannot live with other animal that have already stayed in the area,
     * and false for otherwise.
     */
    public boolean isIncompatible(Animal animal){
        //compare with all the animals in array animals
        for(Animal i : animals){
            if(!(i.isCompatibleWith(animal))){
                return true;
            }
        }
        return false;
    }
}
