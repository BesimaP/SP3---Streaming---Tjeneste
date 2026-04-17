import java.util.ArrayList;

public class Season {
    private int seasonNumber;
    private int year;
    private ArrayList<Episode> episodes;

    public int getSeasonNumber(){
        return seasonNumber;
    }

    public ArrayList<Episode> getEpisodes() {
        return episodes;
    }

    public void addEpisode(Episode e) {
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
