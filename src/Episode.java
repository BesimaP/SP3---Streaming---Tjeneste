public class Episode extends Media{

    private String title;
    private int episodeNumber;
    private int duration;

    public Episode(String title, int episodeNumber, int duration) {
        super(title, episodeNumber, duration); // sender til Media konstruktøren
        this.title = title;
        this.episodeNumber = episodeNumber;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void play() {
        System.out.println("Now playing: " + title + " (Episode " + episodeNumber + ")");

    }


    public int getEpisodeNumber() {
        return episodeNumber;
    }


    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Episode " + episodeNumber + ": " + title;
    }
}
