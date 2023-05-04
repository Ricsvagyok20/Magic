package Egysegek;
import Hos.Hos;
import java.util.Random;

/**
 * Az Egyseg gyerek osztalya, a Ijasz az egyik alap egyseg a sebzesen allitva van balansz szemponjabol
 */
public class Ijasz extends Egyseg{
    Random random = new Random();
    public static int price = 6;
    public Ijasz(){
        this.mindmg = 3;
        this.maxdmg = 6;
        this.hitpoints = 7;
        this.maxhitpoints = 7;
        this.movespeed = 4;
        this.initiative = 9;
        this.counterattacked = false;
    }
    public void meleeAttack(Egyseg opponent, Hos sajathos, Hos ellenfelhos){
        int dmg = 0;
        if(random.nextInt(0,100) < sajathos.getLuck() * 5){
            dmg = (int) Math.round(random.nextInt(mindmg, maxdmg+1) * (10+sajathos.getDmg())/10 * (1 - ellenfelhos.getDef()/20)  * 2);
            System.out.println("Kritikus tamadas, sebzes: " + dmg*getAmount());
        }
        else{
            dmg = (int) Math.round(random.nextInt(mindmg, maxdmg+1) * (10+sajathos.getDmg())/10 * (1 - ellenfelhos.getDef()/20));
        }
        opponent.takingDamage(dmg*getAmount());
    }
    public void rangedAttack(Egyseg opponent, Hos sajathos, Hos ellenfelhos){

        int dmg = 0;
        if(random.nextInt(0,100) < sajathos.getLuck() * 5){
            dmg = (int) Math.round(maxdmg * (10+sajathos.getDmg())/10 * (1 - ellenfelhos.getDef()/20)  * 2);
            System.out.println("Kritikus tamadas, sebzes: " + dmg*getAmount());
        }
        else{
            dmg = (int) Math.round(maxdmg * (10+sajathos.getDmg())/10 * (1 - ellenfelhos.getDef()/20));
        }
        opponent.takingDamage(dmg*getAmount());
    }
}
