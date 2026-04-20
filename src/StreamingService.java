import java.util.ArrayList;
import Util.TextUI;
import Util.FileIO;

public class StreamingService {

    private ArrayList<Media>mediaList;
    private ArrayList<User>userList;
    private TextUI ui;
    private FileIO fileIO;

    //Uden argumenter - opretter tomme lister selv
    public StreamingService() {
        this.mediaList = new ArrayList<>();
        this.userList = new ArrayList<>();
        this.ui = new TextUI();
        this.fileIO = new FileIO();
    }

    public ArrayList<Media> getMediaList() {
        return mediaList;
    }

    public void setMediaList(ArrayList<Media> mediaList) {
        this.mediaList = mediaList;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }

    // Starter systemet: indlæser al data fra filer og viser startmenuen
    public void startSystem() {

        // Velkomstbesked
        ui.displayMsg("\uD83C\uDFAC\uD83C\uDF7F Welcome to BLLKstream \uD83D\uDCFA\uD83C\uDFA5");

        // Læs alle linjer fra movies.csv
        // FileIO springer automatisk header-linjen over
        ArrayList<String> movieData = FileIO.readData("data/movies.csv");

        for (String line : movieData) {

            // Del linjen op ved ";" → [titel, år, genre, rating]
            String[] parts = line.split(";");
            String title  = parts[0].trim();
            int year      = Integer.parseInt(parts[1].trim());
            String genre  = parts[2].trim();
            double rating = Double.parseDouble(parts[3].trim().replace(",", "."));

            // Oprettet film-objekt med titel, år, rating og varighed 0 (ikke i CSV)
            Movie movie = new Movie(title, year, rating);

            // Deler genre op ved ","
            // og tilføj hver kategori til filmen
            String[] genres = genre.split(",");
            for (String g : genres) {
                Category c = parseCategory(g);
                // Tilføj kun hvis kategorien er kendt (ikke null)
                if (c != null) {
                    movie.addCategory(c);
                }
            }

            // Tilføj filmen til systemets samlede liste
            mediaList.add(movie);
        }

        // Læs alle linjer fra series.csv
        // FileIO springer automatisk header-linjen over
        ArrayList<String> seriesData = FileIO.readData("data/series.csv");

        for (String line : seriesData) {

            // Del linjen op ved ";"
            String[] parts = line.split(";");
            String title  = parts[0].trim();
            // Årstal kan stå som "2008-2013" – vi tager kun de første 4 tegn
            int year      = Integer.parseInt(parts[1].trim().substring(0, 4));
            String genre  = parts[2].trim();
            double rating = Double.parseDouble(parts[3].trim().replace(",", "."));
            String season = parts[4].trim();

            // Oprettet serie-objekt med titel, år og rating
            Series series = new Series(title, year, rating);

            // Deler genre op ved "," og tilføj hver kategori til serien
            String[] genres = genre.split(",");
            for (String g : genres) {
                Category c = parseCategory(g);
                // Tilføjes kun hvis kategorien er kendt (ikke null)
                if (c != null) {
                    series.addCategory(c);
                }
            }

            // → sæson 1 har 7 episoder, sæson 2 har 13 episoder
            // Del op ved "," → ["1-7", "2-13"]
            String[] seasons = season.split(",");
            for (String s : seasons) {

                // Del op ved "-" → [sæsonnummer, antal episoder]
                String[] seasonData = s.split("-");
                int seasonNumber = Integer.parseInt(seasonData[0].trim());
                int episodeCount = Integer.parseInt(seasonData[1].trim());

                // Oprettet sæson-objekt
                Season newSeason = new Season(seasonNumber, year);

                // Tilføjet det rigtige antal episoder til sæsonen
                for (int i = 1; i <= episodeCount; i++) {
                    newSeason.addEpisode(new Episode("Episode " + i, i, 0));
                }

                // Tilføj den færdige sæson til serien
                series.addSeason(newSeason);
            }

            // Tilføj serien til systemets samlede liste
            mediaList.add(series);
        }

        // loadUsers() læser users.csv og returnerer en liste af User-objekter
        // hvis filen ikke findes returnerer FileIO en tom liste
        userList = loadUsers();

        // Hvis ingen brugere findes → send til UC2: RegisterUser
        // Ellers → vis startmenuen med login/opret valgmuligheder
        if (userList.isEmpty()) {
            registerUser();
        } else {
            showStartMenu();
        }
        ArrayList<String> watchedMedia = FileIO.readData("data/watchedMedia.csv");


        ArrayList<String> savedMedia = FileIO.readData("data/savedMedia.csv");

    }

    public void registerUser() {
        int attempts = 0;
        while (attempts < 3) {
            // Spørger om brugernavn
            String username = ui.promptText("Enter your username: ");

            // Tjekker om brugernavnet allerede er taget
            for (User u : userList) {
                if (u.getUsername().equals(username)) {
                    ui.displayMsg("Username already exists, try again");
                    attempts++;
                }
            }

            // Spørger om adgangskode
            String password = ui.promptText("Enter your password: ");

            //TILFøjer userID og starter fra 1 og går op skal også parses
            int nextId = 1;
            for (User u : userList) {
                int uid = Integer.parseInt(u.getId());
                if (uid >= nextId) {
                    nextId = uid + 1;
                }
            }

            // Opretteer ny bruger og tilføj til listen
            User newUser = new User(String.valueOf(nextId), username, password);
            userList.add(newUser);


            // Gemmer den opdaterede brugerliste i filen
            saveUsers();

            // Sender videre til login (UC3)
            loginUser();
            return;
        }

        // 3 mislykkede forsøg → tilbage til startmenuen
        ui.displayMsg("Too many attempts, returning to start menu");
        showStartMenu();
    }

