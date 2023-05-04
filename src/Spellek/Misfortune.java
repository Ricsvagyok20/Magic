package Spellek;
import Hos.Hos;

/**
 * A Spell osztaly gyereke, ha az ellenfeleid tul szerencsesek itt az ideje bevetni
 */
public class Misfortune extends Spell{
    public static int ar = 70;
    public static int mana = 8;

    public Misfortune() {

    }
    public void unlucky(Hos hos, Hos enemyhos, boolean robot){
        if(hos.getMana() < mana){
            if(!robot) {
                System.out.println("Bocs, de nincs eleg manad.");
            }
            return;
        }
        if(enemyhos.getLuck() < 3){
            enemyhos.setLuck(0);
        }
        else {
            enemyhos.setLuck(enemyhos.getLuck() - 3);
        }
        hos.setMana(hos.getMana() - mana);
    }
}
