# Alkalmazasfejlesztesi kornyezetek hazi feladat

## A feladat rovid leirasa

A felev soran egy webshop rendszert fogunk kesziteni, ahol fejlesztok a sajat szoftvereiket tudjak ertekesiteni.
A projekt harom fo komponensbol all:

- Kliens oldal
- Authorizacios szerver
- Resource szerver

A rendszerbe lehet regisztralni, szoftvereket eladni, illetve mas szoftvereit megvasarolni. Jelszoemlekezteto email kuldeset a rendszer tamogatja, illetve bizonyos idokozonkent az inaktiv felhasznaloknak emlekezteto emailt kuld.

A rendszer indulaskor ontesztelest vegez. A tesztesetek a rendszer nagy szazalekat lefedik.

### A ket backend szerver

A szerverek kozotti kommunikaciot REST API-n keresztul valositjuk meg.
Kezdetben ket felhasznaloi szint letezik, adminisztrator es sima user. Elobbi mindent lat es modosithat (hirdetesek, userek), utobbi pedig csak a publikus oldalakat eri el, illetve a sajat maga altal felrakott hirdeteseket szerkesztheti.
Az autentikaciot es autorizaciot tokenek segitsegevel valositjuk meg. A tokenek kezelesehez az oran is bemutatott Oauth2 Bearer token flow-t hasznaljuk, aszinkron uzenetsorokkal.

A jelszavakat biztonsagosan taroljuk, illetve egyeb intezkedeseket is teszunk a sebezhetosegek elkerulese erdekeben.

Minden fontosabb esemeny logolasra kerul (bejelentkezes, kijelentkezes, hirdetes inditasa, vasarlas, regisztracio es az ezek kozben felmerulo exception-ok es error-ok), fontossagtol fuggoen kulonbozo loglevel-lel.

Az adatbazisok hasznalatat Spring Data JPA-val valositjuk meg (ha jol ertelmeztem a feladatkiirast, akkor ez a Spring Data es JPA alap feladatokat teljesiti).
Entitasok:

- Entity - ez tartalmazza az altalanos attributumokat es ebbol oroklodik minden mas
- Software - egy megveheto, vagy eladott termek a webshop-ban
- Person - ebbol szarmaznak a tovabbi user tipusok
  - Admin
  - BasicUser
- Cart - ideiglenesen tarolja a felhasznalo altal megvenni kivant termekeket
- Category - kategoriak a szoftvereknek
- Purchase - egy eladasrol keletkezett bejegyzes

Ha egy felhasznalo torlodik, akkor minden hozza kapcsolodo adat is torlodik.

#### Authorizacios szerver

Csak a userek azonositashoz szukseges adatait kezeli. Tokeneket general, frissit, von vissza, illetve validal a resource szerver szamara.

#### Resource szerver

A webshop tenyleges funkcionalitasa itt valosul meg.

### Kliens oldal

Az oran bemutatott peldahoz hasonloan, React-et hasznalva keszitjuk el.
