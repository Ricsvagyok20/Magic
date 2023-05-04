package Main;
import java.util.*;
import Hos.Hos;
import Spellek.*;
import Egysegek.*;

/**
 * A Game osztalyban van megvalositva maga a jatek es annak minden segito fuggvenye, a main fuggvenyt lefuttatva lehet inditani a jatekot.
 */
public class Game {
    private static String command;
    private static int szamcommand;
    private static int szamlalo = 0;
    private static Scanner console = new Scanner(System.in);
    private static Player jatekos = new Player();
    private static Player bot = new Player();
    private static Egyseg[] sajategysegek = new Egyseg[5];
    private static Egyseg[] botegysegek = new Egyseg[5];
    private static Hos sajathos = new Hos();
    private static Hos bothos = new Hos();
    private static Spell[] sajatspellek = new Spell[5];
    private static Spell[] botspellek = new Spell[5];
    private static Random random = new Random();
    private static String[] abc = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    private static String[][] csatater = new String[10][12];
    private static double hosupgrade = 5;
    private static int[] spellek = new int[sajatspellek.length];
    private static int[] egysegek = new int[sajategysegek.length];
    private static String[] egysegnevek = {"elf", "flm", "gri", "ija", "trl"};
    private static String[] spellnevek = {"teleportalas", "szerencsetlenseg", "tuzlabda", "villamcsapas", "feltamasztas"};
    private static int[] sajatmaxamounts = new int[sajategysegek.length];
    private static int[] botmaxamounts = new int[botegysegek.length];
    private static int[] egysegar = {Elf.price,Foldmuves.price,Griff.price,Ijasz.price,Troll.price};
    private static boolean[] vissztamadott = new boolean[5];
    private static boolean[] vissztamadottbot = new boolean[5];

    public static void Title(){
        System.out.println("Kezdodik a jatek!");
    }

    /**
     * A Fomenu metodus, ami megvan hivva a mainben a jatek nehezsegi szintjenek a kivalasztasat valositja meg
     */
    public static void Fomenu(){
        System.out.println("Udvozollek a fomenuben valaszd ki a nehezsegi szintet.");
        System.out.println("Konnyu(1) Kozepes(2) Nehez(3)");


        boolean joertek = false;
        while (!joertek){
            command = console.next();
            while(!isNumeric(command)){
                command = console.next();
            }
            szamcommand = Integer.parseInt(command);
            if(szamcommand == 1){
                jatekos.setArany(1300);
                joertek = true;
            }
            if(szamcommand == 2){
                jatekos.setArany(1000);
                joertek = true;
            }
            if(szamcommand == 3){
                jatekos.setArany(700);
                joertek = true;
            }
            if(!joertek){
                System.out.println("Nincs ilyen nehezsegi szint adj egy szamot 1-tol 3-ig.");
            }
        }
        System.out.println();
    }

    /**
     * A Vasarlas metodus, ami megvan hivva a mainben a hos fejlesztesek es spell vagy egyseg vasarlasok megvalositasa
     */
    public static void Vasarlas(){
        System.out.println("Most, hogy kivalasztottad a nehezseget allitsd ossze a csapatod!");
        System.out.println();
        int mertek = 1;
        int egyseg = 1;
        int eredetimertek = 1;
        boolean vettegyseget = true;
        int szamlalo = 0;
        System.out.println("Hosod tulajdonsagai");
        System.out.format("Sebzes: %.0f \t", sajathos.getDmg());
        System.out.format("Vedekezes: %.0f \t", sajathos.getDef());
        System.out.format("Varazsero: %.0f \t", sajathos.getSpelldmg());
        System.out.print("Manna: " + (sajathos.getMana()/10) + "\t");
        System.out.print("Moral: " + sajathos.getMoral() + "\t");
        System.out.print("Szerencse: " + sajathos.getLuck() + "\n");
        System.out.println();
        while(jatekos.getArany() > 2){
            System.out.println("Hos tulajdonsag fejlesztese (hos), Spell vasarlas (spell), Egyseg vasarlas (egyseg), Tovabblepes(stop)");
            System.out.println("Jelenlegi aranyad: " + jatekos.getArany());
            command = "";
            if(jatekos.getArany() < 100){
                for (int i = 0; i < sajategysegek.length; i++){
                    if(sajategysegek[i] == null){
                        szamlalo++;
                    }
                }
                if(szamlalo == 5){
                    vettegyseget = false;
                }
                if(!vettegyseget){
                    System.out.println("Kevesebb, mint 100 aranyad van, es ha nem veszel egyseget azonnal veszites, ezert beviszunk egyseget venni.");
                    command = "egyseg";
                }
            }
            command = console.next();
            if(command.equals("stop")){
                System.out.println("Vasarlas vege!");
                break;
            }
            if(!vettegyseget){
                command = "egyseg";
            }
            if(command.equals("hos")){
                int tulajdonsag = 1;
                boolean kisebbtiz = true;
                System.out.println("Sebzes(1), Vedekezes(2), Varazsero(3), Manna(4), Moral(5), Szerencse(6)");
                command = console.next();
                while(!isNumeric(command)){
                    command = console.next();
                }
                szamcommand = Integer.parseInt(command);
                while(szamcommand < 1 || szamcommand > 6){
                    System.out.println("Nem megfelelo fejlesztest valasztottal, adj meg egy szamot 1-tol 6-ig.");
                    command = console.next();
                    while(!isNumeric(command)){
                        command = console.next();
                    }
                    szamcommand = Integer.parseInt(command);
                }
                tulajdonsag = szamcommand;
                System.out.println("Mennyit szeretnel fejleszeteni rajta (1-9, mivel 10nel nagyobb nem lehet egy adott tulajdonsag).");
                command = console.next();
                while(!isNumeric(command)){
                    command = console.next();
                }
                szamcommand = Integer.parseInt(command);
                while(szamcommand < 1 || szamcommand > 9){
                    System.out.println("Nem megfelelo mertek, adj meg egy szamot 1-tol 9-ig.");
                    command = console.next();
                    while(!isNumeric(command)){
                        command = console.next();
                    }
                    szamcommand = Integer.parseInt(command);
                }
                mertek = szamcommand;
                eredetimertek = szamcommand;
                boolean robot = false;
                hosupgrade = hosFejlesztes(tulajdonsag, mertek, eredetimertek, hosupgrade, kisebbtiz, jatekos, sajathos, robot);
            }
            if(command.equals("spell")){
                System.out.println("Teleport(1), ara: " + Teleport.ar + " mana koltsege: " + Teleport.mana);
                System.out.println("Szerencsetlenseg(2), ara: " + Misfortune.ar + " mana koltsege: " + Misfortune.mana);
                System.out.println("Fireball(3), ara: " + Fireball.ar + " mana koltsege: " + Fireball.mana);
                System.out.println("Villamcsapas(4), ara: " + Lightning.ar + " mana koltsege: " + Lightning.mana);
                System.out.println("Feltamasztas(5), ara: " + Revive.ar + " mana koltsege: " + Revive.mana);
                command = console.next();
                while(!isNumeric(command)){
                    command = console.next();
                }
                szamcommand = Integer.parseInt(command);
                while(szamcommand < 1 || szamcommand > 5){
                    System.out.println("Nem megfelelo spell, adj meg egy szamot 1-tol 5-ig.");
                    command = console.next();
                    while(!isNumeric(command)){
                        command = console.next();
                    }
                    szamcommand = Integer.parseInt(command);
                }
                for (int i = 0; i < spellek.length; i++){
                    if(szamcommand == i+1 && spellek[i] != 0){
                        System.out.println("Ezt mar megvetted, valamely masikat valaszd.");
                        command = console.next();
                        while(!isNumeric(command)){
                            command = console.next();
                        }
                        szamcommand = Integer.parseInt(command);
                    }
                }
                if(jatekos.getArany() - spellek[szamcommand - 1] < 0){
                    System.out.println("Erre mar nincs eleg penzed!");
                    continue;
                }
                spellVasarlas(szamcommand, spellek, sajatspellek);
                jatekos.setArany(jatekos.getArany() - spellek[szamcommand - 1]);
            }
            if(command.equals("egyseg")) {
                System.out.println("Elf(1), Foldmuves(2), Griff(3), Ijasz(4), Troll(5)");
                command = console.next();
                while(!isNumeric(command)){
                    command = console.next();
                }
                szamcommand = Integer.parseInt(command);
                while(szamcommand < 1 || szamcommand > 5) {
                    System.out.println("Nem megfelelo egyseg, adj meg egy szamot 1-tol 5-ig.");
                    command = console.next();
                    while(!isNumeric(command)){
                        command = console.next();
                    }
                    szamcommand = Integer.parseInt(command);
                }
                for (int i = 0; i < egysegek.length; i++) {
                    if (szamcommand == i + 1 && egysegek[i] != 0) {
                        System.out.println("Ezt mar megvetted, valamely masikat valaszd.");
                        command = console.next();
                        while(!isNumeric(command)){
                            command = console.next();
                        }
                        szamcommand = Integer.parseInt(command);
                    }
                }
                egyseg = szamcommand;
                System.out.println("Mennyi egyseget vennel?");
                command = console.next();
                while(!isNumeric(command)){
                    command = console.next();
                }
                szamcommand = Integer.parseInt(command);
                mertek = szamcommand;
                eredetimertek = szamcommand;
                boolean semmipenz = false;
                while(jatekos.getArany() - mertek * egysegar[egyseg - 1] < 0){
                    if(jatekos.getArany() < egysegar[egyseg - 1]){
                        semmipenz = true;
                        break;
                    }
                    System.out.println("Sajnos ennyi egyseget nem tudsz venni ebbol a fajtabol, mert nincs eleg penzed, adj meg egy masik mennyiseget.");
                    command = console.next();
                    while(!isNumeric(command)){
                        command = console.next();
                    }
                    szamcommand = Integer.parseInt(command);
                    mertek = szamcommand;
                }
                if(!semmipenz){
                    egysegVasarlas(egyseg, mertek, sajategysegek, sajatmaxamounts);
                    jatekos.setArany(jatekos.getArany() - mertek * egysegar[egyseg - 1]);
                }
            }
        }
    }

