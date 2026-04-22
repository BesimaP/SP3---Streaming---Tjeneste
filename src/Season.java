import java.util.ArrayList;

public class Season {
    private int seasonNumber;
    private ArrayList<Episode> episodes;

    public Season(int seasonNumber) {
        this.seasonNumber = seasonNumber;
        this.episodes = new ArrayList<>();
    }

    public int getSeasonNumber(){
        return seasonNumber;
    }

    public ArrayList<Episode> getEpisodes() {
        return episodes;
    }

    public void addEpisode(Episode e) {
        episodes.add(e);
    }

    @Override
    public String toString() {
        return "Season " + seasonNumber + " - Episodes: " + episodes.size();
    }
}
