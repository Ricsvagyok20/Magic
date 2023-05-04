package Egysegek;
import Hos.Hos;
import java.util.Random;

/**
 * Az Egyseg gyerek osztalya, a Griff az egyik alap egyseg a sebzesen allitva van balansz szemponjabol
 */
public class Griff extends Egyseg{
    Random random = new Random();
    public static int price = 20;
    public Griff(){
        this.mindmg = 7;
        this.maxdmg = 12;
        this.hitpoints = 30;
        this.maxhitpoints = 30;
        this.movespeed = 7;
        this.initiative = 15;
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
    //infinite counterattack
}
