import Util.TextUI;

public class Movie extends Media{
    private int duration;
    TextUI ui;

    public Movie(String title, int releaseYear, double rating, int duration) {
        super(title,releaseYear,rating);
        this.duration = duration;
    }

    public int getDuration(){
        return duration;
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
