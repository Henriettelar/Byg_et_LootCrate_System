import Exceptions.NotEnoughCreditsException;
import Exceptions.PlayerNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Map<String, Player> players = new HashMap();
    private static Scanner input = new Scanner(System.in);
    private static boolean keepRunning = true;

    public static void main(String[] args) {
        new LootCrate("Normal crate", 20);
        new LootCrate("Epic crate", 50);
        new LootCrate("Rare crate", 80);
        new LootCrate("Legendary crate", 150);

        while (keepRunning) {
            menu();
            System.out.println();
        }
        System.out.println("Loot Crate System closing...");


    }

    private static void menu(){

        System.out.println("Welcome to the LootCrate System");
        System.out.println("1. Add player");
        System.out.println("2. List of players");
        System.out.println("3. Add credits to player");
        System.out.println("4. Crates");
        System.out.println("5. Exit");
        System.out.println("Enter your choice: ");
        int choice = input.nextInt();
        input.nextLine();
        switch(choice){
            case 1:
                addPlayer();
                break;
            case 2:
                listPlayers();
                break;
            case 3:
                addCredits();
                break;
            case 4:
                startCrateMenu();
                break;
            case 5:
                keepRunning = false;
                break;
            default: System.out.println("Invalid choice");
            break;
        }
    }

    private static void startCrateMenu() {
        boolean inCrateMenu = true;
        while (inCrateMenu) {
            System.out.println();
            System.out.println("---Loot Crate System---");
            System.out.println("1. See available Loot crates");
            System.out.println("2. Open crate");
            System.out.println("3. Back");
            System.out.println("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    seeAvailableLootCrates();
                    break;
                case 2:
                    openLootCrate();
                    break;
                case 3:
                    inCrateMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;

            }
        }

    }

    private static void addPlayer(){
        System.out.println("---Add player---");
        System.out.print("Player name: ");
        String name = input.nextLine();

        System.out.print("\nPlayer's credits: ");
        int credits = input.nextInt();

        Player player = new Player(name, credits);
        players.put(name, player);

        System.out.println("Player added: " + player + " with " +  credits + " credits.");
    }

    private static void listPlayers(){
        for (Player player : players.values()){
            System.out.println(player);
        }
    }

    private static void addCredits(){
        System.out.println("---Add credits to player---");
        System.out.print("Player name: ");
        String name = input.nextLine();
        System.out.print("Add credits to " + name + ": ");
        int credits = input.nextInt();
        players.get(name).addCredits(credits);
    }

    private static void seeAvailableLootCrates(){
        LootCrate.ListOfCrates();
    }

    private static void openLootCrate(){
        System.out.println("---Loot Crates---");
        seeAvailableLootCrates();
        System.out.println();
        System.out.println("Choose a crate: ");
        String crateName = input.nextLine();

        LootCrate crate = LootCrate.getCrateByName(crateName);
        if (crate == null) {
            System.out.println("Crate not found!");
            return;
        }

        System.out.print("Enter player name: ");
        String playerName = input.nextLine();

        //Bruger checked exception til at se om spilleren findes samt se om de har nok credits
        try {
            Player player = findPlayerByName(playerName);
            crate.openCrate(player);
            System.out.println(playerName + " now has " + player.getCredits() + " credits left.");
        } catch (PlayerNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NotEnoughCreditsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    //Checked exception for at se om spilleren er der
    private static Player findPlayerByName(String name) throws PlayerNotFoundException {
        Player player = players.get(name);
        if (player == null) {
            throw new PlayerNotFoundException("Player '" + name + "' not found!");
        }
        return player;
    }




}