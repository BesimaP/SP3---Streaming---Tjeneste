public class Episode extends Media{

    private String title;
    private int episodeNumber;


    public Episode(String title, int episodeNumber, int releaseYear, double rating) {
        super(title,releaseYear,rating); // sender til Media konstruktøren
        this.title = title;
        this.episodeNumber = episodeNumber;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void play() {
        System.out.println("Now playing: " + title + " (Episode " + episodeNumber + ")");
    }


    @Override
    public String toString() {
        return "Episode " + episodeNumber + ": " + title;
    }
}
