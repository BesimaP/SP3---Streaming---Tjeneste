import java.util.ArrayList;

public class Series extends Media{
    private ArrayList<Season> seasons;

    public Series(String title, int relaseYear, double rating) {
        super(title,relaseYear,rating);
        this.seasons = new ArrayList<>();
    }

    public ArrayList<Season> getSeasons(){
        return seasons;
    }

    public void addSeason(Season s){

    }

    public int getNumberOfSeasons(){
        return 0;
    }

    public void play(){
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
