package Egysegek;
import Hos.Hos;
import java.util.Random;

/**
 * Az Egyseg gyerek osztalya, az egyik kulonleges egyseg megvalositva, kulonleges kepessege a regeneralas
 */
public class Troll extends Egyseg{
    public static int price = 42;
    public Troll(){
        this.mindmg = 12;
        this.maxdmg = 20;
        this.hitpoints = 50;
        this.maxhitpoints = 50;
        this.movespeed = 5;
        this.initiative = 12;
        this.counterattacked = false;
    }
    public void meleeAttack(Egyseg opponent, Hos sajathos, Hos ellenfelhos){
        Random random = new Random();
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
    //Troll kulonleges kepesseg
    public void regen(){
        if(this.getHitpoints() < 50){
            this.setHitpoints(this.getHitpoints() + 50 - this.getHitpoints());
        }
    }
}
