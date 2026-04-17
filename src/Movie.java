public class Movie extends Media{
    private int duration;

    public Movie(int duration) {
        this.duration = duration;
    }

    public int getDuration(){
        return duration;
    }

    public void play(){
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