    /**
     * A spellek megvasarlasa van megvalositva, ezt a metodust a Vasarlas hasznalja
     * @param mit felhasznalotol bekert adattag, ami megadja melyik spellt venne meg a jatekos
     * @param spellek a spellek tomb ellenorzes keppen van hogy egy spellt ne tudjon megvenni ketszer
     * @param kispellje bot vagy sajat spellek tomb
     */
    public static void spellVasarlas(int mit, int[] spellek, Spell[] kispellje){
        switch(mit){
            case 1:
                kispellje[0] = new Teleport();
                spellek[0] = Teleport.ar;
                break;
            case 2:
                kispellje[1] = new Misfortune();
                spellek[1] = Misfortune.ar;
                break;
            case 3:
                kispellje[2] = new Fireball();
                spellek[2] = Fireball.ar;
                break;
            case 4:
                kispellje[3] = new Lightning();
                spellek[3] = Lightning.ar;
                break;
            case 5: kispellje[4] = new Revive();
                spellek[4] = Revive.ar;
                break;
        }
    }

    /**
     * Az egysegek vasarlasa van megvalositva a Vasarlas metodus hasznalja
     * @param egyseg felhasznalotol bekert ertek, altala lehet eldonteni melyik egyseget szerente a jatekos
     * @param mertek az egyseg vasarolni kivant mennyisege
     * @param kiegysege meghivaskor fontos hogy ki vasarol, ez alapjan fog az adott egyseg tombbe(sajat vagy bot) megjelenni a kivant egyseg
     */
    public static void egysegVasarlas(int egyseg, int mertek, Egyseg[] kiegysege, int[] maxamounts){
        switch (egyseg) {
            case 1:
                kiegysege[0] = new Elf();
                kiegysege[0].setAmount(mertek);
                maxamounts[0] = mertek;
                egysegek[0] = egysegar[0];
                break;
            case 2:
                kiegysege[1] = new Foldmuves();
                kiegysege[1].setAmount(mertek);
                maxamounts[1] = mertek;
                egysegek[1] = egysegar[1];
                break;
            case 3:
                kiegysege[2] = new Griff();
                kiegysege[2].setAmount(mertek);
                maxamounts[2] = mertek;
                egysegek[2] = egysegar[2];
                break;
            case 4:
                kiegysege[3] = new Ijasz();
                kiegysege[3].setAmount(mertek);
                maxamounts[3] = mertek;
                egysegek[3] = egysegar[3];
                break;
            case 5:
                kiegysege[4] = new Troll();
                kiegysege[4].setAmount(mertek);
                maxamounts[4] = mertek;
                egysegek[4] = egysegar[4];
                break;
        }
    }

