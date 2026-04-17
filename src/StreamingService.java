import java.util.ArrayList;
import Util.TextUI;
import Util.FileIO;

public class StreamingService {

    private ArrayList<Media>mediaList;
    private ArrayList<User>userList;
    private TextUI ui;
    private FileIO fileIO;

    public StreamingService( ArrayList<Media> mediaList, ArrayList<User> userList) {
        this.mediaList = mediaList;
        this.userList = userList;
        this.ui = new TextUI();
        this.fileIO = new FileIO();
    }

    public void startSystem() {
        ui.displayMsg("\uD83C\uDFAC\uD83C\uDF7F Welcome to BLLKstream \uD83D\uDCFA\uD83C\uDFA5");

        // Indlæs film fra CSV
        ArrayList<String> movieData = FileIO.readData("data/movies.csv");
        for (String line : movieData) {
            String[] parts = line.split(";");
            String title = parts[0].trim();
            int year = Integer.parseInt(parts[1].trim());
            double rating = Double.parseDouble(parts[3].trim().replace(",", "."));

            Movie movie = new Movie(title, year, rating, 0);

            mediaList.add(movie);
        }

        //Indlæs serier fra CSV
        ArrayList<String> seriesData = FileIO.readData("data/series.csv");
        for (String line : seriesData) {
            String[] parts = line.split(";");
            String title = parts[0].trim();
            int year = Integer.parseInt(parts[1].trim().substring(0, 4));
            double rating = Double.parseDouble(parts[3].trim().replace(",", "."));

            Series series = new Series(title, year, rating);

            mediaList.add(series);

        }
    }

    public void registerUser(){

    }

    public void loginUser(){

    }

    public void findMedia(){

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
}