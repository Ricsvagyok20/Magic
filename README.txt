Üdvözöllek kedves játékos, ezt mindenképp olvasd végig, mielőtt először játszanál.

Először is a játék futtatásához, le kell tölteni az Intellij Idea legfrissebb community verzióját.
Megnyitni az Intellijt és megnyitni az open funkcióval a már kicsomagolt játék mappáját.
A futtatható main a Main package Game.java osztályában található.
Megvárni, hogy mindent betöltsön a program és a main-t futtatva indulhat a játék.
A játék konzolosan van megvalósítva, ami azt jelenti, hogy szöveges inputot vár, és minden felhasználói
interakció esetén, le van írva mit kell tenni.

A legtöbb esetben, hogy ne kelljen annyit gépelni ott lesz az opció mellett zárójelek között a
száma és ezt kell inputként beírni, amennyiben nincs szám a zárójelben (ez általában rövidebb szöveg
inputoknál van kivéve a varázslatok használatát) akkor a kiírt lehetőségeg közül kell választani és azt
inputként beírni.
Példa: a egység vásárláskor Elf(1) Foldmuves(2) Griff(3), ekkor ha griffet szeretnél vásárolni, beírod a
3-ast, majd nyomsz egy Entert.

Fontos még, hogyha egyszer már vásároltál egységet, akkor abból többet már nem tudsz, és felkészüléskor
csak egyszer tudod elhelyezni az adott egységet onnantól az fixen ott van.

Főmenü:
A játék indításakor a főmenüben találod magad, ahol ki kell választani a nehézségi szintet.
Könnyű: 1300 arany
Közepes: 1000 arany
Nehéz: 700 arany
A gépi ellenfeled minden esetben 1000 arannyal kezd, ezzel az arannyal tudsz majd vásárolni.

Vásárlás:
Hős fejlesztés:
Belépsz a hos szóval és utána 1-6ig ki kell választani, hogy melyik tulajdonságot és mennyivel szeretnéd
fejleszteni.
Varázslat vásárlás:
Belépsz a spell szóval és utána le lesz írva a képesség neve, ára és manna költsége a különleges
varázslatok lentebb láthatóak.
Egység vásárlás:
Belépsz egyseg szóval és utána hasonlóan az előző kettőhöz ott van zarójelben a szám és amelyik egységet
szeretnéd megvásárolni annak a számát írod be, majd utána el tudod dönteni, hogy mennyit szeretnél az
adott egységből venni.

Felkészülés:
Miután végeztél a vásárlással ahonnan vagy az összes arany elköltésével vagy a stop szó beírásával
tudsz továbblépni jön a felkészülési fázis erről tájékoztat is a program, itt ki lesznek írva az ellenséges
hős tulajdonságpontjai, varazslatai, majd megjelenik a csatatér ahol az egységek is látszanak, fontos
ahol E betűvel végzödik egy egység ott ellenséges egység található. Itt tudod majd a saját egységeidet is
elhelyezni, a csatatér első két sorába a játék minden esetben kérdezni fogja, hogy melyik sorba (A-J)
és melyik oszlopba szeretnéd helyezni, pl. Melyik sorba? (A-J) B Melyik oszlopba(1-2) 2, ezután az
egység meg fog jelenni a B2-es helyen. Miután elhelyeztél mindenkit vagy a stop szó beírásával
tudsz továbblépni a csatába. Fontos ha nem vettél egységet, akkor azonnal veszíteni fogsz.

Csata:
A csata körökre van osztva, minden körben ki lesz írva az egységek sorrendje, ha a te egységed van soron
akkor az egységed tud támadni, mozogni vagy várakozni, de minden körben egyszer amikor te vagy soron
tudsz a hősöddel is támadni, vagy varázslatot használni.
A varázslat használathoz végig kell írni az adott varazslat nevet olyan formában ahogy ki lesz 
írva használat előtt.
Az egyseg mozgása úgy van megvalósítva, hogy először irányt kér, ekkor átlós mozgáshoz vesszővel
egymás után kell írni de csak az irányt pl: balra,fel. Majd ezután fogja kérni, hogy mennyit mozognál.
A játék addig megy, amíg vagy legyőzöd az ellenfél összes egységét vagy a te egységeid fogynak el.

Egységek
Elf: Egy távolsági támadással rendelkező, kifejezetten erős egység, ára 40 arany, és a különleges
képessége, hogy kétszer támad.
Troll: Egy közelharci tank, nagy életerővel és sebzéssel rendelkezik, cserébe lassab és drágább,
ára 35 arany, különleges képessége, hogy minden kör végén maximum életerőre regenerál
Varázslatok
Szerencsétlenség: Ellenfél hősének szerencse pontjait csökkenti 3-al
Teleportálás: Egy kívánt, nem foglalt pozícióra teleportálja az egységet
Árak és manna költségek ki van írva vásárláskor.

