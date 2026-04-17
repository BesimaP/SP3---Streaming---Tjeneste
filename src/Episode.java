public class Episode {

    private String title;
    private int episodeNumber;
    private int duration;


    public Episode(String title, int episodeNumber, int duration) {
        this.title = title;
        this.episodeNumber = episodeNumber;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }


    public int getEpisodeNumber() {
        return episodeNumber;
    }


    public int getDuration() {
        return duration;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
