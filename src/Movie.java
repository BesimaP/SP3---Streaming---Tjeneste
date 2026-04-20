import Util.TextUI;

public class Movie extends Media{

    TextUI ui;

    public Movie(String title, int releaseYear, double rating) {
        super(title,releaseYear,rating);
    }



    @Override
    public void play(){
        ui.displayMsg(getTitle() + " is now playing...");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
