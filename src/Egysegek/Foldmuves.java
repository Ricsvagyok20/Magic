package Egysegek;
import Hos.Hos;
import java.util.Random;
/**
 * Az Egyseg gyerek osztalya, a Folmuves az egyik alap egyseg valtozatlan tulajdonsagokkal
 */
public class Foldmuves extends Egyseg{
    public static int price = 2;
    public Foldmuves(){
        this.mindmg = 1;
        this.maxdmg = 1;
        this.hitpoints = 3;
        this.maxhitpoints = 3;
        this.movespeed = 4;
        this.initiative = 8;
        this.counterattacked = false;
    }
    public void meleeAttack(Egyseg opponent, Hos sajathos, Hos ellenfelhos){
        Random random = new Random();
        if(random.nextInt(0,100) < sajathos.getLuck() * 5){
            opponent.takingDamage((int) (2 * getAmount() * (10+sajathos.getDmg())/10 * (1 - ellenfelhos.getDef()/20)));
            System.out.println("Kritikus tamadas, sebzes: " + (int) (2 * getAmount() * (10+sajathos.getDmg())/10 * (1 - ellenfelhos.getDef()/20)));
        }
        opponent.takingDamage((int) (getAmount() * (10+sajathos.getDmg())/10 * (1 - ellenfelhos.getDef()/20)));
    }
}
