package Spellek;

import Egysegek.*;
import Hos.Hos;

/**
 * A Spell osztaly gyereke, egy celpontos varazslat viszonylag nagy sebzessel, keves eletereju ellenfelekre gyors megoldas
 */
public class Lightning extends Spell{
    public static int ar = 60;
    public static int mana = 9;
    public Lightning() {

    }

    public void lightningAttack(Egyseg opponent, Hos hos, boolean robot){
        if(hos.getMana() < mana){
            if(!robot) {
                System.out.println("Bocs, de nincs eleg manad.");
            }
            return;
        }
        hos.setMana(hos.getMana() - mana);
        opponent.takingDamage((int) (hos.getSpelldmg() * 30));
    }
}
