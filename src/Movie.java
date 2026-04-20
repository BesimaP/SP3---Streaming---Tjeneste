public class Movie extends Media{
    private int duration;

    public Movie(String title, int releaseYear, double rating) {
        super(title,releaseYear,rating);
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
