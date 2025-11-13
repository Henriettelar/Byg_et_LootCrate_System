import Exceptions.NotEnoughCreditsException;

import java.util.HashMap;
import java.util.Map;

public class LootCrate {
    private String crate;
    private  int credits;
    private static Map<String, LootCrate> crateList = new HashMap<>();

    public LootCrate(String crate, int credits) {
        this.crate = crate;
        this.credits = credits;
        crateList.put(crate, this); //bruges til getCrateByName
    }

    public int getCratePrize(){
        return credits;
    }
    public String getCrate() {
        return crate;
    }
    public static void ListOfCrates(){
        System.out.println("---Available crates---");
        for (Map.Entry<String, LootCrate> entry : crateList.entrySet()) {
            LootCrate crate = entry.getValue();
            System.out.println("-" + crate.getCrate() + " (" + crate.getCratePrize() + " credits)");
        }
    }

    public void openCrate(Player player) throws NotEnoughCreditsException {
        player.openLootCrate(this);
    }

    public static LootCrate getCrateByName(String name) {
        return crateList.get(name); //Kan returnere info om valgte Crate
    }

    @Override
    public String toString() {
        return crate + " (" + credits + " credits)";
    }

}