    /**
     * A hos tulajdonsagainak fejleszteset valostijuk meg, a Vasarlas metodus hivja meg
     * @param tulajdonsag a tulajdonsag amit fejleszteni szeretne a jatekos
     * @param mertek aktualis mertek, amivel noveljuk az adott tulajdonsagot
     * @param eredetimertek eredeti mertek, amivel novelni akarja az adott tulajdonsagot
     * @param hosupgrade a hosfejlesztes akkori ara, amit majd kesobb vissza is adunk frissitve
     * @param kisebbtiz elerte e mar az adott tulajdonsag a tizet, ha igen nem tud tobbet fejleszteni rajta
     * @param player a vasarlo jatekosa
     * @param hos a vasarlo hose
     * @param robote benne van a neveben annyit figyel hogy a robot vasarol-e mert abban az esetben nem irunk ki hibauzenetet
     * @return visszaadja a hosfejlesztes legfrissebb arat
     */
    public static double hosFejlesztes(int tulajdonsag, int mertek, int eredetimertek, double hosupgrade, boolean kisebbtiz, Player player, Hos hos, boolean robote){
        while (mertek > 0){
            switch (tulajdonsag){
                case 1: if(hos.getDmg() < 10){hos.setDmg(hos.getDmg() + 1);} else{kisebbtiz = false;} break;
                case 2: if(hos.getDef() < 10){hos.setDef(hos.getDef() + 1);} else{kisebbtiz = false;} break;
                case 3: if(hos.getSpelldmg() < 10){hos.setSpelldmg(hos.getSpelldmg() + 1);} else{kisebbtiz = false;} break;
                case 4: if(hos.getMana() < 100){hos.setMana(hos.getMana() + 10);} else{kisebbtiz = false;} break;
                case 5: if(hos.getMoral() < 10){hos.setMoral(hos.getMoral() + 1);} else{kisebbtiz = false;} break;
                case 6: if(hos.getLuck() < 10){hos.setLuck(hos.getLuck() + 1);} else{kisebbtiz = false;} break;
            }
            if(!kisebbtiz){
                if(!robote){
                    System.out.println("10-nel nem lehet nagyobb egy tulajdonsag se!");
                }
                break;
            }
            if(player.getArany() - (int)Math.ceil(hosupgrade) < 0){
                if(!robote){
                    System.out.println("Sajnos nem volt penzed ennyi fejlesztesre, de sikerult " + (eredetimertek-mertek) + " fejlesztest megejteni.");
                }
                break;
            }
            player.setArany(player.getArany() - (int)Math.ceil(hosupgrade));
            hosupgrade *= 1.1;
            hosupgrade = Math.ceil(hosupgrade);
            mertek--;
        }
        return hosupgrade;
    }

    /**
     * A Felkeszules metodus meg van hivva a mainben es a csata elotti egyseg elhelyezes van megvalositva
     * @return Igazat ad vissza ha van legalabb egy egysege a jatekosnak kulonben hamisat, ez alapjan dol el hogy elindul e egyaltalan a csata
     */
    public static boolean Felkeszules(){
        System.out.println("Felkeszules kezdete, alabb lathatod gepi ellenfeled pontjait es kepessegeit.");
        System.out.format("Sebzes pontok: %.0f \t", bothos.getDmg());
        System.out.format("Vedekezo pontok: %.0f \t", bothos.getDef());
        System.out.format("Varazsero pontok: %.0f \t", bothos.getSpelldmg());
        System.out.print("Mana pontok: " + (bothos.getMana()/10) + "\t");
        System.out.print("Moral pontok: " + bothos.getMoral() + "\t");
        System.out.print("Szerencse pontok: " + bothos.getLuck() + "\n");
        System.out.print("Ellenfel varazslatai: \t");
        for (int i = 0; i < botspellek.length; i++){
            if(botspellek[i] != null){
                System.out.print(spellnevek[i] + "\t");
            }
        }
        System.out.println();
        String[] lerakniegysegek = new String[5];
        int szamlalo = 0;
        for (int i = 0; i < sajategysegek.length; i++){
            if(sajategysegek[i] == null){
                szamlalo++;
            }
        }
        if(szamlalo == 5){
            System.out.println("Nincs egy darab egységed sem, sajnalom de vesztettel!");
            return false;
        }
        System.out.println("Itt lathato a csatater.");
        String sor;
        int sorszam = 1;
        int oszlop = 1;
        boolean vege = false;
        System.out.println("A stop szo kiirasaig lehetoseged van a csatater bal oldalan elhelyezni az egysegeidet.");
        System.out.println("Sajat egysegeid: ");
        for (int i = 0; i < sajategysegek.length; i++){
            if(sajategysegek[i] != null){
                System.out.print(egysegnevek[i] + " ");
                lerakniegysegek[i] = egysegnevek[i];
            }
        }
        System.out.println();
        while(true){
            Csatater(csatater);
            System.out.println("Melyik egyseget szeretned elhelyezni?");
            for (String asd: lerakniegysegek){
                if(asd == null){
                    continue;
                }
                System.out.print(asd + " ");
            }
            System.out.println();
            command = console.next();
            if(command.equals("stop")){
                break;
            }
            for(int i = 0; i < lerakniegysegek.length; i++){
                if(lerakniegysegek[i] == null){
                    continue;
                }
                if(command.equals(lerakniegysegek[i])){
                    System.out.println("Melyik sorba? (A-J)");
                    sor = console.next();
                    if(sor.equals("stop")){
                        vege = true;
                        break;
                    }
                    boolean nincsbenne = false;
                    while(!nincsbenne){
                        for(int j = 0; j < abc.length;j++ ) {
                            if(sor.equals(abc[j])){
                                nincsbenne = true;
                                break;
                            }
                        }
                        if(!nincsbenne){
                            System.out.println("Csak A-tol J-ig van a csatater, azon kivulre nem tudsz egyseget helyezni.");
                            sor = console.next();
                        }
                    }
                    System.out.println("Melyik oszlop? (1-2)");
                    command = console.next();
                    if(command.equals("stop")){
                        vege = true;
                        break;
                    }
                    while(!isNumeric(command)){
                        command = console.next();
                    }
                    oszlop = Integer.parseInt(command);
                    while(oszlop > 2 || oszlop < 1){
                        System.out.println("Csak az elso ket sorba helyezheted az egysegeidet.");
                        command = console.next();
                        while(!isNumeric(command)){
                            command = console.next();
                        }
                        oszlop = Integer.parseInt(command);
                    }
                    for(int j = 0; j < abc.length;j++ ) {
                        if(sor.equals(abc[j])){
                            sorszam = j;
                            break;
                        }
                    }
                    if(!csatater[sorszam][oszlop - 1].equals("-")){
                        System.out.println("Ide mar raktal egy masik egyseget, tedd mashova!");
                        break;
                    }
                    csatater[sorszam][oszlop - 1] = lerakniegysegek[i] + sajategysegek[i].getAmount();
                    lerakniegysegek[i] = null;
                }
            }
            if(vege){
                break;
            }
            szamlalo = 0;
            for (String asd: lerakniegysegek){
                if(asd == null){
                    szamlalo++;
                }
            }
            if(szamlalo == 5){
                break;
            }
        }
        return true;
    }

