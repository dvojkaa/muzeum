# MuzeumSys

Webová aplikace pro správu uměleckých děl v muzeích, vytvořená jako bakalářská práce. Aplikace umožňuje správu děl, místností, QR kódů a přístupy pro administrátory a zaměstnance.

## 🛠 Backend

### 1. Vytvoření databáze

Vytvoř si databázi PostgreSQL např. s názvem `muzeum` a nastav přístupové údaje v `application.properties` nebo pomocí environmentálních proměnných:

```ini
spring.datasource.url=jdbc:postgresql://localhost:5432/muzeum
spring.datasource.username=postgres
spring.datasource.password=heslo
```

### 2. Spuštění backendu

```bash
./mvnw spring-boot:run
```

## 🎨 Frontend

### 1. Přechod do složky

```bash
cd frontend
```

### 2. Instalace závislostí

```bash
npm install
```

### 3. Spuštění vývojového serveru

```bash
npm run dev
```

Frontend běží typicky na `http://localhost:5173` a komunikuje s backendem (např. `http://localhost:8080`).

## 🧪 Testování

Projekt obsahuje jednotkové a integrační testy pomocí JUnit a MockMvc. Testy ověřují funkčnost REST API, včetně autentizace pomocí získaného JWT tokenu.

### Spuštění testů

```bash
./mvnw test
```

## 👥 Uživatelské role a přístupy

| Role     | Účet                 | Heslo | Popis                                     |
|----------|----------------------|-------|--------------------------------------------|
| ADMIN    | admin@muzeum.cz      | admin | Má přístup do celé administrace            |
| EMPLOYEE | employee@muzeum.cz   | admin | Přístup k mobilní správě pomocí QR         |

## 🌍 Deployment

| Část systému | Platforma | URL                                         |
|--------------|-----------|---------------------------------------------|
| Backend      | Railway   | https://muzeum-production.up.railway.app   |
| Frontend     | Vercel    | https://muzeum-sigma.vercel.app            |

## 📸 Ukázky rozhraní

Ukázky rozhraní se nacházejí ve složce `screenshots`.

- Admin panel
- Mobilní rozhraní

## 📂 Struktura projektu

```
MuzeumSys/
├── frontend/              # React aplikace
├── src/                   # Backendový kód
│   ├── config/            # Bezpečnost, JWT, konfigurace
│   ├── controller/        # REST controllery
│   ├── dto/               # DTO objekty
│   ├── entity/            # JPA entity
│   ├── repository/        # Spring Data JPA repozitáře
│   └── service/           # Logika aplikace
├── tests/                 # JUnit integrační testy
└── README.md              # Tento soubor
```

## 💬 REST API ukázka

### Login

```http
POST /user/login
Content-Type: application/json

{
  "email": "admin@muzeum.cz",
  "password": "admin"
}
```

### Response:

```json
{
  "role": "ROLE_ADMIN",
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

## 📜 Licence

Tento projekt byl vytvořen jako bakalářská práce na Fakultě elektrotechnické ČVUT v Praze. Projekt je dostupný pro studijní a výzkumné účely.

## ✍️ Autor

**Bc. Vojtěch Kratina**  
Fakulta elektrotechnická, ČVUT v Praze  
Bakalářská práce, 2024/2025  
Vedoucí: Ing. Lukáš Zoubek