    public void loginUser(){
        int attempts = 0;
        while(attempts < 3) {
            String username = ui.promptText("Enter your username: ");
            String password = ui.promptText("Enter your password: ");

            for (User u : userList) {
                if (u.getUsername().equals(username) && u.checkLogin(password)) {
                    // Login lykkedes → vis hovedmenu
                    ui.displayMsg("Login successful");
                    showMainMenu(u);
                    return;
                }
            }
    
            // Forkert brugernavn eller password → tæl forsøg op
            attempts++;
            ui.displayMsg("Wrong username or password. Attempts left: " + (3 - attempts));
        }

        // 3 mislykkede forsøg → tilbage til startmenuen
        ui.displayMsg("Too many attempts, returning to start menu");
        showStartMenu();
    }

    public void findMedia(){

    }

    // Oversætter en tekst fra CSV-filen til et Category objekt
    // Eks: "Crime" → Category.Crime
    // Vi har brug for den fordi Java ikke selv forstår at "Crime" = Category.Crime
    private Category parseCategory(String genre) {

        // Gør teksten til lowercase og fjern mellemrum
        // Eks: " Crime " → "crime"
        switch (genre.toLowerCase().trim()) {

            case "crime":
                return Category.Crime;
            case "war":
                return Category.War;
            case "drama":
                return Category.Drama;
            case "family":
                return Category.Family;
            case "romance":
                return Category.Romance;
            case "sci-fi":
                return Category.Sciencefiction;
            case "mystery":
                return Category.Mystery;

            // Hvis teksten ikke matcher nogen kategori returneres null
            // Eks: "Unknown" → null
            // null håndteres i forloopet med if (c != null)
            default:          return null;
        }
    }

    // Læser users.csv og returnerer en liste af User-objekter
    // Hvis filen ikke findes returneres en tom liste (håndteres i FileIO)
    private ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<>();
        ArrayList<String> data = FileIO.readData("data/users.csv");
        for (String line : data) {
            // Hver linje ser sådan ud: "username,password"
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(",");
            users.add(new User(parts[0].trim(), parts[1].trim(), parts[2].trim()));
        }
        return users;
    }

    // Gemmer alle brugere i users.csv
    // Format: "username,password" – én bruger per linje - Tilføjhelse af userID for at holde styr på watch liste og saved
    private void saveUsers() {
        ArrayList<String> data = new ArrayList<>();
        for (User u : userList) {
            data.add(u.getId() +"," + u.getUsername() + "," + u.getPassword());
        }
        FileIO.saveData(data, "data/users.csv", "userID,username,password");
    }

    //Menuer
    private void showStartMenu() {
        ui.displayMsg("\n1. Login\n2. Create User");
        int choice = ui.promptNumeric("Choose:");
        if (choice == 1) loginUser();
        else if (choice == 2) registerUser();
    }

    private void showMainMenu(User user) {
        ui.displayMsg("\n1. Search by title\n2. Search by category\n3. Watched list\n4. Saved list");
        int choice = ui.promptNumeric("Choose:");
        run(user);
    }

    public void run(User user){
        String choice = "";
        while (!choice.equals("0")) {
            showMainMenu(user);
            choice = ui.promptText("Vælg:").trim();

            switch (choice) {
                case "1": searchTitle();
                break;
               // case "2": searchCategory();break;
                case "3":
                    displayWatchedList(user);
                    manageMedia(user, user.getWatched());
                    break; // break rykkes for syntax
                case "4":
                    displaySavedList(user);
                    manageMedia(user, user.getSaved());
                    break;
                case "0": System.out.println("Afslutter...");
                break;
                default :System.out.println("Ugyldigt valg, prøv igen.");
            }
        }
    }

    public void searchTitle() {
        String title = ui.promptText("Search title: ");

        for (Media m : mediaList) {
            if (m.getTitle().toLowerCase().contains(title.toLowerCase())) {
                ui.displayMsg(m.getTitle());
            }
        }
    }

    public void searchCategory(String title) {


    }

    public void displayWatchedList(User user){
        if(user.getWatched().isEmpty()){
            ui.displayMsg("List is empty");
            return;
        }
        for (Media watchedList : user.getWatched()) {
            ui.displayMsg(watchedList.getTitle());
        }
    }

    public void displaySavedList(User user){
        if(user.getWatched().isEmpty()){
            ui.displayMsg("List is empty");
            return;
        }
        for(Media savedList : user.getSaved()){
            ui.displayMsg(savedList.getTitle());
        }
    }

    private void manageMedia(User user, ArrayList<Media>list){
        boolean found = false;
        String titleChoice = ui.promptText("Enter title: You can play or delete a media from your list");
        for(Media m: list){
            if(m.getTitle().equals(titleChoice)){
                found = true;
                //film fundet - vis valg:
                ui.displayMsg("You have chosen"+ titleChoice);
                int choice = ui.promptNumeric("Press 1 to play and press 2 to delete");

                if(choice == 1){
                    m.play();
                    user.addToWatched(m);
                }else if (choice == 2){
                    user.removeFromSaved(m);
                    saveUsers();
                    ui.displayMsg(titleChoice + " has been removed");
                }
                return;
            }
        }
        if(!found){
            ui.displayMsg("Title not found");
            manageMedia(user, list);
        }
    }


}