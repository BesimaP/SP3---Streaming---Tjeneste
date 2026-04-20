import Util.TextUI;

import java.util.ArrayList;

public class Series extends Media{
    private ArrayList<Season> seasons;
    private TextUI ui;

    public Series(String title, int relaseYear, double rating) {
        super(title,relaseYear,rating);
        this.seasons = new ArrayList<>();
    }

    public ArrayList<Season> getSeasons(){
        return seasons;
    }

    public void addSeason(Season s){
        seasons.add(s);
    }

    public int getNumberOfSeasons(){
        return seasons.size();
    }

    @Override
    public void play(){
        ui.displayMsg(getTitle() + " is now playing...");
    }

    @Override
    public String toString() {
        return super.toString() + " - Seasons: " + getNumberOfSeasons();
    }
}
