import java.util.ArrayList;

public abstract class Media {
    private String title;
    private int releaseYear;
    private double rating;
    private ArrayList<Category> categories;
    private String imagePath;

    public Media(String title, int releaseYear, double rating) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.categories = new ArrayList<>();
        this.imagePath = "";
    }

    public String getTitle(){
        return title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public double getRating(){
        return rating;
    }

    public String getImagePath(){
        return imagePath;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void addCategory(Category c){
        categories.add(c);
    }

    public abstract void play();

    @Override
    public String toString() {
        return title + " (" + releaseYear + ") - Rating: " + rating;
    }
}
