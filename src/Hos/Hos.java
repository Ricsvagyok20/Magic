package Hos;
import Egysegek.*;

/**
 * A Hos osztaly szinten nagyon fontos resze a jateknak, mert a tulajdonsagoktol majdnem minden fugg
 */
public class Hos {
    private double dmg;
    private double def;
    private double spelldmg;
    private int mana;
    private int moral;
    private int luck;
    public Hos(){
        this.dmg = 1;
        this.def = 1;
        this.spelldmg = 1;
        this.mana = 10;
        this.moral = 1;
        this.luck = 1;
    }
    public Hos(int dmg, int def, int spelldmg, int mana, int moral, int luck) {
        this.dmg = dmg;
        this.def = def;
        this.spelldmg = spelldmg;
        this.mana = mana;
        this.moral = moral;
        this.luck = luck;
    }

    public double getDmg() {
        return dmg;
    }

    public double getDef() {
        return def;
    }

    public double getSpelldmg() {
        return spelldmg;
    }

    public void setDmg(double dmg) {
        this.dmg = dmg;
    }


    public void setDef(double def) {
        this.def = def;
    }

    public void setSpelldmg(double spelldmg) {
        this.spelldmg = spelldmg;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getMoral() {
        return moral;
    }

    public void setMoral(int moral) {
        this.moral = moral;
    }

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public void hosAttack(Egyseg opponent){
        opponent.takingDamage((int) (dmg*10));
    }
}