    /**
     * Mindig az aktualis csatateret irja ki formazva
     * @param csatater Az a string matrix ami az egesz jatek folyaman folyamatosan frissul
     */
    public static void Csatater(String[][] csatater) {
        int x = 10;
        int y = 12;

        System.out.format("    ");
        for(int i = 1; i <= y;i++ ) {
            System.out.format("%7d",i);
        }
        System.out.println();
        for(int i = 0; i < (y+1)*7;i++ ) {
            System.out.print("-");
        }
        System.out.println();
        for(int i = 0;i < x;i++) {
            System.out.format("%2s |", abc[i]);
            for(int j = 0;j < y;j++) {
                if(csatater[i][j] == null){
                    csatater[i][j] = "-";
                    System.out.format("%7s", csatater[i][j]);
                }
                else {
                    System.out.format("%7s", csatater[i][j]); //3 betus egyseg rovidites, 3 szamos mennyiseg
                }
            }
            System.out.println();
        }
    }

    /**
     *
     */
    public static void aiFelkeszul(){
        Arrays.fill(egysegek, 0);
        Arrays.fill(spellek, 0);
        hosupgrade = 5;
        int mertek = 1;
        int egyseg = 1;
        int egysegetvegyen = 0;
        while(bot.getArany() > 30){
            int donto = random.nextInt(1,101);
            if(egysegetvegyen == 0){
                donto = 70;
            }
            if(donto < 31){
                int tulajdonsag = random.nextInt(1,7);
                boolean kisebbtiz = true;
                boolean robot = true;
                mertek = random.nextInt(1,10);
                int eredetimertek = mertek;
                hosFejlesztes(tulajdonsag,mertek,eredetimertek,hosupgrade,kisebbtiz,bot,bothos,robot);
            }
            else if(donto < 51){
                int mit = random.nextInt(1,6);
                for (int i = 0; i < spellek.length; i++) {
                    if (mit == i + 1 && spellek[i] != 0) {
                        mit = random.nextInt(1,6); //Ugyanazt ne vegye meg
                    }
                }
                if(bot.getArany() - spellek[mit - 1] < 0){
                    continue;
                }
                spellVasarlas(mit,spellek,botspellek);
                bot.setArany(bot.getArany() - botspellek[mit - 1].getAr());
            }
            else {
                egyseg = random.nextInt(1,6);
                for (int i = 0; i < egysegek.length; i++) {
                    if (egyseg == i + 1 && egysegek[i] != 0) {
                        egyseg = random.nextInt(1,6); //Ugyanazt ne vegye meg
                    }
                }
                mertek = random.nextInt(15,40);
                while(bot.getArany() - mertek * egysegar[egyseg - 1] < 0){
                    mertek = random.nextInt(1,20); //0-nal tobb penze kell h legyen
                }
                egysegVasarlas(egyseg,mertek,botegysegek, botmaxamounts);
                bot.setArany(bot.getArany() - mertek * egysegar[egyseg - 1]);
            }
            egysegetvegyen++;
        }
        for(int i = 0; i < botegysegek.length; i++){
            if(botegysegek[i] == null){
                continue;
            }
            int x = random.nextInt(0,10);
            int y = random.nextInt(10, 12);
            csatater[x][y] = egysegnevek[i] + botegysegek[i].getAmount() + "E";
        }
    }

    public static void csataterFrissito(){
        int[] hol;
        for (int i = 0; i < sajategysegek.length; i++){
            if(sajategysegek[i] == null){
                continue;
            }
            hol = holVan(i, false);
            csatater[hol[0]][hol[1]] = egysegnevek[i] + sajategysegek[i].getAmount();
        }
        for (int i = 0; i < botegysegek.length; i++){
            if(botegysegek[i] == null){
                continue;
            }
            hol = holVan(i, true);
            csatater[hol[0]][hol[1]] = egysegnevek[i] + botegysegek[i].getAmount() + "E";
        }
        Csatater(csatater);
    }

    //Ennek tudnia kell egyseg hanyadik helyen van az egysegnevekben
    public static int[] holVan(int egyseg, boolean robot){
        int[] hol = new int[2];
        for (int i = 0; i < csatater.length; i++){
            for (int j = 0; j < csatater[i].length; j++){
                if(!robot && csatater[i][j].contains(egysegnevek[egyseg]) && !csatater[i][j].contains("E")){
                    hol[0] = i;
                    hol[1] = j;
                    break;
                }
                if(robot && csatater[i][j].contains(egysegnevek[egyseg]) && csatater[i][j].contains("E")){
                    hol[0] = i;
                    hol[1] = j;
                    break;
                }
            }
        }
        return hol;
    }

    public static void kitTamad(Egyseg[] egysegs){
        System.out.println("Kit szeretnel tamadni?");
        for (int i = 0; i < egysegs.length;i++){
            if(egysegs[i] == null){
                continue;
            }
            System.out.print(egysegnevek[i] + " ");
        }
        System.out.println();
    }

    public static void kitReviveol(Egyseg[] egysegs){
        System.out.println("Kit szeretnel reviveolni/teleportalni?");
        for (int i = 0; i < egysegs.length;i++){
            if(egysegs[i] == null){
                continue;
            }
            System.out.print(egysegnevek[i] + " ");
        }
        System.out.println();
    }

    /**
     * Ez a metodus ellenorzi hogy mindenki el meg megvan hivva a Csata metodusban es ha nem el mar senki visszater hamissal es vege a Csatanak
     * @param egysegs sajat vagy bot egysegtomb amiben keressuk a halott egysegeket
     * @return igazzal ter vissza ha nem halt meg mindenki, hamissal ha mindenki halott
     */
    public static boolean mindenkiEl(Egyseg[] egysegs){
        int szamlalo = 0;
        for (int i = 0; i < egysegs.length;i++){
            if(egysegs[i] == null){
                szamlalo++;
            }
        }
        if(szamlalo == 5){
            return false;
        }
        return true;
    }

    public static int nullAmount(Egyseg[] kiegysege){ //Hol a 0 amountu pali az egysegek kozott, eztan kell megkeresni, -1-et ad vissza ha nincs ilyen fontos
        int nulla = -1;
        for (int i = 0; i < kiegysege.length;i++){
            if(kiegysege[i] == null){
                continue;
            }
            if(kiegysege[i].getAmount() == 0){
                nulla = i;
            }
        }
        return nulla;
    }

