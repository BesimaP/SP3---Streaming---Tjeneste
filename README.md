# 🎬 BLLKstream

> Tekstbaseret streaming tjeneste bygget i Java — 1. semester, SP3

BLLKstream er et konsolbaseret streaming system hvor brugere kan oprette en profil, logge ind, søge i et katalog af film og serier, afspille medier og gemme favoritter. Projektet er udviklet som en del af SP3-opgaven på 1. semester.

---

## Gruppemedlemmer

| Navn | GitHub |
|------|--------|
| Besima | |
| Louise | |
| Kristina | |
| Lucas | |

---

## Funktioner

- **Opret bruger & login** — med validering og maks 3 forsøg
- **Søg på titel eller kategori** — find film og serier hurtigt
- **Afspil medier** — marker som set og tilføj til sete medier
- **Gem favoritter** — tilføj og fjern medier fra din gemmede liste
- **Se dine lister** — overblik over sete og gemte medier
- **Datapersistens** — brugere og lister gemmes i CSV-filer
- **JavaFX katalog** ⭐ — grafisk vindue med filmplakater og serieforsider

---

## Teknologi

- Java 17+
- JavaFX (ekstra feature — grafisk katalog)
- CSV-filer til datapersistens
- Git til versionsstyring

---

## Sådan kører du programmet

### Krav
- Java 17 eller nyere installeret
- JavaFX SDK (kun nødvendigt for katalog-funktionen)

### Kør programmet

```bash
# Kompiler
javac -cp . *.java

# Kør
java Main
```

### Kør med JavaFX katalog

```bash
java --module-path /sti/til/javafx-sdk/lib \
     --add-modules javafx.controls,javafx.fxml \
     --enable-native-access=javafx.graphics \
     Main
```

---

## Projektstruktur

```
BLLKstream/
├── Main.java               # Indgangspunkt
├── StreamingService.java   # Datalaget — loader og gemmer data
├── Menu.java               # Menuer og navigation
├── UserController.java     # UC2: Opret bruger / UC3: Login
├── MediaController.java    # UC4: Søg & afspil / UC5: Administrer lister
├── CatalogView.java        # JavaFX grafisk katalog (ekstra feature)
├── Media.java              # Abstrakt klasse for film og serier
├── Movie.java              # Film
├── Series.java             # Serier
├── User.java               # Bruger
└── data/
    ├── film.csv            # 100 film
    ├── serier.csv          # 100 serier
    ├── users.csv           # Brugere
    ├── filmplakater/       # Billedfiler til film
    └── serieforsider/      # Billedfiler til serier
```

---

## Use Cases

| UC | Navn | Beskrivelse |
|----|------|-------------|
| UC1 | Systemstart | Indlæs data og vis startmenu |
| UC2 | Opret bruger | Registrer ny bruger med brugernavn og adgangskode |
| UC3 | Login | Log ind med eksisterende bruger |
| UC4 | Søg og afspil | Søg på titel/kategori, afspil eller gem medie |
| UC5 | Administrer lister | Se og administrer sete og gemte medier |

---

## Arkitektur

Projektet følger en lagdelt MVC-inspireret arkitektur:

```
Menu  ──────────────────────────────┐
  │                                 │
  ├── UserController                │
  │     registerUser()              │
  │     loginUser()                 │
  │                                 ▼
  └── MediaController          StreamingService
        searchTitle()          (datalaget)
        searchCategory()
        playMedia()
        manageMedia()
```

---

## Samarbejde

- Klassediagram og sekvensdiagrammer som fælles reference
- Kanban board til opgavestyring
- Git med hyppige, beskrivende commits
- Beskrivende kommentarer i koden for fælles forståelse
- Pair programming til de svære dele