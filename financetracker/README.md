# Finance Tracker - Banki Hiteltermék Kezelő és Elemző API

Ez egy **Spring Boot** alapú backend REST API projekt, amely a vállalati banki hiteltermékek (pl. Széchenyi Kártya programok, Garantiqa kezességgel biztosított hitelek) strukturált kezelését, nyilvántartását és kockázati szűrését valósítja meg.

A projekt elsődleges célja a **10 éves corporate banki, KYC és Compliance iparági tapasztalatom (Domain Knowledge)** ötvözése a modern **szoftverfejlesztői és adatbázis-kezelési** ismereteimmel.

## 🚀 Főbb Funkciók és Üzleti Logika
- **Strukturált Hiteltermék Modell:** Pénzügyi entitások kezelése (hitel típusa, maximális hitelösszeg, hitel neve, utolsó módosítás dátuma).
- **Kockázati és Termékalapú Szűrés:** REST végponton keresztüli dinamikus adatmanipuláció és szűrés.
- **Automatizált Adat-inicializálás:** A rendszer induláskor automatikusan ellenőrzi a perzisztens réteget, és szükség esetén feltölti a piacvezető hazai államilag támogatott hitelkonstrukciók adataival.

## 🛠️ Alkalmazott Technológiák (Tech Stack)
- **Backend:** Java 21, Spring Boot 3.x
- **Adatkezelés & Perzisztencia:** Spring Data JPA, Hibernate ORM
- **Adatbázis:** **MySQL** (Relációs, perzisztens vállalati adatbázis-integráció a korábbi in-memory H2 helyett)
- **Minőségbiztosítás / Tesztelés:** JUnit 5, Mockito (Mock MVC architektúra tesztelés a Controller rétegben)
- **Projektmenedzsment & Verziókövetés:** Maven, Git / GitHub

## 📐 Architektúra és Tiszta Kód (Clean Code)
A projekt tervezése során kiemelt figyelmet fordítottam a rétegzett architektúra és a **SOLID elvek** követésére:
- **Controller Réteg:** Fogadja a kliensoldali HTTP kéréseket és kezeli a REST API végpontokat.
- **Repository Réteg:** Interfész-alapú adatbázis-kapcsolat a Spring Data JPA segítségével.
- **Automated Testing:** Magas tesztlefedettség a Controller rétegen, Mockito-val izolált adatbázis-szimulációval (Mocking), követve a *Given-When-Then* struktúrát.
- **Biztonság (Security Best Practices):** Az adatbázis-jelszavak és érzékeny adatok kiszervezésre kerültek a forráskódból környezeti változókba (`Environment Variables`), megakadályozva a kritikus adatok repozitóriumba kerülését.

## 💻 Lokális indítás és futtatás
1. Klónozd a repozitóriumot: `git clone https://github.com`
2. Hozz létre egy lokális MySQL sémát `financetracker` néven.
3. Állítsd be a `DB_PASSWORD` környezeti változót a saját MySQL jelszavadra.
4. Indítsd el az alkalmazást: `./mvnw spring-boot:run`