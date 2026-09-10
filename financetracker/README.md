# 🏦 Corporate Loan Product Catalog API - Spring Boot Practice

Ez a projekt a 13 hetes Java Backend fejlesztői képzésem során szerzett tudásomat reprezentálja, kifejezetten a 10 éves vállalati banki és értékesítéstámogatói múltamhoz igazítva. A szoftver egy nyilvános banki hiteltermék-katalógus háttérrendszere (REST API).

## 🚀 Alkalmazott technológiák és koncepciók (Tech Stack):
- **Spring Boot 3 & REST Architecture:** Szabványos HTTP végpontok (GET, POST) kezelése az adatközléshez.
- **Spring Data JPA & Perzisztencia:** Relációs adatbázis-management beágyazott H2 SQL adatbázis segítségével.
- **Java Stream API (12. hét):** Adatok hatékony, funkcionális szűrése a háttérben a maximálisan igényelhető hitelösszeg (limit) alapján.
- **Clean Code & OOP:** Gyári vállalati szabvány szerint elkülönített Model-Repository-Controller rétegelt architektúra.

---

## 📌 Hogyan működik a projekt?
A háttérprogram elindulásakor automatikusan feltölti a beágyazott adatbázist a piacon elérhető legnépszerűbb vállalati hiteltermékekkel (pl. Széchenyi Kártya program, Garantiqa és MFB konstrukciók). A `/api/loans` végponton keresztül az összes termék lekérdezhető, míg a `/api/loans/szures?limit=[összeg]` végponton a Java Stream API segítségével a szoftver azonnal átválogatja a hiteleket a megadott finanszírozási korlát alapján.
