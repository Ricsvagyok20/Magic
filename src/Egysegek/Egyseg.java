package Egysegek;
import Hos.Hos;

import java.util.Random;

/**
 * Egyik legfontosabb resze a jateknak az Egyseg class, ebbol szarmaznak az egysegek, sok kozos funkcio itt van megvalositva
 */
public class Egyseg {
    public static int price;
    protected int mindmg;
    protected int maxdmg;
    protected int hitpoints;
    protected int movespeed;
    protected int initiative;
    protected int amount;
    protected int maxhitpoints;
    protected boolean counterattacked;

    public boolean isCounterattacked() {
        return counterattacked;
    }

    public void setCounterattacked(boolean counterattacked) {
        this.counterattacked = counterattacked;
    }

    public int getMaxhitpoints() {
        return maxhitpoints;
    }


    public int getPrice() {
        return price;
    }

    public int getMindmg() {
        return mindmg;
    }

    public void setMindmg(int mindmg) {
        this.mindmg = mindmg;
    }

    public int getMaxdmg() {
        return maxdmg;
    }

    public void setMaxdmg(int maxdmg) {
        this.maxdmg = maxdmg;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }

    public int getMovespeed() {
        return movespeed;
    }

    public void setMovespeed(int movespeed) {
        this.movespeed = movespeed;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    /**
     * Az egyik legfontosabb része a csatának, ez az egysegek sebzodeset valositja meg, ha egy egyseg meghal akkor a mennyiseguk csokken.
     * @param amount a sebzes merteke amit kap az egyseg
     */
    public void takingDamage(int amount){
        while(amount > this.hitpoints){
            if(this.amount == 0){
                return;
            }
            this.amount--;
            amount -= this.hitpoints;
            this.hitpoints = this.maxhitpoints;
        }
        this.hitpoints -= amount;
        if(this.amount == 0){
            return;
        }
        if(this.hitpoints == 0){
            this.amount--;
            this.hitpoints = this.maxhitpoints;
        }
    }

    /**
     * Az egyseg korul minden egyes helyet megnez es ha van korulotte ellenseges egyseg, akkor igazzal ter vissza, kulonben hamissal
     * @param csatater a csatater string matrix ami tarolja az egysegek hol letet
     * @param x egyseg helyenek x koordinataja
     * @param y egyseg helyenek y koordinataja
     * @param robot robot e az egyseg, ha az akkor benne van a neveben az "E" betu es attol is fugg hogy mit keres hogy robot e vagy sem
     * @return hamisat ad vissza ha nincs mellette senki, amugy meg igazat
     */
    public boolean vanMellette(String[][] csatater, int x, int y, boolean robot){
        boolean vanmellette = false;
        for (int i = y - 1; i <= y+1; i++) {
            if(i < 0){
                continue;
            }
            if(i > csatater[0].length - 1){
                break;
            }
            if(!robot){
                if(x - 1 > 0){
                    if(csatater[x - 1][i].contains("E")){
                        vanmellette = true;
                        break;
                    }
                }
                if(csatater[x][i].contains("E")){
                    vanmellette = true;
                    break;
                }
                if(x + 1 < csatater.length) {
                    if (csatater[x + 1][i].contains("E")) {
                        vanmellette = true;
                        break;
                    }
                }
            }
            else{
                if(x - 1 > 0){
                    if(!csatater[x - 1][i].equals("-") && !csatater[x - 1][i].contains("E")){
                        vanmellette = true;
                        break;
                    }
                }
                if(!csatater[x][i].equals("-") && !csatater[x][i].contains("E")){
                    vanmellette = true;
                    break;
                }
                if(x + 1 < csatater.length) {
                    if (!csatater[x + 1][i].equals("-") && !csatater[x + 1][i].contains("E")) {
                        vanmellette = true;
                        break;
                    }
                }
            }

        }
        return vanmellette;
    }

    /**
     * Az egyseg korul minden egyes helyet megnez es ha az adott egyseg mellette van akkor igazat ad vissza
     * @param csatater a csatater string matrix ami tarolja az egysegek hol letet
     * @param x egyseg helyenek x koordinataja
     * @param y egyseg helyenek y koordinataja
     * @param robot robot e az egyseg, ha az akkor benne van a neveben az "E" betu es attol is fugg hogy mit keres hogy robot e vagy sem
     * @param egysegnevek az egysegek roviditett nevei amik ki vannak irva a csatateren
     * @param ki adott egyseg melyik indexen talalhato az egysegnevek tombben
     * @return hamisat ad vissza ha nincs mellette az adott egyseg, amugy meg igazat
     */
    public boolean kivanMellette(String[][] csatater, int x, int y, boolean robot, String[] egysegnevek, int ki){
        boolean vanmellette = false;
        for (int i = y - 1; i <= y+1; i++) {
            if(i < 0){
                continue;
            }
            if(i > csatater[0].length - 1){
                break;
            }
            if(!robot){
                if(x - 1 > 0){
                    if (csatater[x - 1][i].contains("E") && csatater[x - 1][i].contains(egysegnevek[ki])){
                        vanmellette = true;
                        break;
                    }
                }
                if(csatater[x][i].contains("E") && csatater[x][i].contains(egysegnevek[ki])){
                    vanmellette = true;
                    break;
                }
                if (x + 1 < csatater.length) {
                    if(csatater[x + 1][i].contains("E") && csatater[x + 1][i].contains(egysegnevek[ki])){
                        vanmellette = true;
                        break;
                    }
                }
            }
            else{
                if(x - 1 > 0){
                    if (!csatater[x - 1][i].equals("-") && csatater[x - 1][i].contains(egysegnevek[ki]) && !csatater[x - 1][i].contains("E")){
                        vanmellette = true;
                        break;
                    }
                }
                if(!csatater[x][i].equals("-") && csatater[x][i].contains(egysegnevek[ki]) && !csatater[x][i].contains("E")){
                    vanmellette = true;
                    break;
                }
                if (x + 1 < csatater.length) {
                    if(!csatater[x + 1][i].equals("-") && csatater[x + 1][i].contains(egysegnevek[ki]) && !csatater[x + 1][i].contains("E")){
                        vanmellette = true;
                        break;
                    }
                }
            }

        }
        return vanmellette;
    }

    /**
     * Egysegek mozgasanak megvalositasa ez is rendesen szereves reszet adja a csatanak
     * @param mennyi hany egysegnyit mozogjon az egyseg
     * @param csatater string matrix amiben az egysegek vannak
     * @param irany ez alapjan van eldontve hogy merre mennyit mozogjon az egyseg
     * @param x az egyseg aktualis helyenek x koordinataja
     * @param y az egyseg aktualis helyenek y koordinataja
     * @param nev az egyseg neve, ami a kiiraskor fontos hogy tudjuk kicsoda
     * @param robot fontos mert ha robot hozza kell fuzni hogy "E"
     * @return a csatater matrixot modositja majd visszaadja
     */

    public String[][] movement(int mennyi, String[][] csatater, String irany, int x, int y, String nev, boolean robot){
        int eredetix = x;
        int eredetiy = y;
        csatater[x][y] = "-";
        if(mennyi > this.movespeed){
            mennyi = this.getMovespeed();
        }
        if(mennyi < 0){
            mennyi = 0;
        }
        if(irany.contains("jobbra")){
            if(y + mennyi < 12){
                y += mennyi;
            }
            else if(!robot){
                System.out.println("Kimennel a palyarol");
            }
        }
        if(irany.contains("balra")){
            if(y - mennyi > 0){
                y -= mennyi;
            }
            else if(!robot){
                System.out.println("Kimennel a palyarol");
            }
        }
        if(irany.contains("fel")){
            if(x - mennyi > 0){
                x -= mennyi;
            }
            else if(!robot){
                System.out.println("Kimennel a palyarol");
            }
        }
        if(irany.contains("le")){
            if(x + mennyi < 10){
                x += mennyi;
            }
            else if(!robot){
                System.out.println("Kimennel a palyarol");
            }
        }
        if(robot){
            if(csatater[x][y].equals("-")){
                csatater[x][y] = nev + this.getAmount() + "E";
            }
            else{
                csatater[eredetix][eredetiy] = nev + this.getAmount() + "E";
            }
        }
        else{
            if(csatater[x][y].equals("-")){
                csatater[x][y] = nev + this.getAmount();
            }
            else{
                System.out.println("Nem tudtam menni, mert ott volt valaki.");
                csatater[eredetix][eredetiy] = nev + this.getAmount();
            }
        }
    return csatater;
}

    /**
     * Az egysegek visszatamadasa itt van megvalositva ami a Game-ben van hasznalva, fel sebzessel tamadnak vissza
     * @param opponent ellenseges egyseg, aki kapni fogja a sebzest
     * @param sajathos sajat hosnek a tamadasa es szerencseje miatt kell
     * @param ellenfelhos ellenfel hos vedekezese miatt kell
     */
    public void counterAttack(Egyseg opponent, Hos sajathos, Hos ellenfelhos){
        Random random = new Random();
        int dmg = 0;
        if(random.nextInt(0,100) < sajathos.getLuck() * 5){
            dmg = (int) Math.round(random.nextInt(mindmg, maxdmg+1) * (10+sajathos.getDmg())/10 * (1 - ellenfelhos.getDef()/20));
            System.out.println("Kritikus visszatamadas, sebzes: " + dmg*getAmount());
        }
        else{
            dmg = (int) Math.round(random.nextInt(mindmg, maxdmg+1) * (10+sajathos.getDmg())/10 * (1 - ellenfelhos.getDef()/20) / 2);
        }
        opponent.takingDamage(dmg*getAmount());
    }
}
