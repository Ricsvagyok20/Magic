package Spellek;
import Egysegek.*;
import Hos.Hos;

/**
 * A Spell osztaly gyereke, az egyik legerosebb varazslat egy 3x3-as teruleten minden egyseget sebez
 */
public class Fireball extends Spell{
    public static int ar = 120;
    public static int mana = 9;
    public Fireball() {

    }
    public void areadmg(String[][] csatater, int x, int y, Egyseg[] sajategysegek, Egyseg[] robotegysegek, Hos hos, String[] egysegnevek, boolean robot){
        if(hos.getMana() < mana){
            if(!robot) {
                System.out.println("Bocs, de nincs eleg manad.");
            }
            return;
        }
        for (int i = y - 1; i <= y+1; i++) {
            if(i < 0){
                continue;
            }
            if(i > csatater[0].length - 1){
                break;
            }
            if(x - 1 > 0){
                if (!csatater[x - 1][i].equals("-")) {
                    for (int j = 0; j < egysegnevek.length; j++){
                        if(csatater[x - 1][i].contains(egysegnevek[j]) && !csatater[x - 1][i].contains("E")){
                            sajategysegek[j].takingDamage((int) (hos.getSpelldmg()*20));
                            break;
                        }
                        else if(csatater[x - 1][i].contains(egysegnevek[j]) && csatater[x - 1][i].contains("E")){
                            robotegysegek[j].takingDamage((int) (hos.getSpelldmg()*20));
                            break;
                        }
                    }
                }
            }
            if (!csatater[x][i].equals("-")) {
                for (int j = 0; j < egysegnevek.length; j++){
                    if(csatater[x][i].contains(egysegnevek[j]) && !csatater[x][i].contains("E")){
                        sajategysegek[j].takingDamage((int) (hos.getSpelldmg()*20));
                        break;
                    }
                    else if(csatater[x][i].contains(egysegnevek[j]) && csatater[x][i].contains("E")){
                        robotegysegek[j].takingDamage((int) (hos.getSpelldmg()*20));
                        break;
                    }
                }
            }
            if(x + 1 < 10){
                if (!csatater[x + 1][i].equals("-")) {
                    for (int j = 0; j < egysegnevek.length; j++){
                        if(csatater[x + 1][i].contains(egysegnevek[j]) && !csatater[x + 1][i].contains("E")){
                            sajategysegek[j].takingDamage((int) (hos.getSpelldmg()*20));
                            break;
                        }
                        else if(csatater[x + 1][i].contains(egysegnevek[j]) && csatater[x + 1][i].contains("E")){
                            robotegysegek[j].takingDamage((int) (hos.getSpelldmg()*20));
                            break;
                        }
                    }
                }
            }
        }
        hos.setMana(hos.getMana() - mana);
    }
}
