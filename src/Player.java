import Exceptions.NegativeAmountException;
import Exceptions.NotEnoughCreditsException;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String username;
    private int credits;
    private List<String> inventory;



    //Konstruktør
    public Player(String username, int credits) {
        this.username = username;
        try {
            this.credits = credits;
        } catch (NegativeAmountException e) {
            throw new NegativeAmountException("You can't start with a negative amount of credits.");
        }
        this.inventory = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }
    public int  getCredits() {
        return credits;
    }
    public List<String> getInventory() {
        return inventory;
    }

    public int addCredits(int credits) {
        negativeAmountException(credits);
        return this.credits += credits;
    }

    public void addItem(String item) {
        inventory.add(item);
    }

    public List<String> showInventory() {
        return new ArrayList<>(inventory);
    }


    public void openLootCrate(LootCrate crate) throws NotEnoughCreditsException {
            checkCredits(credits, crate);
            credits = credits - crate.getCratePrice(); //Trækker ens credits fra når en crate er åbnet
    }

    //checked exception til at se om der er nok credits
    public static void checkCredits(int credits, LootCrate crate) throws NotEnoughCreditsException {
        if (credits < crate.getCratePrice()){
            throw new NotEnoughCreditsException("Must have enough credits to open crate!");
        }
    }

    //unchecked exception til at se om tallet er negativt
    public void negativeAmountException(int credits){
        if (credits < 0){
            throw new NegativeAmountException("Cannot add negative credits!");
        }
    }

    @Override
    public String toString() {
        return "Player: " + username + '\'' +
                " | Credits: " + credits;
    }




}
