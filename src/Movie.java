public class Movie extends Media{
    private int duration;

    public Movie(String title, int releaseYear, double rating, int duration) {
        super(title,releaseYear,rating);
        this.duration = duration;
    }

    public int getDuration(){
        return duration;
    }

    @Override
    public void play(){
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
