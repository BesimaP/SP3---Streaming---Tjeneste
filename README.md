# 🎬 BLLKstream

> Text-based streaming service built in Java — 1st semester, SP3

BLLKstream is a console-based streaming system where users can create a profile, log in, search a catalog of movies and series, play media, and save favourites. The project was developed as part of the SP3 assignment in the 1st semester.

---

## Group Members

| Name | GitHub |
|------|--------|
| Besima | |
| Louise | |
| Kristina | |
| Lucas | |

---

## Features

- **Create user & login** — with validation and max 3 attempts
- **Search by title or category** — find movies and series quickly
- **Play media** — mark as watched and add to watched list
- **Save favourites** — add and remove media from your saved list
- **View your lists** — overview of watched and saved media
- **Data persistence** — users and lists are saved in CSV files
- **JavaFX catalog** ⭐ — graphical window with movie posters and series covers

---

## Technology

- Java 17+
- JavaFX (extra feature — graphical catalog)
- CSV files for data persistence
- Git for version control

---

## How to Run

### Requirements
- Java 17 or newer installed
- JavaFX SDK (only required for the catalog feature)

### Run the program

```bash
# Compile
javac -cp . *.java

# Run
java Main
```

### Run with JavaFX catalog

```bash
java --module-path /path/to/javafx-sdk/lib \
     --add-modules javafx.controls,javafx.fxml \
     --enable-native-access=javafx.graphics \
     Main
```

---

## Project Structure

```
BLLKstream/
├── Main.java               # Entry point
├── StreamingService.java   # Data layer — loads and saves data
├── Menu.java               # Menus and navigation
├── UserController.java     # UC2: Create user / UC3: Login
├── MediaController.java    # UC4: Search & play / UC5: Manage lists
├── CatalogView.java        # JavaFX graphical catalog (extra feature)
├── Media.java              # Abstract class for movies and series
├── Movie.java              # Movie
├── Series.java             # Series
├── User.java               # User
└── data/
    ├── movies.csv          # 100 movies
    ├── series.csv          # 100 series
    ├── users.csv           # Users
    ├── filmplakater/       # Movie poster images
    └── serieforsider/      # Series cover images
```

---

## Use Cases

| UC | Name | Description |
|----|------|-------------|
| UC1 | System Start | Load data and show start menu |
| UC2 | Create User | Register a new user with username and password |
| UC3 | Login | Log in with an existing user |
| UC4 | Search & Play | Search by title/category, play or save media |
| UC5 | Manage Lists | View and manage watched and saved media |

---

## Architecture

The project follows a layered MVC-inspired architecture:

```
Menu  ──────────────────────────────┐
  │                                 │
  ├── UserController                │
  │     registerUser()              │
  │     loginUser()                 │
  │                                 ▼
  └── MediaController          StreamingService
        searchTitle()          (data layer)
        searchCategory()
        playMedia()
        manageMedia()
```

---

## Collaboration

- Class diagram and sequence diagrams as a shared reference
- Kanban board for task management
- Git with frequent, descriptive commits
- Descriptive comments in the code for shared understanding
- Pair programming for the difficult parts