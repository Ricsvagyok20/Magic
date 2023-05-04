package Spellek;
import Hos.Hos;
import Egysegek.Egyseg;

/**
 * A Spell osztaly gyereke, konnyu ujrapozicionalast tesz lehetove. Bajban vagy, esetleg nem birod mashogy elerni az ellenfeled? Itt a megoldas teleport.
 */
public class Teleport extends Spell{
    public static int ar = 50;
    public static int mana = 5;
    public Teleport() {

    }

    public boolean tp(String[][] csatater, String nev, int x, int y, Egyseg egyseg, Hos hos, boolean robot){
        boolean siker = true;
        if(hos.getMana() < mana){
            if(!robot){
                System.out.println("Bocs, de nincs eleg manad.");
            }
            return false;
        }
        if(!csatater[x][y].equals("-")){
            if(!robot){
                System.out.println("Ide nem lehet teleportálni, mert már van itt egy másik egység!");
            }
            siker = false;
        }
        else{
            if(!robot){
                csatater[x][y] = nev + egyseg.getAmount();
            }
            else{
                csatater[x][y] = nev + egyseg.getAmount() + "E";
            }
        }
        hos.setMana(hos.getMana() - mana);
        return siker;
    }
}
