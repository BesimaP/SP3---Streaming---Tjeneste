import java.util.ArrayList;
import Util.TextUI;
import Util.FileIO;

    public class StreamingService {

        // ── Attributter ────────────────────────────────────────
        // Liste over alle film og serier i systemet
        private ArrayList<Media> mediaList;

        // Liste over alle registrerede brugere
        private ArrayList<User> userList;

        // UI-objekt til at vise beskeder og læse input
        private TextUI ui;

        // Controllers til brugere, media og menuer
        private UserController userController;
        private MediaController mediaController;
        private Menu menu;

        // ── Konstruktør ───────────────────────────────────
        public StreamingService() {
            this.mediaList = new ArrayList<>();
            this.userList = new ArrayList<>();
            this.ui = new TextUI();
            // Opret controllerne
            this.mediaController = new MediaController(this);
            this.userController = new UserController(this);
            // Menu oprettes sidst og sættes på UserController via setter
            this.menu = new Menu(userController, mediaController, ui);
            this.userController.setMenu(menu);
        }

        // ══════════════════════════════════════════════════
        // UC1: StartSystem
        // ══════════════════════════════════════════════════
        public void startSystem() {

            // Vis velkomstbesked
            ui.displayMsg("\uD83C\uDFAC\uD83C\uDF7F Welcome to BLLKstream \uD83D\uDCFA\uD83C\uDFA5");

            // ── Indlæs film fra CSV ───────────────────────
            ArrayList<String> movieData = FileIO.readData("data/movies.csv");
            for (String line : movieData) {
                // Del linjen op ved ";" → [titel, år, genre, rating]
                String[] parts = line.split(";");
                String title  = parts[0].trim();
                int year      = Integer.parseInt(parts[1].trim());
                String genre  = parts[2].trim();
                double rating = Double.parseDouble(parts[3].trim().replace(",", "."));

                // Opret film-objekt
                Movie movie = new Movie(title, year, rating, 0);

                // Tilføj kategorier
                for (String g : genre.split(",")) {
                    Category c = parseCategory(g);
                    if (c != null) movie.addCategory(c);
                }

                mediaList.add(movie);
            }

            // ── Indlæs serier fra CSV ─────────────────────
            ArrayList<String> seriesData = FileIO.readData("data/series.csv");
            for (String line : seriesData) {
                // Del linjen op ved ";" → [titel, år, genre, rating, sæsoner]
                String[] parts = line.split(";");
                String title  = parts[0].trim();
                // Årstal kan stå som "2008-2013" – vi tager kun de første 4 tegn
                int year      = Integer.parseInt(parts[1].trim().substring(0, 4));
                String genre  = parts[2].trim();
                double rating = Double.parseDouble(parts[3].trim().replace(",", "."));
                String season = parts[4].trim();

                // Opret serie-objekt
                Series series = new Series(title, year, rating);

                // Tilføj kategorier
                for (String g : genre.split(",")) {
                    Category c = parseCategory(g);
                    if (c != null) series.addCategory(c);
                }

                // Opret sæsoner og episoder
                // Format: "1-8, 2-22" → sæson 1 har 8 episoder, sæson 2 har 22
                for (String s : season.split(",")) {
                    String[] seasonData = s.split("-");
                    int seasonNumber = Integer.parseInt(seasonData[0].trim());
                    int episodeCount = Integer.parseInt(seasonData[1].trim());

                    Season newSeason = new Season(seasonNumber, year);
                    for (int i = 1; i <= episodeCount; i++) {
                        newSeason.addEpisode(new Episode("Episode " + i, i, 0));
                    }
                    series.addSeason(newSeason);
                }

                mediaList.add(series);
            }

            // ── Indlæs brugere fra fil ────────────────────
            // Regnvejrsdag: hvis filen ikke findes returnerer FileIO en tom liste
            userList = loadUsers();

            // Ingen brugere → opret ny (UC2), ellers vis startmenu
            if (userList.isEmpty()) {
                userController.registerUser();
            } else {
                menu.showStartMenu();
            }
        }

        // ── Hjælpemetoder ─────────────────────────────────

        // Oversætter tekst fra CSV til Category-objekt
        // Eks: "crime" → Category.Crime | returnerer null hvis ukendt
        public Category parseCategory(String genre) {
            switch (genre.toLowerCase().trim()) {
                case "crime":   return Category.Crime;
                case "war":     return Category.War;
                case "drama":   return Category.Drama;
                case "family":  return Category.Family;
                case "romance": return Category.Romance;
                case "sci-fi":  return Category.Sciencefiction;
                case "mystery": return Category.Mystery;
                default:        return null;
            }
        }

        // Læser users.csv og returnerer en liste af User-objekter
        private ArrayList<User> loadUsers() {
            ArrayList<User> users = new ArrayList<>();
            ArrayList<String> data = FileIO.readData("data/users.csv");
            for (String line : data) {
                if (line.trim().isEmpty()) continue;
                // Format: "userID,username,password"
                String[] parts = line.split(",");
                users.add(new User(parts[0].trim(), parts[1].trim(), parts[2].trim()));
            }
            return users;
        }

        // Gemmer alle brugere i users.csv
        // Format: "userID,username,password" – én bruger per linje
        public void saveUsers() {
            ArrayList<String> data = new ArrayList<>();
            for (User u : userList) {
                data.add(u.getId() + "," + u.getUsername() + "," + u.getPassword());
            }
            FileIO.saveData(data, "data/users.csv", "userID,username,password");
        }

        public void saveWatchMedia(){

        }
        public void loadSavedMedia(){

        }
        public void loadWatchMedia(){

        }
        public void saveSavedMedia(){

        }

        // ── Getters ───────────────────────────────────────

        public ArrayList<Media> getMediaList() {
            return mediaList;
        }

        public ArrayList<User> getUserList() {
            return userList;
        }

        public TextUI getUi() {
            return ui;
        }

        // Tilføjer en bruger til listen
        public void addUser(User u) {
            userList.add(u);
        }
    }
