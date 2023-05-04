package Spellek;
import Hos.Hos;
import Egysegek.*;

/**
 * A Spell osztaly gyereke, az egyik legerosebb varazslat egysegeidet konnyen ujra elesztheted, ezzel ujdonsult tempora tehetsz szert, teljesen halott egysegeket mar nem
 * lehet visszahozni, de amig 1 el addig mindenkit fel lehet tamasztani
 */
public class Revive extends Spell{
    public static int ar = 120;
    public static int mana = 9;
    public Revive() {
    }

    public void cast(Egyseg egyseg, Hos hos, int[] maxamounts, int melyikegyseg, boolean robot){
        if(hos.getMana() < mana){
            if(!robot) {
                System.out.println("Bocs, de nincs eleg manad.");
            }
            return;
        }
        double szamlalo = hos.getSpelldmg()*50;
        while (szamlalo > 0){
            if(egyseg.getAmount() == maxamounts[melyikegyseg]){
                break;
            }
            else{
                egyseg.setAmount(egyseg.getAmount()+1);
            }
            szamlalo -= egyseg.getHitpoints();
        }
        hos.setMana(hos.getMana() - mana);
    }
    //Reviveol egy saját karaktert akár többet is spelldmg * 50 hp összesen több nem lehet mint volt
}
