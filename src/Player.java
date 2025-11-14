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
        this.credits = credits;
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

    public void addCredits(int credits) {
        negativeAmountException(credits);
        this.credits += credits;
        System.out.println("Credits added! " + getUsername() + "'s current credits: " + getCredits());
    }

    public void addItem(String item) {
        inventory.add(item);
    }

    public void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println(username + " has no items yet.");
        } else {
            System.out.println(username + "'s inventory:");
            for (String item : inventory) {
                System.out.println("- " + item);
            }
        }
    }


    public void openLootCrate(LootCrate crate) throws NotEnoughCreditsException {
            checkCredits(credits, crate);
            System.out.println("Crate opened!");
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