    public static int[][] initiativeSzamolo(){
        int[][] initiativetomb = new int[10][3];
        int[] initiative = new int[10];
        int[] rendezett = new int[10];
        for (int i = 0; i < sajategysegek.length; i++){
            if(sajategysegek[i] == null){
                continue;
            }
            initiative[szamlalo] = sajategysegek[i].getInitiative() + sajathos.getMoral();
            szamlalo++;
        }
        for (int i = 0; i < botegysegek.length; i++){
            if(botegysegek[i] == null){
                continue;
            }
            initiative[szamlalo] = botegysegek[i].getInitiative() + bothos.getMoral();
            szamlalo++;
        }
        Arrays.sort(initiative);
        for (int i = initiative.length - 1; i >= (initiative.length - szamlalo); i--){
            rendezett[initiative.length - 1 - i] = initiative[i];
        }
        int i = 0;
        //Ha egyenlo a kezdemenyezes akkor a player fog hamarabb menni pont emiatt
        while(i < szamlalo){
            for (int j = 0; j < sajategysegek.length; j++){
                if(sajategysegek[j] == null){
                    continue;
                }
                if( (sajategysegek[j].getInitiative() + sajathos.getMoral()) == rendezett[i]){
                    initiativetomb[i][0] = rendezett[i];
                    initiativetomb[i][1] = j;
                    initiativetomb[i][2] = 1;
                    i++;
                }
            }
            for (int j = 0; j < botegysegek.length; j++){
                if(botegysegek[j] == null){
                    continue;
                }
                if( (botegysegek[j].getInitiative() + bothos.getMoral()) == rendezett[i]){
                    initiativetomb[i][0] = rendezett[i];
                    initiativetomb[i][1] = j;
                    initiativetomb[i][2] = 2;
                    i++;
                }
            }
        }

        return initiativetomb;
    }

    /**
     * Az a metodus ami meghivja a kulonbozo egysegek visszatamadasat csata kozben
     * @param egyseg az adott egyseg aki visszatamad
     * @param opponent az akire visszatamad
     * @param robot fontos a az egyseg tombok szempontjabol
     * @param vanmellette megnezi hogy az egyseg mellett van e valaki, ennek koszonheto hogy ranged attack ellen nem tamad vissza
     */
    public static void visszaTamadas(Egyseg egyseg, Egyseg opponent, boolean robot, boolean vanmellette){
        if(vanmellette){
            if(!robot){
                egyseg.counterAttack(opponent, sajathos, bothos);
            }
            else{
                egyseg.counterAttack(opponent, bothos, sajathos);
            }
        }
    }

    public static String lat(int ki, boolean robot){
        int[] hol;
        hol = holVan(ki , robot);
        for (int i = hol[0]; i < csatater.length; i++){
            if(!csatater[i][hol[1]].equals("-") && !csatater[i][hol[1]].contains("E")){
                return "le";
            }
        }
        for (int i = hol[0]; i >= 0; i--){
            if(!csatater[i][hol[1]].equals("-") && !csatater[i][hol[1]].contains("E")){
                return "fel";
            }
        }
        return "balra";
    }

    public static void tamadas(Egyseg[] tamadoegysegek, Egyseg opponent, Hos sajathos, Hos ellenfelhos, int i, int[][] initiativetomb, boolean robot, boolean vanmellette, boolean kivanmellette){
        if(tamadoegysegek[initiativetomb[i][1]] instanceof Elf){
            //Itt van megoldva a ketszer tamadas, mint kulonleges kepesseg
            if(vanmellette){
                if(!kivanmellette && !robot){
                    System.out.println("Vannak mellettem, ezert nemtudom ot megloni.");
                }
                else {
                    ((Elf) tamadoegysegek[initiativetomb[i][1]]).meleeAttack(opponent, sajathos, ellenfelhos);
                    ((Elf) tamadoegysegek[initiativetomb[i][1]]).meleeAttack(opponent, sajathos, ellenfelhos);
                }

            }
            else{
                ((Elf) tamadoegysegek[initiativetomb[i][1]]).rangedAttack(opponent, sajathos, ellenfelhos);
                ((Elf) tamadoegysegek[initiativetomb[i][1]]).rangedAttack(opponent, sajathos, ellenfelhos);
            }
        }
        if(tamadoegysegek[initiativetomb[i][1]] instanceof Foldmuves){
            if(kivanmellette){
                ((Foldmuves) tamadoegysegek[initiativetomb[i][1]]).meleeAttack(opponent, sajathos, ellenfelhos);
            }
            else{
                if(!robot){
                    System.out.println("Nincs mellettem ez az ellenfel, ezert nem tudtam tamadni.");
                }
            }
        }
        if(tamadoegysegek[initiativetomb[i][1]] instanceof Griff){
            if(kivanmellette){
                ((Griff) tamadoegysegek[initiativetomb[i][1]]).meleeAttack(opponent, sajathos, ellenfelhos);
            }
            else{
                if(!robot){
                    System.out.println("Nincs mellettem ez az ellenfel, ezert nem tudtam tamadni.");
                }
            }
        }
        if(tamadoegysegek[initiativetomb[i][1]] instanceof Ijasz){
            if(vanmellette){
                if(!kivanmellette && !robot){
                    System.out.println("Vannak mellettem, ezert nemtudom ot megloni.");
                }
                else {
                    ((Ijasz) tamadoegysegek[initiativetomb[i][1]]).meleeAttack(opponent, sajathos, ellenfelhos);
                }
            }
            else{
                ((Ijasz) tamadoegysegek[initiativetomb[i][1]]).rangedAttack(opponent, sajathos, ellenfelhos);
            }
        }
        if(tamadoegysegek[initiativetomb[i][1]] instanceof Troll){
            if(kivanmellette){
                ((Troll) tamadoegysegek[initiativetomb[i][1]]).meleeAttack(opponent, sajathos, ellenfelhos);
            }
            else{
                if(!robot){
                    System.out.println("Nincs mellettem ez az ellenfel, ezert nem tudtam tamadni.");
                }
            }
        }
    }

    public static void Varakozas(int melyik, boolean robot){
        if(robot){
            System.out.println("Ellenfel " + egysegnevek[melyik] + " varakozik!");
        }
        else {
            System.out.println("Szovetseges " + egysegnevek[melyik] + " varakozik!");
        }
    }

    public static boolean isNumeric(String string) {
        int intValue;

        if(string == null || string.equals("")) {
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Nem szamot adtal meg!");
        }
        return false;
    }

