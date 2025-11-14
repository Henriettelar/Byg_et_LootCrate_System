import Exceptions.NotEnoughCreditsException;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LootCrate {
    private String crate;
    private  int credits;
    private static Map<String, LootCrate> crateList = new HashMap<>();
    private static final String[] itemsForNormal = {
            "Normal Sword", "Healing Potion", "Iron Armor", "Low-level Magic Wand"
    };
    private static final String[] itemsForRare = {
            "Iron Sword", "Strength Potion", "Normal Magic Wand", "Tall Boots"
    };
    private static final String[] itemsForEpic = {
            "Magic Bow", "Speed Potion", "Big Hat", "Fire Arrows"
    };
    private static final String[] itemsForLegendary = {
            "Golden Sword", "Stealth Potion", "Stealth Armor", "Spiked Mace"
    };


    public LootCrate(String crate, int credits) {
        this.crate = crate;
        this.credits = credits;
        crateList.put(crate, this); //bruges til getCrateByName
    }

    public int getCratePrice(){
        return credits;
    }
    public String getCrate() {
        return crate;
    }
    public static void ListOfCrates(){
        System.out.println("---Available crates---");
        for (Map.Entry<String, LootCrate> entry : crateList.entrySet()) {
            LootCrate crate = entry.getValue();
            System.out.println("-" + crate.getCrate() + " (" + crate.getCratePrice() + " credits)");
        }
    }

    public void openCrate(Player player) throws NotEnoughCreditsException {
        player.openLootCrate(this);
        String reward = getRandomReward();
        player.addItem(reward);
        System.out.println("Player " + player.getUsername() + " received " + reward + "!");
    }

    //Metode til at vælge en reward til crate baseret på tilfældighed men stadig med større chance ud fra hvad spilleren åbner
    private String getRandomReward() {
        Random rand = new Random();

        // Weighted choice based on crate type
        if (crate.equalsIgnoreCase("Normal crate")) {
            return chooseWeighted(
                    new String[][]{itemsForNormal, itemsForRare, itemsForEpic, itemsForLegendary},
                    new int[]{70, 20, 8, 2}   // weights i procent
            );
        }
        if (crate.equalsIgnoreCase("Rare crate")) {
            return chooseWeighted(
                    new String[][]{itemsForNormal, itemsForRare, itemsForEpic, itemsForLegendary},
                    new int[]{30, 50, 15, 5}
            );
        }
        if (crate.equalsIgnoreCase("Epic crate")) {
            return chooseWeighted(
                    new String[][]{itemsForNormal, itemsForRare, itemsForEpic, itemsForLegendary},
                    new int[]{10, 25, 45, 20}
            );
        }
        if (crate.equalsIgnoreCase("Legendary crate")) {
            return chooseWeighted(
                    new String[][]{itemsForNormal, itemsForRare, itemsForEpic, itemsForLegendary},
                    new int[]{0, 20, 30,50}
            );
        }
        return "Unknown reward";
    }

    public static LootCrate getCrateByName(String name) {
        return crateList.get(name); //Kan returnere info om valgte Crate
    }

    private String chooseWeighted(String[][] itemPools, int[] weights) {
        Random rand = new Random();
        int total = 0;

        for (int weight : weights) {
            total += weight;
        }

        int randomValue = rand.nextInt(total) + 1;

        int cumulative = 0;
        for (int i = 0; i < weights.length; i++) {
            cumulative += weights[i];
            if (randomValue <= cumulative) {
                // vælg et random item fra den valgte pool
                String[] pool = itemPools[i];
                return pool[rand.nextInt(pool.length)];
            }
        }
        return "Nothing";
    }

    @Override
    public String toString() {
        return crate + " (" + credits + " credits)";
    }

}
