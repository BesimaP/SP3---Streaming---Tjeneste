import Util.TextUI;

public class Movie extends Media{


    public Movie(String title, int releaseYear, double rating) {
        super(title, releaseYear, rating);
    }


    @Override
    public void play() {
        // Viser at filmen afspilles nu
        System.out.println(getTitle() + " is now playing...");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