    public static void Csata(){
        boolean mindenkiel = true;
        boolean mindenkielbot = true;
        int[][] initiativetomb = initiativeSzamolo(); //Elso oszlop initial, masodik oszlop hanyadik index, harmadik oszlop 1 ha player, 2 ha bot
        int korok = 1;
        int hoscsinal = 1;
        int bothoscsinal = 1;
        int nullasajat;
        int nullabot;
        Egyseg opponent;
        System.out.println("Felkeszules vege, itt az ideje a csatanak!");
        System.out.println("Itt a koron beluli sorrend: ");
        for (int i = 0; i < initiativetomb.length; i++){
            if(initiativetomb[i][0] == 0){
                break;
            }
            if(initiativetomb[i][2] == 1){
                System.out.print("Sajat " + egysegnevek[initiativetomb[i][1]] + "\t");
            }
            else{
                System.out.print("Ellenfel " + egysegnevek[initiativetomb[i][1]] + "\t");
            }
        }
        System.out.println();
        while (mindenkiel && mindenkielbot){ //Korok
            System.out.println(korok + ". kor");
            Csatater(csatater);
            for(int i = 0; i < initiativetomb.length; i++){
                if(initiativetomb[i] == null){
                    continue;
                }
                if(initiativetomb[i][2] == 0){
                    continue;
                }
                //Player csinal
                if(initiativetomb[i][2] == 1){
                    boolean nemrobot = false;
                    System.out.println("Szovetseges " + egysegnevek[initiativetomb[i][1]] + " kovetkezik, vagy tudsz a hosoddel is tamadni vagy spellt kasztolni.");
                    System.out.println(egysegnevek[initiativetomb[i][1]]+"(1) Hos(2)");
                    command = console.next();
                    while(!isNumeric(command)){
                        command = console.next();
                    }
                    szamcommand = Integer.parseInt(command);
                    if(szamcommand == 2 && hoscsinal == 0){
                        System.out.println("A hosod mar csinalt valamit ebben a korben!");
                        szamcommand = 1;
                    }
                    if(szamcommand == 2){
                        hoscsinal = 0;
                        System.out.println("Tamadas(1) Spell(2)");
                        command = console.next();
                        while(!isNumeric(command)){
                            command = console.next();
                        }
                        szamcommand = Integer.parseInt(command);
                        if(szamcommand == 1){
                            kitTamad(botegysegek);
                            command = console.next();
                            for (int j = 0; j < egysegnevek.length; j++){
                                if(command.equals(egysegnevek[j])){
                                    opponent = botegysegek[j];
                                    sajathos.hosAttack(opponent);
                                    csataterFrissito();
                                    break;
                                }
                            }
                        }
                        else{
                            System.out.println("Ezeket a spelleket tudod hasznalni: ");
                            for (int j = 0; j < sajatspellek.length; j++){
                                if(sajatspellek[j] != null){
                                    System.out.print(spellnevek[j] + "\t");
                                }
                            }
                            System.out.println();
                            command = console.next();
                            for (int j = 0; j < spellnevek.length; j++){
                                if(sajatspellek[j] == null){
                                    continue;
                                }
                                if(command.equals(spellnevek[j])) {
                                    if(sajatspellek[j] instanceof Misfortune){
                                        ((Misfortune) sajatspellek[j]).unlucky(sajathos, bothos,nemrobot);
                                    }
                                    if(sajatspellek[j] instanceof Fireball){
                                        System.out.println("Hova szeretned loni(x,y)");
                                        System.out.println("Sor(A-J)");
                                        String sor = console.next();
                                        int x = 0;
                                        for (int k = 0; k < abc.length; k++){
                                            if(abc[k].equals(sor)){
                                                x = k + 1;
                                                break;
                                            }
                                        }
                                        System.out.println("Oszlop(1-12)");
                                        command = console.next();
                                        while(!isNumeric(command)){
                                            command = console.next();
                                        }
                                        szamcommand = Integer.parseInt(command);
                                        int y = szamcommand;
                                        while (y > 12){
                                            System.out.println("Ez kint lenne a jatekterbol, adj megy egy 12-nel kisebb szamot.");
                                            y = console.nextInt();
                                        }

                                        ((Fireball) sajatspellek[j]).areadmg(csatater, x-1, y-1, sajategysegek, botegysegek, sajathos, egysegnevek, nemrobot);
                                        csataterFrissito();
                                    }
                                    if(sajatspellek[j] instanceof Lightning){
                                        kitTamad(botegysegek);
                                        command = console.next();
                                        for (int k = 0; k < egysegnevek.length; k++){
                                            if(command.equals(egysegnevek[k])){
                                                opponent = botegysegek[k];
                                                ((Lightning) sajatspellek[j]).lightningAttack(opponent,sajathos, nemrobot);
                                                csataterFrissito();
                                                break;
                                            }
                                        }
                                    }
                                    if(sajatspellek[j] instanceof Revive){
                                        kitReviveol(sajategysegek);
                                        command = console.next();
                                        for (int k = 0; k < egysegnevek.length;k++){
                                            if(egysegnevek[k].equals(command)){
                                                ((Revive) sajatspellek[j]).cast(sajategysegek[k],sajathos, sajatmaxamounts,k, nemrobot);
                                                csataterFrissito();
                                                break;
                                            }
                                        }
                                    }
                                    if(sajatspellek[j] instanceof Teleport){
                                        boolean teleportsiker = true;
                                        kitReviveol(sajategysegek);
                                        command = console.next();
                                        int unit = 0;
                                        for (int k = 0; k < egysegnevek.length; k++){
                                            if(egysegnevek[k].equals(command)){
                                                unit = k;
                                            }
                                        }
                                        int[] hol;
                                        hol = holVan(unit, nemrobot);
                                        System.out.println("Hova szeretnél teleporalni? (x,y)");
                                        System.out.println("Sor(A-J)");
                                        String sor = console.next();
                                        int x = 0;
                                        for (int k = 0; k < abc.length; k++){
                                            if(abc[k].equals(sor)){
                                                x = k + 1;
                                                break;
                                            }
                                        }
                                        System.out.println("Oszlop(1-12)");
                                        command = console.next();
                                        while(!isNumeric(command)){
                                            command = console.next();
                                        }
                                        szamcommand = Integer.parseInt(command);
                                        int y = szamcommand;
                                        while (y > 12){
                                            System.out.println("Ez kint lenne a jatekterbol, adj megy egy 12-nel kisebb szamot.");
                                            command = console.next();
                                            while(!isNumeric(command)){
                                                command = console.next();
                                            }
                                            szamcommand = Integer.parseInt(command);
                                            y = szamcommand - 1;
                                        }
                                        teleportsiker = ((Teleport) sajatspellek[j]).tp(csatater, egysegnevek[unit], x-1, y-1, sajategysegek[unit], sajathos, nemrobot);
                                        if(teleportsiker){
                                            csatater[hol[0]][hol[1]] = "-";
                                        }
                                        csataterFrissito();
                                    }
                                }
                            }
                        }
                        szamcommand = 1;
                    }
                    if(szamcommand == 1){
                        System.out.println(egysegnevek[initiativetomb[i][1]] + " tud, Tamadni(1), Mozogni(2), Varakozni(3)");
                        command = console.next();
                        while(!isNumeric(command)){
                            command = console.next();
                        }
                        szamcommand = Integer.parseInt(command);
                        if(szamcommand == 1){
                            int[] hol = holVan(initiativetomb[i][1],nemrobot);
                            boolean vanmellette;
                            boolean kivanmellette;
                            kitTamad(botegysegek);
                            command = console.next();
                            for (int j = 0; j < egysegnevek.length; j++){
                                if(botegysegek[j] == null){
                                    continue;
                                }
                                if(sajategysegek[initiativetomb[i][1]] == null){
                                    break;
                                }
                                if(command.equals(egysegnevek[j])){
                                    opponent = botegysegek[j];
                                    vanmellette = sajategysegek[initiativetomb[i][1]].vanMellette(csatater,hol[0],hol[1],nemrobot);
                                    kivanmellette = sajategysegek[initiativetomb[i][1]].kivanMellette(csatater,hol[0],hol[1],nemrobot,egysegnevek,j);
                                    tamadas(sajategysegek, opponent, sajathos, bothos, i, initiativetomb, nemrobot, vanmellette, kivanmellette);
                                    hol = holVan(j,true);
                                    kivanmellette = opponent.kivanMellette(csatater,hol[0],hol[1],true,egysegnevek,initiativetomb[i][1]);
                                    if(!botegysegek[j].isCounterattacked()){ //Itt van ellenorizve hogy counterelt-e mar
                                        visszaTamadas(opponent, sajategysegek[initiativetomb[i][1]], nemrobot, kivanmellette);
                                        if(botegysegek[j] instanceof Griff){ //Itt a griff vegtelen visszatamadas
                                            botegysegek[j].setCounterattacked(false);
                                        }
                                        else {
                                            botegysegek[j].setCounterattacked(true);
                                        }
                                    }
                                }
                            }
                        }
                        if(szamcommand == 2){
                            System.out.println("Merre szeretnél mozogni? (jobbra, balra, fel, le) Atlos mozgashoz csak ird a ket szot egymas utan vesszovel elvalasztva pl: (balra,fel).");
                            command = console.next();
                            String irany = command;
                            System.out.println("Mennyit szeretnél mozgatni? (ha tul nagy szamot irsz akkor a maximum sebessegevel fog menni)");
                            command = console.next();
                            while(!isNumeric(command)){
                                command = console.next();
                            }
                            int mennyi = Integer.parseInt(command);
                            int[] hol;
                            hol = holVan(initiativetomb[i][1], nemrobot);
                            csatater = sajategysegek[initiativetomb[i][1]].movement(mennyi,csatater,irany, hol[0],hol[1],egysegnevek[initiativetomb[i][1]], nemrobot);
                        }
                        if(szamcommand == 3){
                            Varakozas(initiativetomb[i][1],nemrobot);
                        }
                    }

                }
                //Bot csinal
                else{
                    System.out.println("Most az Ellenseges " + egysegnevek[initiativetomb[i][1]] + " kovetkezik, de tudja a hoset is hasznalni az ellenfel.");
                    boolean robot = true;
                    int donto = random.nextInt(0,101);
                    //Minden korben eloszor a hossel tamad vagy spellezik
                    if(bothoscsinal != 0){
                        bothoscsinal = 0;
                        if(donto < 71){
                            System.out.println("Ellenfel hose tamad");
                            //Ekkor tamad
                            for (int x = 0; x < sajategysegek.length; x++){
                                if(sajategysegek[x] != null){
                                    bothos.hosAttack(sajategysegek[x]);
                                    csataterFrissito();
                                    break;
                                }
                            }
                        }
                        else{
                            int mennyispell = 0;
                            int hanyadik = 1;
                            for (int j = 0; j < botspellek.length;j++){
                                if(botspellek[j] != null){
                                    mennyispell++;
                                }
                            }
                            if(mennyispell != 0) {
                                System.out.println("Ellenfel hose varazslatot hasznal");
                                donto = random.nextInt(0,5);
                                while(botspellek[donto] == null){
                                    donto = random.nextInt(0,5);
                                }
                                hanyadik = donto;
                                if (botspellek[hanyadik] instanceof Misfortune) {
                                    ((Misfortune) botspellek[hanyadik]).unlucky(bothos, sajathos, robot);
                                    System.out.println("Szerencsetlenseg");
                                }
                                if (botspellek[hanyadik] instanceof Fireball) {
                                    int x = 0;
                                    int y = 0;
                                    for (int g = 0; g < csatater.length; g++){
                                        for (int k = 0; k < csatater[g].length; k++){
                                            if(!csatater[g][k].equals("-") && !csatater[g][k].contains("E")){
                                                x = g;
                                                y = k;
                                                break;
                                            }
                                        }
                                    }
                                    System.out.println("Tuzlabda");
                                    ((Fireball) botspellek[hanyadik]).areadmg(csatater, x - 1, y - 1, sajategysegek, botegysegek, sajathos, egysegnevek, robot);
                                }
                                if (botspellek[hanyadik] instanceof Lightning) {
                                    for (int k = 0; k < sajategysegek.length; k++) {
                                        if (sajategysegek[k] != null) {
                                            opponent = sajategysegek[k];
                                            ((Lightning) botspellek[hanyadik]).lightningAttack(opponent, bothos, robot);
                                            break;
                                        }
                                    }
                                    System.out.println("Villamcsapas");
                                }
                                if (botspellek[hanyadik] instanceof Revive) {
                                    for (int k = 0; k < botegysegek.length; k++) {
                                        if(botegysegek[k] == null){
                                            continue;
                                        }
                                        if (botegysegek[k].getAmount() != sajatmaxamounts[k]) {
                                            ((Revive) botspellek[hanyadik]).cast(botegysegek[k], bothos, sajatmaxamounts, k, robot);
                                            csataterFrissito();
                                            break;
                                        }
                                    }
                                    System.out.println("Feltamasztas");
                                }
                                if (botspellek[hanyadik] instanceof Teleport) { //Pozicionalas szempontjabol ha az ellenfel utolso elotti sorara teleportal
                                    boolean teleportsiker;                      //Atlagosan az a leghasznosabb
                                    int unit = 0;
                                    for (int x = 0; x < botegysegek.length; x++){
                                        if(botegysegek[x] != null){
                                            unit = x;
                                        }
                                    }
                                    int[] hol;
                                    hol = holVan(unit, robot);
                                    int x = random.nextInt(0,10);
                                    teleportsiker = ((Teleport) botspellek[hanyadik]).tp(csatater, egysegnevek[unit], x, 1, sajategysegek[unit], sajathos, robot);
                                    if (teleportsiker) {
                                        csatater[hol[0]][hol[1]] = "-";
                                    }
                                    System.out.println("Teleportalas");
                                }
                            }
                        }
                    }
                    //Most valaszthat 3 hogy tamad mozog vagy varakozik
                    donto = random.nextInt(1,101);
                    int[] hol;
                    hol = holVan(initiativetomb[i][1], robot);
                    boolean vanmellette = botegysegek[initiativetomb[i][1]].vanMellette(csatater,hol[0],hol[1],robot);
                    boolean kivanmellette;
                    if(donto < 11){
                        Varakozas(initiativetomb[i][1],robot);
                    }
                    else if(!vanmellette && botegysegek[initiativetomb[i][1]] instanceof Elf){
                        for (int j = 0; j < sajategysegek.length; j++){
                            if(sajategysegek[j] != null){
                                kivanmellette = botegysegek[initiativetomb[i][1]].kivanMellette(csatater,hol[0],hol[1],robot,egysegnevek,j);
                                tamadas(botegysegek, sajategysegek[j], bothos, sajathos, i, initiativetomb, robot, vanmellette, kivanmellette);
                                break;
                                //Nem kell countert meghivni mert ha tud ranged attackolni akkor senki akit tamad az sincs mellette
                            }
                        }
                    }
                    else if(!vanmellette && botegysegek[initiativetomb[i][1]] instanceof Ijasz){
                        for (int j = 0; j < sajategysegek.length; j++){
                            if(sajategysegek[j] != null){
                                kivanmellette = botegysegek[initiativetomb[i][1]].kivanMellette(csatater,hol[0],hol[1],robot,egysegnevek,j);
                                tamadas(botegysegek, sajategysegek[j], bothos, sajathos, i, initiativetomb, robot, vanmellette, kivanmellette);
                                break;
                                //Nem kell countert meghivni mert ha tud ranged attackolni akkor senki akit tamad az sincs mellette
                            }
                        }
                    }
                    else if(!vanmellette){
                        int mennyi = random.nextInt(1,3);
                        String irany = lat(initiativetomb[i][1],robot);
                        csatater = botegysegek[initiativetomb[i][1]].movement(mennyi,csatater,irany,hol[0],hol[1],egysegnevek[initiativetomb[i][1]],robot);
                    }
                    else{
                        //Ha van mellette ellenfel akkor mindenkepp attackolni fog
                        for (int x = 0; x < egysegnevek.length; x++){
                            if(botegysegek[initiativetomb[i][1]].kivanMellette(csatater,hol[0],hol[1],robot,egysegnevek,x)){
                                if(sajategysegek[x] == null){
                                    break;
                                }
                                opponent = sajategysegek[x];
                                tamadas(botegysegek,opponent,bothos,sajathos,i,initiativetomb,robot,true,true); //True fixen a feltétel miatt
                                hol = holVan(x, false);
                                vanmellette = opponent.vanMellette(csatater,hol[0],hol[1],false);
                                if(!sajategysegek[x].isCounterattacked()){
                                    visszaTamadas(opponent, botegysegek[initiativetomb[i][1]],false, vanmellette);
                                    if(sajategysegek[x] instanceof Griff){ //Itt is a griff vegtelen visszatamadas
                                        sajategysegek[x].setCounterattacked(false);
                                    }
                                    else {
                                        sajategysegek[x].setCounterattacked(true);
                                    }
                                }
                            }
                        }
                    }
                }
                csataterFrissito();
                nullasajat = nullAmount(sajategysegek);
                nullabot = nullAmount(botegysegek);
                if(nullasajat != -1){ //0 amountos egység már úgyse ér semmit uh ne is menjen rajta a for ciklus
                    for (int x = 0; x < initiativetomb.length; x++){
                        if(initiativetomb[x] == null){
                            continue;
                        }
                        if(initiativetomb[x][1] == nullasajat && initiativetomb[x][2] == 1){
                            int[] hol;
                            hol = holVan(initiativetomb[x][1], false);
                            csatater[hol[0]][hol[1]] = "-";
                            sajategysegek[initiativetomb[x][1]] = null;
                            initiativetomb[x] = null;
                        }
                    }
                }
                if(nullabot != -1){
                    for (int x = 0; x < initiativetomb.length; x++){
                        if(initiativetomb[x] == null){
                            continue;
                        }
                        if(initiativetomb[x][1] == nullabot && initiativetomb[x][2] == 2){
                            int[] hol;
                            hol = holVan(initiativetomb[x][1], true);
                            csatater[hol[0]][hol[1]] = "-";
                            botegysegek[initiativetomb[x][1]] = null;
                            initiativetomb[x] = null;
                        }
                    }
                }
            }
            //Troll regeneralas itt van meghivva
            //Counterattack visszaallito
            for (int j = 0; j < sajategysegek.length; j++){
                if(sajategysegek[j] != null){
                    sajategysegek[j].setCounterattacked(false);
                    if(sajategysegek[j] instanceof Troll){
                        ((Troll) sajategysegek[j]).regen();
                    }
                }
            }
            for (int j = 0; j < botegysegek.length; j++){
                if(botegysegek[j] != null){
                    botegysegek[j].setCounterattacked(false);
                    if(botegysegek[j] instanceof Troll){
                        ((Troll) botegysegek[j]).regen();
                    }
                }
            }
            //Vegso ellenorzes hogy megy e meg a game
            hoscsinal = 1;
            bothoscsinal = 1;
            mindenkiel = mindenkiEl(sajategysegek);
            mindenkielbot = mindenkiEl(botegysegek);
            korok++;
        }
        if(!mindenkiel){
            System.out.println("Jo csata volt, de sajnos vereseget szenvedtel!");
        }
        else{
            System.out.println("Winner Winner Chicken Dinner!!4!44");
        }
    }

    public static void main(String[] args) {
        try {
            bot.setArany(1000);
            boolean nincsvege = false;
            Title();
            Fomenu();
            Vasarlas();
            aiFelkeszul();
            nincsvege = Felkeszules();
            if (nincsvege) {
                Csata();
            }
        } catch (ArithmeticException e) {
            System.out.println("Aritmetikai exception: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("NullPointer exception: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBounds exception: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Egyeb hiba!");
        }
        System.out.println("A jatek vegere ertel, remelem elvezted!");
    }
}
