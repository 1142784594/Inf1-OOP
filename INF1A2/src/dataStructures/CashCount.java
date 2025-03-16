package dataStructures;

public class CashCount implements ICashCount{
    //The pounds part of the money
    public int pounds_part;
    //The pence part of the money
    public int pence_part;
    //The number of 20 pounds notes
    public int NrNotes_20pounds;
    //The number of 10 pounds notes
    public int NrNotes_10pounds;
    //The number of 5 pounds notes
    public int NrNotes_5pounds;
    //The number of 2 pounds notes
    public int NrCoins_2pounds;
    //The number of 1 pound notes
    public int NrCoins_1pounds;
    //The number of 50 pence coins
    public int NrCoins_50p;
    //The number of 20  pence coins
    public int NrCoins_20p;
    //The number of 10 pence coins
    public int NrCoins_10p;
    //The 100 times money
    public int moneyScaleOfOneHundred;


    // ---------------- SETTERS ----------------

    /**
     * @param noteCount Sets the number of 20 pound notes available in this pool of cash.
     */
    public void setNrNotes_20pounds(int noteCount){
        NrNotes_20pounds = noteCount;
    }

    /**
     * @param noteCount Sets the number of 10 pound notes available in this pool of cash.
     */
    public void setNrNotes_10pounds(int noteCount){
        NrNotes_10pounds = noteCount;
    }

    /**
     * @param noteCount Sets the number of 5 pound notes available in this pool of cash.
     */
    public void setNrNotes_5pounds(int noteCount){
        NrNotes_5pounds = noteCount;
    }

    /**
     * @param coinCount Sets the number of 2 pound coins available in this pool of cash.
     */
    public void setNrCoins_2pounds(int coinCount){
        NrCoins_2pounds = coinCount;
    }

    /**
     * @param coinCount Sets the number of 1 pound coins available in this pool of cash.
     */
    public void setNrCoins_1pound(int coinCount){
        NrCoins_1pounds = coinCount;
    }

    /**
     * @param coinCount Sets the number of 50 pence coins available in this pool of cash.
     */
    public void setNrCoins_50p(int coinCount){
        NrCoins_50p = coinCount;
    }


    /**
     * @param coinCount Sets the number of 20 pence coins available in this pool of cash.
     */
    public void setNrCoins_20p(int coinCount){
        NrCoins_20p = coinCount;
    }

    /**
     * @param coinCount Sets the number of 10 pence coins available in this pool of cash.
     */
    public void setNrCoins_10p(int coinCount){
        NrCoins_10p = coinCount;
    }

    // ---------------- GETTERS ----------------

    /**
     * @return Returns the number of 20 pound notes available in this pool of cash.
     */
    public int getNrNotes_20pounds(){
        return  NrNotes_20pounds;
    }

    /**
     * @return Returns the number of 10 pound notes available in this pool of cash.
     */
    public int getNrNotes_10pounds(){
        return  NrNotes_10pounds;
    }

    /**
     * @return Returns the number of 5 pound notes available in this pool of cash.
     */
    public int getNrNotes_5pounds(){
        return NrNotes_5pounds;
    }

    /**
     * @return Returns the number of 2 pound coins available in this pool of cash.
     */
    public int getNrCoins_2pounds(){
        return NrCoins_2pounds;
    }

    /**
     * @return Returns the number of 1 pound coins available in this pool of cash.
     */
    public int getNrCoins_1pound(){
        return NrCoins_1pounds;
    }

    /**
     * @return Returns the number of 50 pence coins available in this pool of cash.
     */
    public int getNrCoins_50p(){
        return NrCoins_50p;
    }

    /**
     * @return Returns the number of 20 pence coins available in this pool of cash.
     */
    public int getNrCoins_20p(){
        return NrCoins_20p;
    }

    /**
     * @return Returns the number of 10 pence coins available in this pool of cash.
     */
    public int getNrCoins_10p(){
        return NrCoins_10p;
    }

    /**
     *
     * @return Returns the pounds part of all the money
     */
    public int getPounds_part(){
        //get pounds part by multiplying the number of notes or coins with its value and adding all pounds part together
        pounds_part = getNrNotes_20pounds() * 20 + getNrNotes_10pounds() * 10 + getNrNotes_5pounds() * 5 +
                getNrCoins_2pounds() * 2 + getNrCoins_1pound() * 1;
        return pounds_part;
    }

    /**
     *
     * @return Returns the pence part of all the money
     */
    public int getPence_part(){
        //get pence part by multiplying the number of notes or coins with its value and adding all pence part together
        pence_part = getNrCoins_50p() * 50 + getNrCoins_20p() * 20 + getNrCoins_10p() * 10;
        return pence_part;
    }

    /**
     *
     * @return One hundred times of all the money
     */
    public int getMoneyScaleOfOneHundred(){
        moneyScaleOfOneHundred = getPounds_part() * 100 + getPence_part();
        return moneyScaleOfOneHundred;
    }

    /**
     * This method allows given cash to be added into the original one.
     * @param cashInserted The given cash need to be inserted.
     */
    public void addMoneyIn(CashCount cashInserted){
        NrNotes_20pounds = NrNotes_20pounds + cashInserted.getNrNotes_20pounds();
        NrNotes_10pounds = NrNotes_10pounds + cashInserted.getNrNotes_10pounds();
        NrNotes_5pounds = NrNotes_5pounds + cashInserted.getNrNotes_5pounds();
        NrCoins_2pounds = NrCoins_2pounds + cashInserted.getNrCoins_2pounds();
        NrCoins_1pounds = NrCoins_1pounds + cashInserted.getNrCoins_1pound();
        NrCoins_50p = NrCoins_50p + cashInserted.getNrCoins_50p();
        NrCoins_20p = NrCoins_20p + cashInserted.getNrCoins_20p();
        NrCoins_10p = NrCoins_10p + cashInserted.getNrCoins_10p();
    }

    /**
     * This method allows given cash to be removed from the original one.
     * @param cashInserted The given cash need to be removed.
     */
    public void removeMoneyOut(CashCount cashInserted){
        NrNotes_20pounds = NrNotes_20pounds - cashInserted.getNrNotes_20pounds();
        NrNotes_10pounds = NrNotes_10pounds - cashInserted.getNrNotes_10pounds();
        NrNotes_5pounds = NrNotes_5pounds - cashInserted.getNrNotes_5pounds();
        NrCoins_2pounds = NrCoins_2pounds - cashInserted.getNrCoins_2pounds();
        NrCoins_1pounds = NrCoins_1pounds - cashInserted.getNrCoins_1pound();
        NrCoins_50p = NrCoins_50p - cashInserted.getNrCoins_50p();
        NrCoins_20p = NrCoins_20p - cashInserted.getNrCoins_20p();
        NrCoins_10p = NrCoins_10p - cashInserted.getNrCoins_10p();
    }

}
