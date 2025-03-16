package zoo;

import animals.Animal;
import areas.Area;
import areas.Entrance;
import areas.IArea;
import dataStructures.CashCount;
import dataStructures.ICashCount;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * This class can realize almost all the function of a zoo
 */
public class Zoo implements IZoo{

    //This array list stores all the areas in the zoo
    public ArrayList<Area> areas;
    //This array list stores ID of all the areas in the zoo
    public ArrayList<Integer> areasIDRecord;
    //This array list stores all the animals that visitors have seen in the zoo.
    public ArrayList<String> sawAnimals;
    //This array list stores all the areas that visitors can reach in the zoo from the entrance.
    public Set<Integer> reachableArea;
    //This array list stores all the areas that visitors cannot reach in the zoo from the entrance.
    public Set<Integer> unreachableArea;
    //The pounds part of the ticket price
    public int ticketPricePounds;
    //The pence part of the ticket price
    public int tickPricePence;
    //The cash stored in the machine
    public CashCount cashSupply;
    //A CashCount class without money
    public CashCount noneMoney;
    //the reminder of the money with scale of one hundred
    public int reminderScaleOfOneHundred;
    //the money return to the customer
    public CashCount cashBack;

    /**
     * The constructor of the zoo class.
     * Since every zoo need an entrance, the entrance area will be created and added to zoo
     * when the zoo has been created. Since the ID of the entrance is always zero, zero will
     * be added to the areasIDRecord array too.
     * This constructor also initialise all the field with package class.
     */
    public Zoo(){
        areas = new ArrayList<>();
        Entrance entrance = new Entrance();
        areas.add(entrance);
        areasIDRecord = new ArrayList<>();
        areasIDRecord.add(0);
        sawAnimals = new ArrayList<>();
        unreachableArea = new HashSet<>();
        noneMoney = new CashCount();
        cashBack = new CashCount();
    }

    /**
     * Adds the given area to the zoo, unless it is already part of the zoo
     * or is a second entrance (which is not allowed).
     * @param area The area to be added.
     * @return An ID by which the added area can be uniquely identified or -1
     * if the area cannot be added.
     */
    public int addArea(IArea area) {
        //Since parameter area is IArea, we need to turn it into Area to get more methods.
        Area add_area = (Area) area;
        int ID = add_area.getUniqueID();
        //eliminate the area that has already been in the zoo and entrance area
        for(Area i : areas ){
            if(add_area.getType().equals("entrance") ){
                return -1;
            }if(i.getUniqueID() == ID){
                return -1;
            }
        }
        areas.add(add_area);
        addAreasIDRecord(add_area.getUniqueID());
        return ID;
    }

    /**
     * Removes the specified area from the zoo.
     * @param areaId The ID of the area to be removed.
     * @return Returns true if (and only if) an area was successfully removed.
     */
    public boolean removeArea(int areaId) {
        //entrance cannot be removed
        if(areaId == 0){
            return false;
        }
        for (Area i : areas){
            //check whether given area is in the zoo
            if(areaId == i.getUniqueID()){
                areas.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the area associated with the given ID.
     * @param areaId The ID of the area to be fetched.
     * @return The area corresponding to the given ID.
     */
    public IArea getArea(int areaId) {
        for (Area i : areas){
            //check whether given area is in the zoo
            if(areaId == i.getUniqueID()){
                return i;
            }
        }
        return null;
    }

    /**
     * Tries to add the given animal to the specified area
     * @param areaId The ID of the area the animal is to be added to.
     * @param animal The animal to be added.
     * @return Returns a code indicating success or failure. See {@link Codes}.
     */
    public byte addAnimal(int areaId, Animal animal) {
        for(Area i : areas){
            //check whether the given area is in the zoo
            if(i.getUniqueID() == areaId ){
                //check whether animal is placed into non-habitat areas
                if( i.getType() == "entrance" || i.getType() == "picnic"){
                    return 1;
                }
                //check whether animal is placed into appropriate habitats
                if (i.wrongHabitat(animal)){
                   return 2;
                }else{
                    //check whether the area reach the maximum capacity
                    if(i.reach_max_capacity()){
                        return 3;
                    //check whether the given animal can live with other animals that have already been in the area
                    }if(i.isIncompatible(animal)){
                        return 4;
                    //if the animal and area have not the problems above, the animal will be added to the area.
                    }else{
                        i.addAnimal(animal);
                        return 0;
                    }
                }
            }
        }
        //if the given area is not in the zoo
        return -1;
    }

    /**
     * Allows visitors to move between areas in the given direction (from -> to).
     * @param fromAreaId The ID of the area from which the destination is to be accessible.
     * @param toAreaId The ID of the destination area.
     */
    public void connectAreas(int fromAreaId, int toAreaId) {
        for(Area i : areas){
            //From area and to area should all be in the zoo, and they should not be the same.
            if(i.getUniqueID() == fromAreaId && fromAreaId != toAreaId && containsAresIDRecord(toAreaId)){
                i.addAdjacentArea(toAreaId);
            }
        }
    }

    /**
     *
     * @return Returns the ID of all the areas in the zoo
     */
    public ArrayList<Integer> getAreasIDRecord(){
        return areasIDRecord;
    }

    /**
     * Add the given ID to the record of areas' ID
     * @param IDRecord The unique ID of the area.
     */
    public void addAreasIDRecord(int IDRecord){
        areasIDRecord.add(IDRecord);
    }

    /**
     * Check whether the given ID is in the ID record of all the areas in the zoo
     * @param IDRecord The unique ID of the area.
     * @return Returns true if the given ID is in the ID record of all the areas in the zoo and false for otherwise.
     */
    public boolean containsAresIDRecord(int IDRecord){
        return areasIDRecord.contains(IDRecord);
    }

    /**
     * Checks if the given path obeys the one-way system.
     * @param areaIds The path is provided as a list of area IDs. It starts with the area ID at index 0.
     * @return Returns true iff visitors are allowed to visit the areas in the order given by the passed in list.
     */
    public boolean isPathAllowed(ArrayList<Integer> areaIds) {
        for(Integer i : areaIds){
            //turn the area ID into area
            Area nowArea = (Area) getArea(i);
            //check whether i is the last item of areaIds, since the last one is the end,
            // and it does not need to be test.
            if(i != areaIds.get(areaIds.size()-1)){
                //check whether the next area in the array list is the adjacent area
                // of the area that we are looking at now. If the next area is not the adjacent area
                // of the last area, then the path is not allowed.
                if(!(nowArea.adjacentAreas.contains(areaIds.get(areaIds.indexOf(i)+1)))){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Visits the areas in the specified order and records the names of all animals seen.
     * @param areaIdsVisited Areas IDs in the order they were visited.
     * @return Returns a list of the names of all animals seen during the visit in the order they were seen.
     */
    public ArrayList<String> visit(ArrayList<Integer> areaIdsVisited) {
        //initialise the sawAnimals array every time the visitors record the names of all animals seen.
        sawAnimals.clear();
        //check whether the visitors' path obeys the one-way system.
        if(!(isPathAllowed(areaIdsVisited))){
            return null;
        }
        for ( Integer i : areaIdsVisited){
          Area nowArea = (Area) getArea(i);
          for(Animal j : nowArea.getAnimals()){
              //Record the animals that visitors have seen in the zoo
              sawAnimals.add(j.getNickname());
          }
        }
        return sawAnimals;
    }

    /**
     * Finds all areas that cannot be reached from the entrance of the zoo.
     * @return The IDs of all inaccessible areas (unordered).
     */
    public Set<Integer> findUnreachableAreas() {
        reachableArea = new HashSet<>();
        reachableArea.add(0);
        //find all reachable area from the entrance
        //The times for looping depend on the numbers of areas,
        // since the longest way to reach an area is one by one from entrance to the farthest area.
        for(int j = 0; j < areas.size(); j++){
            for(int i : reachableArea){
                //add all the adjacent area of the reachable area into reachable area
                reachableArea.addAll(getArea(i).getAdjacentAreas());
            }
        }
        unreachableArea.addAll(areasIDRecord);
        //the unreachable areas are the opposite to the reachable area
        unreachableArea.removeAll(reachableArea);
        return unreachableArea;
    }



    /**
     * Sets a new ticket price in pounds and pence.
     * @param pounds The first part of the cost before the point e.g. 17 for a ticket that costs £17.50
     * @param pence The second part of the cost after the point e.g. 50 for a ticket that costs £17.50
     */
    public void setEntranceFee(int pounds, int pence) {
        //check whether pounds part is no less than zero
        if(pounds >= 0){
            ticketPricePounds = pounds;
        }
        //check whether pence part is no less than zero
        if(pence >= 0){
            tickPricePence = pence;
        }
    }

    /**
     * Stocks the ticket machine with the provided pool of cash.
     * @param coins The number of notes and coins of different denominations available.
     */
    public void setCashSupply(ICashCount coins) {
        cashSupply = (CashCount) coins;
    }

    /**
     * Used to inspect the ticket machine's currently available pool of cash.
     * @return The amount of each note and coin currently in the machine.
     */
    public ICashCount getCashSupply() {
        return cashSupply;
    }

    /**
     * Takes an amount of cash inserted into the ticket machine and returns the appropriate change
     * (if any) after deducting the amount of the entrance fee as set by @setEntranceFee.
     * @param cashInserted The notes and coins inserted by the user buying a ticket.
     * @return The change returned to the user (see assignment instructions for precise specification).
     */
    public ICashCount payEntranceFee(ICashCount cashInserted) {
        CashCount cashInsertedNow= (CashCount) cashInserted;
        //renew the reminder every time the visitor pay the ticket
        reminderScaleOfOneHundred = 0;
        //If the inserted cash amounts to less than the price of a ticket, the
        //machine returns the cash that was inserted.
        if(cashInsertedNow.getMoneyScaleOfOneHundred() < (ticketPricePounds * 100 + tickPricePence)){return cashInserted;}
        //If the inserted cash pays for the ticket exactly, no change is given.
        if(cashInsertedNow.getMoneyScaleOfOneHundred() == (ticketPricePounds * 100 + tickPricePence)){
            //add inserted money into the machine
            cashSupply.addMoneyIn(cashInsertedNow);
            return noneMoney;
        }
        //add inserted money into the machine
        cashSupply.addMoneyIn(cashInsertedNow);
        //use the method to find whether the machine has enough money to return the change
        setCashBack(cashInsertedNow.getMoneyScaleOfOneHundred() - (ticketPricePounds * 100 + tickPricePence));
        //check the reminder after changing
        if(reminderScaleOfOneHundred == 0){
            return cashBack;
        }else{
            //If the machine cannot give exact change,
            // it will give the money back to visitor
            // and the cash supply will put the cash that they try to change for visitor back to the machine.
            cashSupply.addMoneyIn(cashBack);
            cashSupply.removeMoneyOut(cashInsertedNow);
            return cashInserted;
        }
    }

    /**
     * This method record how many 20 pounds notes can be returned to visitor
     * and record the left money still need to be returned to visitor.
     * This method also remove the cash back to visitor from the cash supply.
     * @param backMoney The money need to turn back to visitor.
     */
    public void back_20pounds(int backMoney){
        reminderScaleOfOneHundred = backMoney;
        //check whether the money in the machine can have enough 20 pounds notes return to visitor
        if( (backMoney/2000) <= cashSupply.getNrNotes_20pounds()){
            //set the number of 20 pounds notes need to be return to visitor
            cashBack.setNrNotes_20pounds((backMoney/2000));
            //remove the money returned to visitor from the cash supply
            cashSupply.setNrNotes_20pounds(cashSupply.NrNotes_20pounds - (backMoney/2000));
            //record the money still need to be returned to visitor
            reminderScaleOfOneHundred = reminderScaleOfOneHundred % 2000;
        //if the cash supply does not have enough 20 pounds giving to visitor,
        // it will give all the 20 pounds that stored in the cash supply.
        }else{
            //give visitor all the 20 pounds that stored in the cash supply
            cashBack.setNrNotes_20pounds(cashSupply.NrNotes_20pounds);
            //record the money still need to be returned to visitor
            reminderScaleOfOneHundred = reminderScaleOfOneHundred - cashSupply.NrNotes_20pounds * 2000;
            //clear all the 20 pounds notes in the cash supply
            cashSupply.setNrNotes_20pounds(0);
        }
    }

    /**
     * This method record how many 10 pounds notes can be returned to visitor
     * and record the left money still need to be returned to visitor.
     * This method also remove the cash back to visitor from the cash supply.
     */
    public void back_10pounds(){
        //check whether the money in the machine can have enough 10 pounds notes return to visitor
        if( (reminderScaleOfOneHundred/1000) <= cashSupply.getNrNotes_10pounds()){
            //set the number of 10 pounds notes need to be return to visitor
            cashBack.setNrNotes_10pounds((reminderScaleOfOneHundred/1000));
            //remove the money returned to visitor from the cash supply
            cashSupply.setNrNotes_10pounds(cashSupply.NrNotes_10pounds - (reminderScaleOfOneHundred/1000));
            //record the money still need to be returned to visitor
            reminderScaleOfOneHundred = reminderScaleOfOneHundred % 1000;
        //if the cash supply does not have enough 10 pounds giving to visitor,
        // it will give all the 10 pounds that stored in the cash supply.
        }else{
            //give visitor all the 10 pounds that stored in the cash supply
            cashBack.setNrNotes_10pounds(cashSupply.NrNotes_10pounds);
            //record the money still need to be returned to visitor
            reminderScaleOfOneHundred = reminderScaleOfOneHundred - cashSupply.NrNotes_10pounds * 1000;
            //clear all the 10 pounds notes in the cash supply
            cashSupply.setNrNotes_10pounds(0);
        }
    }

    /**
     * This method record how many 5 pounds notes can be returned to visitor
     * and record the left money still need to be returned to visitor.
     * This method also remove the cash back to visitor from the cash supply.
     */
    public void back_5pounds(){
        //check whether the money in the machine can have enough 5 pounds notes return to visitor
        if( (reminderScaleOfOneHundred/500) <= cashSupply.getNrNotes_5pounds()){
            //set the number of 5 pounds notes need to be return to visitor
            cashBack.setNrNotes_5pounds((reminderScaleOfOneHundred/500));
            //remove the money returned to visitor from the cash supply
            cashSupply.setNrNotes_5pounds(cashSupply.NrNotes_5pounds - (reminderScaleOfOneHundred/500));
            //record the money still need to be returned to visitor
            reminderScaleOfOneHundred = reminderScaleOfOneHundred % 500 ;
        //if the cash supply does not have enough 5 pounds giving to visitor,
        // it will give all the 5 pounds that stored in the cash supply.
        }else{
            //give visitor all the 5 pounds that stored in the cash supply
            cashBack.setNrNotes_5pounds(cashSupply.NrNotes_5pounds);
            //record the money still need to be returned to visitor
            reminderScaleOfOneHundred = reminderScaleOfOneHundred - cashSupply.NrNotes_5pounds * 500;
            //clear all the 5 pounds notes in the cash supply
            cashSupply.setNrNotes_5pounds(0);
        }
    }
    /**
     * This method record how many 2 pounds coins can be returned to visitor
     * and record the left money still need to be returned to visitor.
     * This method also remove the cash back to visitor from the cash supply.
     */
    public void back_2pounds(){
        //check whether the money in the machine can have enough 2 pounds coins return to visitor
        if( (reminderScaleOfOneHundred/200) <= cashSupply.getNrCoins_2pounds()){
            //set the number of 2 pounds coins need to be return to visitor
            cashBack.setNrCoins_2pounds((reminderScaleOfOneHundred/200));
            //remove the money returned to visitor from the cash supply
            cashSupply.setNrCoins_2pounds(cashSupply.NrCoins_2pounds- (reminderScaleOfOneHundred/200));
            //record the money still need to be returned to visitor
            reminderScaleOfOneHundred = reminderScaleOfOneHundred % 200 ;
        //if the cash supply does not have enough 2 pounds giving to visitor,
        // it will give all the 2 pounds coins that stored in the cash supply.
        }else{
            //give visitor all the 2 pounds coins that stored in the cash supply
            cashBack.setNrCoins_2pounds(cashSupply.NrCoins_2pounds);
            //record the money still need to be returned to visitor
            reminderScaleOfOneHundred = reminderScaleOfOneHundred - cashSupply.NrCoins_2pounds * 200;
            //clear all the 2 pounds coins in the cash supply
            cashSupply.setNrCoins_2pounds(0);
        }
    }

    /**
     * This method record how many 1 pound coins can be returned to visitor
     * and record the left money still need to be returned to visitor.
     * This method also remove the cash back to visitor from the cash supply.
     */
    public void back_1pounds(){
        //check whether the money in the machine can have enough 1 pound coins return to visitor
        if( (reminderScaleOfOneHundred/100) <= cashSupply.getNrCoins_1pound()){
            //set the number of 1 pound coins need to be return to visitor
            cashBack.setNrCoins_1pound((reminderScaleOfOneHundred/100));
            //remove the money returned to visitor from the cash supply
            cashSupply.setNrCoins_1pound(cashSupply.NrCoins_1pounds- (reminderScaleOfOneHundred/100));
            //record the money still need to be returned to visitor
            reminderScaleOfOneHundred = reminderScaleOfOneHundred % 100 ;
        //if the cash supply does not have enough 1 pound coins giving to visitor,
        // it will give all the 1 pound coins that stored in the cash supply.
        }else{
            //give visitor all the 1 pound coins that stored in the cash supply
            cashBack.setNrCoins_1pound(cashSupply.NrCoins_1pounds);
            //record the money still need to be returned to visitor
            reminderScaleOfOneHundred = reminderScaleOfOneHundred - cashSupply.NrCoins_1pounds * 100;
            //clear all the 1 pound coins in the cash supply
            cashSupply.setNrCoins_1pound(0);
        }
    }
    /**
     * This method record how many 50 pence coins can be returned to visitor
     * and record the left money still need to be returned to visitor.
     * This method also remove the cash back to visitor from the cash supply.
     */
    public void back_50p(){
        //check whether the money in the machine can have enough 50 pence coins return to visitor
        if( (reminderScaleOfOneHundred/50) <= cashSupply.getNrCoins_50p()){
            //set the number of 50 pence coins need to be return to visitor
            cashBack.setNrCoins_50p((reminderScaleOfOneHundred/50));
            //remove the money returned to visitor from the cash supply
            cashSupply.setNrCoins_50p(cashSupply.NrCoins_50p- (reminderScaleOfOneHundred/50));
            //record the money still need to be returned to visitor
            reminderScaleOfOneHundred = reminderScaleOfOneHundred % 50 ;
        //if the cash supply does not have enough 50 pence coins giving to visitor,
        // it will give all the 50 pence coins that stored in the cash supply.
        }else{
            //give visitor all the 50 pence coins that stored in the cash supply
            cashBack.setNrCoins_50p(cashSupply.NrCoins_50p);
            //record the money still need to be returned to visitor
            reminderScaleOfOneHundred = reminderScaleOfOneHundred - cashSupply.NrCoins_50p * 50;
            //clear all the 50 pence coins in the cash supply
            cashSupply.setNrCoins_50p(0);
        }
    }
    /**
     * This method record how many 20 pence coins can be returned to visitor
     * and record the left money still need to be returned to visitor.
     * This method also remove the cash back to visitor from the cash supply.
     */
    public void back_20p(){
        //check whether the money in the machine can have enough 20 pence coins return to visitor
        if( (reminderScaleOfOneHundred/20) <= cashSupply.getNrCoins_20p()){
            //set the number of 20 pence coins need to be return to visitor
            cashBack.setNrCoins_20p((reminderScaleOfOneHundred/20));
            //remove the money returned to visitor from the cash supply
            cashSupply.setNrCoins_20p(cashSupply.NrCoins_20p- (reminderScaleOfOneHundred/20));
            //record the money still need to be returned to visitor
            reminderScaleOfOneHundred = reminderScaleOfOneHundred % 20 ;
        //if the cash supply does not have enough 20 pence coins giving to visitor,
        // it will give all the 20 pence coins that stored in the cash supply.
        }else{
            //give visitor all the 20 pence coins that stored in the cash supply
            cashBack.setNrCoins_20p(cashSupply.NrCoins_20p);
            //record the money still need to be returned to visitor
            reminderScaleOfOneHundred = reminderScaleOfOneHundred - cashSupply.NrCoins_20p * 20;
            //clear all the 20 pence coins in the cash supply
            cashSupply.setNrCoins_20p(0);
        }
    }
    /**
     * This method record how many 10 pence coins can be returned to visitor
     * and record the left money still need to be returned to visitor.
     * This method also remove the cash back to visitor from the cash supply.
     */
    public void back_10p(){
        //check whether the money in the machine can have enough 10 pence coins return to visitor
        if( (reminderScaleOfOneHundred/10) <= cashSupply.getNrCoins_10p()){
            //set the number of 10 pence coins need to be return to visitor
            cashBack.setNrCoins_10p((reminderScaleOfOneHundred/10));
            //remove the money returned to visitor from the cash supply
            cashSupply.setNrCoins_10p(cashSupply.NrCoins_10p- (reminderScaleOfOneHundred/10));
            //record the money still need to be returned to visitor
            reminderScaleOfOneHundred = reminderScaleOfOneHundred % 10 ;
        //if the cash supply does not have enough 10 pence coins giving to visitor,
        // it will give all the 10 pence coins that stored in the cash supply.
        }else{
            //give visitor all the 10 pence coins that stored in the cash supply
            cashBack.setNrCoins_10p(cashSupply.NrCoins_10p);
            //record the money still need to be returned to visitor
            reminderScaleOfOneHundred = reminderScaleOfOneHundred - cashSupply.NrCoins_10p * 10;
            //clear all the 10 pence coins in the cash supply
            cashSupply.setNrCoins_10p(0);
        }
    }

    /**
     * This method will record the number of cash needed to be returned to the visitor
     * from the largest denomination  to the lowest one in order not to annoy the visitor.
     * @param backMoney The money need to returned to visitor
     */
    public void setCashBack(int backMoney){
        back_20pounds(backMoney);
        back_10pounds();
        back_5pounds();
        back_2pounds();
        back_1pounds();
        back_50p();
        back_20p();
        back_10p();
    }

}
